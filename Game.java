import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.util.*;
import java.io.*;

public class Game extends Canvas implements Runnable {

  public static int WIDTH = 640, HEIGHT = WIDTH / 12 * 9, SQUARE = 16;
  private Thread thread;
  private boolean running = false;
  private Handler handler;
  public ArrayList<ArrayList<String>> map;
  private HUD hud;
  public static int currentTopRow = 0;
  public static int currentLeftCol = 0;

  public Game(String text){
    this.map = makeMap(text);

    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));

    new Window(WIDTH, HEIGHT, "Twin Game", this);
    hud = new HUD();
    int rows = HEIGHT / SQUARE;
    int cols = WIDTH / SQUARE;
    System.out.println(rows+" by "+cols);
    int numRows = map.size();
    for(int r = 0; r < numRows; r++){
      ArrayList<String> currentRow = map.get(r);
      int numCols = currentRow.size();
      for(int c = 0; c < numCols; c++){
        String currentBlock = currentRow.get(c);
        if(currentBlock.equals("Obstacle")){
          handler.addObject(new Obstacle(c*SQUARE, r*SQUARE, ID.Obstacle));
        }
        else if (currentBlock.equals("Background")){
          handler.addObject(new Background(c*SQUARE, r*SQUARE, ID.Background));
        }
        else {
        }
      }
    }
    handler.addObject(new Player(WIDTH/2-SQUARE/2,HEIGHT/2-SQUARE/2, ID.Player, handler));
  }

  public static int getTopRow() {
    return currentTopRow;
  }

  public static void setTopRow(int row){
    currentTopRow = row;
  }

  public static int getLeftCol() {
    return currentLeftCol;
  }

  public static void setLeftCol(int col){
    currentLeftCol = col;
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
        //System.out.println("FPS: " + frames);
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
