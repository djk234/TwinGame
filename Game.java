import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.awt.FontFormatException;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;
import java.io.*;

public class Game extends Canvas implements Runnable {

  public static final int WIDTH = 720, HEIGHT = WIDTH / 12 * 9, SQUARE = 32;
  public static final int STARTX = WIDTH/2-SQUARE/2, STARTY = HEIGHT/2-SQUARE/2;
  public static final int SCREENCOLS = WIDTH/SQUARE, SCREENROWS = HEIGHT/SQUARE;
  public static ArrayList<NPC> npcs = new ArrayList<NPC>();
  public static ArrayList<Chest> chests = new ArrayList<Chest>();
  public static Font customfont;
  public static Player player;
  public static Pause pause;
  private Thread thread;
  private boolean running = false;
  private Handler handler;
  public ArrayList<ArrayList<String>> map;
  private HUD hud;
  public static State state;
  public static int playerX = STARTX, playerY = STARTY;

  public Game(String text){
    handler = new Handler();
    player = new Player(playerX, playerY, ID.Player, handler);
    
    this.map = makeMap(text);
    pause = new Pause(0,0,ID.Pause);

    this.addKeyListener(new KeyInput(handler));

    new Window(WIDTH, HEIGHT, "Twin Game", this);

    hud = new HUD();
    state = State.Play;
    int numRows = map.size();
    for(int r = 0; r < numRows; r++){
      ArrayList<String> currentRow = map.get(r);
      ArrayList<GameObject> newRow = new ArrayList<GameObject>();
      ArrayList<Chest> newChestRow = new ArrayList<Chest>();
      ArrayList<NPC> newNPCRow = new ArrayList<NPC>();
      int numCols = currentRow.size();
      for(int c = 0; c < numCols; c++){
        newRow.add(null);
        newChestRow.add(null);
        newNPCRow.add(null);
        String currentBlock = currentRow.get(c);
        if(currentBlock.contains("Obstacle")){
          newRow.set(c, new Obstacle(c*SQUARE, r*SQUARE, ID.Obstacle));
        }
        else if (currentBlock.contains("Grass1")){
          newRow.set(c, new Grass1(c*SQUARE, r*SQUARE, ID.Grass1));
        }
        else if (currentBlock.contains("Grass2")){
          newRow.set(c, new Grass2(c*SQUARE, r*SQUARE, ID.Grass2));
        }
        else if (currentBlock.contains("NPC")){
          ArrayList<String> fullobject = new ArrayList<String>(Arrays.asList(currentBlock.split("<")));
          ArrayList<String> convo = new ArrayList<String>(Arrays.asList(fullobject.get(1).split(">")));
          WordBubble bubble = new WordBubble(c*SQUARE, r*SQUARE, ID.WordBubble, handler, convo);
          NPC newNPC = new NPC(c*SQUARE, r*SQUARE, ID.NPC, bubble);
          newNPCRow.set(c, newNPC);
          npcs.add(newNPC);
        }
        else {
        }
        if (currentBlock.contains("Chest")){
          if (currentBlock.contains("NoviceSword")){
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            for(int i = 0; i < 8; i++) {
              try{
                images.add(ImageIO.read(new File("Images/item/novicesword/novicesword_"+i+".png")));
              }
              catch(IOException ex){
              }
            }
            ArrayList<String> convo = new ArrayList<String>();
            convo.add("YOU'VE FOUND THE NOVICE SWORD.");
            convo.add("ITS SLIGHTLY DULL BLADE GLEAMS PLEASANTLY");
            WordBubble bubble = new WordBubble(c*SQUARE, r*SQUARE, ID.WordBubble, handler, convo);
            NoviceSword novicesword = new NoviceSword(c*SQUARE, r*SQUARE, images, bubble, handler);
            Chest newChest = new Chest(c*SQUARE, r*SQUARE, ID.Chest, novicesword);
            newChestRow.set(c, newChest);
            chests.add(newChest);
          }
          else if (currentBlock.contains("WhiteSteelSword")){
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            for(int i = 0; i < 8; i++) {
              try{
                images.add(ImageIO.read(new File("Images/item/whitesteelsword/whitesteelsword_"+i+".png")));
              }
              catch(IOException ex){
              }
            }
            ArrayList<String> convo = new ArrayList<String>();
            convo.add("YOU'VE FOUND THE WHITE STEEL SWORD.");
            convo.add("THE BLADE'S SHINE SHOWS...");
            convo.add("...THE CRAFTSMANSHIP OF THIS WEAPON");
            WordBubble bubble = new WordBubble(c*SQUARE, r*SQUARE, ID.WordBubble, handler, convo);
            WhiteSteelSword whitesteelsword = new WhiteSteelSword(c*SQUARE, r*SQUARE, images, bubble, handler);
            Chest newChest = new Chest(c*SQUARE, r*SQUARE, ID.Chest, whitesteelsword);
            newChestRow.set(c, newChest);
            chests.add(newChest);
          }
          else if (currentBlock.contains("SwordOfEternalLight")){
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            for(int i = 0; i < 8; i++) {
              try{
                images.add(ImageIO.read(new File("Images/item/swordofeternallight/swordofeternallight_"+i+".png")));
              }
              catch(IOException ex){
              }
            }
            ArrayList<String> convo = new ArrayList<String>();
            convo.add("YOU'VE FOUND THE SWORD OF ETERNAL LIGHT!");
            convo.add("ITS BLADE GLOWS HOT...");
            convo.add("...WITH THE DESIRE TO VANQUISH EVIL");
            WordBubble bubble = new WordBubble(c*SQUARE, r*SQUARE, ID.WordBubble, handler, convo);
            SwordOfEternalLight swordofeternallight = new SwordOfEternalLight(c*SQUARE, r*SQUARE, images, bubble, handler);
            Chest newChest = new Chest(c*SQUARE, r*SQUARE, ID.Chest, swordofeternallight);
            newChestRow.set(c, newChest);
            chests.add(newChest);
          }
          else if (currentBlock.contains("RoundShield")){
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            for(int i = 0; i < 8; i++) {
              try{
                images.add(ImageIO.read(new File("Images/item/roundshield/roundshield_"+i+".png")));
              }
              catch(IOException ex){
              }
            }
            ArrayList<String> convo = new ArrayList<String>();
            convo.add("YOU'VE FOUND A ROUND SHIELD");
            convo.add("LOOKS LIKE IT WILL HELP BLOCK ATTACKS");
            WordBubble bubble = new WordBubble(c*SQUARE, r*SQUARE, ID.WordBubble, handler, convo);
            RoundShield roundshield = new RoundShield(c*SQUARE, r*SQUARE, images, bubble, handler);
            Chest newChest = new Chest(c*SQUARE, r*SQUARE, ID.Chest, roundshield);
            newChestRow.set(c, newChest);
            chests.add(newChest);
          }
          else if (currentBlock.contains("StarShield")){
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            for(int i = 0; i < 8; i++) {
              try{
                images.add(ImageIO.read(new File("Images/item/starshield/starshield_"+i+".png")));
              }
              catch(IOException ex){
              }
            }
            ArrayList<String> convo = new ArrayList<String>();
            convo.add("YOU'VE FOUND THE STAR SHIELD");
            convo.add("WITH A MUCH HIGHER DEFENSE AND LIGHTER WEIGHT...");
            convo.add("WALK FASTER WITH IT AND BLOCK MORE ATTACKS.");
            WordBubble bubble = new WordBubble(c*SQUARE, r*SQUARE, ID.WordBubble, handler, convo);
            StarShield starshield = new StarShield(c*SQUARE, r*SQUARE, images, bubble, handler);
            Chest newChest = new Chest(c*SQUARE, r*SQUARE, ID.Chest, starshield);
            newChestRow.set(c, newChest);
            chests.add(newChest);
          }
          else if (currentBlock.contains("ClearGlassShield")){
            ArrayList<BufferedImage> images = new ArrayList<BufferedImage>();
            for(int i = 0; i < 8; i++) {
              try{
                images.add(ImageIO.read(new File("Images/item/clearglassshield/clearglassshield_"+i+".png")));
              }
              catch(IOException ex){
              }
            }
            ArrayList<String> convo = new ArrayList<String>();
            convo.add("YOU'VE FOUND THE CLEAR GLASS SHIELD!");
            convo.add("DEFLECT ATTACKS WITH INCREDIBLE POWER...");
            convo.add("PROJECTILES WILL BE REFLECG");
            WordBubble bubble = new WordBubble(c*SQUARE, r*SQUARE, ID.WordBubble, handler, convo);
            ClearGlassShield clearglassshield = new ClearGlassShield(c*SQUARE, r*SQUARE, images, bubble, handler);
            Chest newChest = new Chest(c*SQUARE, r*SQUARE, ID.Chest, clearglassshield);
            newChestRow.set(c, newChest);
            chests.add(newChest);
          }
        }
      }
      handler.object.add(newRow);
      handler.chests.add(newChestRow);
      handler.npcs.add(newNPCRow);
    }
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
      double delta_tick = 0;
      long tick_start = System.nanoTime();
      while(delta >= 1) {
        tick();
        delta--;
      }
      long tick_stop = System.nanoTime();
      delta_tick = (tick_stop - tick_start)/ns;
      double delta_render = 0;
      if(running){
        long render_start = System.nanoTime();
        render();
        long render_stop = System.nanoTime();
        delta_render = (render_stop - render_start)/ns;
      }
      frames++;

      if(System.currentTimeMillis() - timer > 1000){
        timer += 1000;
        System.out.println("FPS: " + frames+" Tick: "+ delta_tick+ " Render: "+delta_render);
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
      this.createBufferStrategy(3);
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
