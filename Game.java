import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.FontFormatException;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.util.*;
import java.io.*;

public class Game extends Canvas implements Runnable {

  public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9, SQUARE = 32;
  public static final int STARTX = WIDTH/2-SQUARE/2, STARTY = HEIGHT/2-SQUARE/2;
  public static Font customfont;
  public static Player player;
  public static ArrayList<NPC> npcs = new ArrayList<NPC>();
  public static ArrayList<Chest> chests = new ArrayList<Chest>();
  public static Pause pause;
  private Thread thread;
  private boolean running = false;
  private Handler handler;
  public ArrayList<ArrayList<String>> map;
  private HUD hud;
  public static State state;
  public static int currentTopRow = 0;
  public static int currentLeftCol = 0;
  public static int playerX = STARTX, playerY = STARTY;

  public Game(String text){
    this.map = makeMap(text);

    handler = new Handler();
    pause = new Pause(0,0,ID.Pause);

    this.addKeyListener(new KeyInput(handler));

    new Window(WIDTH, HEIGHT, "Twin Game", this);

    hud = new HUD();
    state = State.Play;
    int rows = HEIGHT / SQUARE;
    int cols = WIDTH / SQUARE;
    int numRows = map.size();
    for(int r = 0; r < numRows; r++){
      ArrayList<String> currentRow = map.get(r);
      int numCols = currentRow.size();
      for(int c = 0; c < numCols; c++){
        String currentBlock = currentRow.get(c);
        if(currentBlock.contains("Obstacle")){
          handler.addObject(new Obstacle(c*SQUARE, r*SQUARE, ID.Obstacle));
        }
        else if (currentBlock.contains("Grass1")){
          handler.addObject(new Grass1(c*SQUARE, r*SQUARE, ID.Grass1));
        }
        else if (currentBlock.contains("Grass2")){
          handler.addObject(new Grass2(c*SQUARE, r*SQUARE, ID.Grass2));
        }
        else if (currentBlock.contains("NPC")){
          ArrayList<String> fullobject = new ArrayList<String>(Arrays.asList(currentBlock.split("<")));
          ArrayList<String> convo = new ArrayList<String>(Arrays.asList(fullobject.get(1).split(">")));
          WordBubble bubble = new WordBubble(c*SQUARE, r*SQUARE, ID.WordBubble, handler, convo);
          NPC newNPC = new NPC(c*SQUARE, r*SQUARE, ID.NPC, bubble);
          handler.addObject(newNPC);
          npcs.add(newNPC);
        }
        else {
        }
        if (currentBlock.contains("Chest")){
          Chest newChest = new Chest(c*SQUARE, r*SQUARE, ID.Chest);
          handler.addObject(newChest);
          chests.add(newChest);
        }
      }
    }
    player = new Player(playerX, playerY, ID.Player, handler);
    handler.addObject(player);

  }

  public static Pause getPause(){
    return pause;
  }

  public static State getState(){
    return state;
  }

  public static void setState(State s){
    state = s;
  }

  public static Player getPlayer(){
    return player;
  }

  public static ArrayList<NPC> getNPCS(){
    return npcs;
  }

  public static ArrayList<Chest> getChests(){
    return chests;
  }

  public static int getPlayerX(){
    return playerX;
  }

  public static void setPlayerX(int x){
    playerX = x;
  }

  public static int getPlayerY(){
    return playerY;
  }

  public static void setPlayerY(int y){
    playerY = y;
  }

  public synchronized void start(){
    thread = new Thread(this);
    thread.start();
    running = true;
  }

  public synchronized void stop(){
    try {
      thread.join();
      running = false;
    }
    catch(Exception e) {
      e.printStackTrace();
    }
  }

  public void run(){
    this.requestFocus();
    long lastTime = System.nanoTime();
    double amountOfTicks = 60.0;
    double ns = 1000000000 / amountOfTicks;
    double delta = 0;
    long timer = System.currentTimeMillis();
    int frames = 0;
    while(running){
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while(delta >= 1) {
        tick();
        delta--;
      }
      if(running)
        render();
      frames++;

      if(System.currentTimeMillis() - timer > 1000){
        timer += 1000;
        System.out.println("FPS: " + frames);
        frames = 0;
      }
    }
    stop();
  }

  private void tick(){
    handler.tick();
    hud.tick();
  }

  private void render(){
    BufferStrategy bs = this.getBufferStrategy();
    if(bs == null){
      this.createBufferStrategy(2);
      return;
    }

    Graphics g = bs.getDrawGraphics();

    g.setColor(Color.black);
    g.fillRect(0,0,WIDTH,HEIGHT);

    handler.render(g);
    hud.render(g);

    g.dispose();
    bs.show();
  }

  public static int clamp(int var, int min, int max){
    if(var >= max){
      return var = max;
    }
    else if(var <= min){
      return var = min;
    }
    else{
      return var;
    }
  }

  public ArrayList<ArrayList<String>> makeMap(String text){
    ArrayList<ArrayList<String>> map = new ArrayList<ArrayList<String>>();
    ArrayList<String> rows = new ArrayList<String>(Arrays.asList(text.split("]")));
    for(int r = 0; r < rows.size(); r++){
      ArrayList<String> currentRow = new ArrayList<String>(Arrays.asList(rows.get(r).split(",")));
      map.add(currentRow);
    }
    return map;
  }

  public static void main(String args[]){
    File file = new File("map.txt");
    try {
      customfont = Font.createFont(Font.TRUETYPE_FONT, new File("joystix monospace.ttf")).deriveFont(14f);
      GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
      ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("joystix monospace.ttf")));
    } catch (IOException|FontFormatException e) {
      //Handle exception
    }
    try{
      FileReader fr = new FileReader(file);
      long length = file.length();
      char [] a = new char[(int) length];
      int size = fr.read(a);
      String text = "";
      for(char c : a){
        text = text + c;
      }
      new Game(text);
    }catch(IOException ex){
      ex.printStackTrace();
    }
  }
}
