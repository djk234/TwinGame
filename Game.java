import java.awt.Canvas;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.image.BufferStrategy;
import java.awt.event.KeyEvent;
import java.util.Random;

public class Game extends Canvas implements Runnable {

  public static int WIDTH = 640, HEIGHT = WIDTH / 12 * 9, SQUARE = 16;
  private Thread thread;
  private boolean running = false;
  private Handler handler;
  Random rand = new Random();
  private HUD hud;

  public Game(){
    handler = new Handler();
    this.addKeyListener(new KeyInput(handler));

    new Window(WIDTH, HEIGHT, "Twin Game", this);
    hud = new HUD();
    int rows = HEIGHT / SQUARE;
    int cols = WIDTH / SQUARE;
    for(int r = 0; r < rows; r++){
      for(int c = 0; c < cols; c++){
        int n = rand.nextInt(25) + 1;
        if(n == 10){
          handler.addObject(new Obstacle(c*SQUARE, r*SQUARE, ID.Obstacle));
        }
        else{
          handler.addObject(new Background(c*SQUARE, r*SQUARE, ID.Background));
        }
      }
    }
    handler.addObject(new Player(WIDTH/2-SQUARE/2,HEIGHT/2-SQUARE/2, ID.Player, handler));
    handler.addObject(new BasicEnemy(100,100, ID.BasicEnemy, handler));
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

  public static void main(String args[]){
    new Game();
  }
}
