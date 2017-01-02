import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;

public class Grass1 extends Background {

  Random rand = new Random();

  public Grass1(int x, int y, ID id) {
    super(x, y, id);
  }

  public Rectangle getBounds(){
    return new Rectangle(x%Game.WIDTH, y%Game.HEIGHT,Game.SQUARE,Game.SQUARE);
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(new Color(86, 163, 10));
    g.fillRect(x%Game.WIDTH, y%Game.HEIGHT,Game.SQUARE,Game.SQUARE);
    g.setColor(new Color(66, 143, 0));
    g.fillRect(x%Game.WIDTH, y%Game.HEIGHT,2,2);
    g.fillRect(x%Game.WIDTH+7, y%Game.HEIGHT,2,2);
    g.fillRect(x%Game.WIDTH+9, y%Game.HEIGHT,2,2);
    g.fillRect(x%Game.WIDTH+13, y%Game.HEIGHT,2,2);
    g.fillRect(x%Game.WIDTH+1, y%Game.HEIGHT+2,2,2);
    g.fillRect(x%Game.WIDTH+2, y%Game.HEIGHT+2,2,2);
    g.fillRect(x%Game.WIDTH+10, y%Game.HEIGHT+2,2,2);
    g.fillRect(x%Game.WIDTH+14, y%Game.HEIGHT+2,2,2);
    g.fillRect(x%Game.WIDTH+4, y%Game.HEIGHT+4,2,2);
    g.fillRect(x%Game.WIDTH+5, y%Game.HEIGHT+4,2,2);
    g.fillRect(x%Game.WIDTH+10, y%Game.HEIGHT+4,2,2);
    g.fillRect(x%Game.WIDTH+11, y%Game.HEIGHT+4,2,2);
    g.fillRect(x%Game.WIDTH, y%Game.HEIGHT+6,2,2);
    g.fillRect(x%Game.WIDTH+3, y%Game.HEIGHT+6,2,2);
    g.fillRect(x%Game.WIDTH+8, y%Game.HEIGHT+6,2,2);
    g.fillRect(x%Game.WIDTH+12, y%Game.HEIGHT+6,2,2);
    g.fillRect(x%Game.WIDTH+4, y%Game.HEIGHT+8,2,2);
    g.fillRect(x%Game.WIDTH+7, y%Game.HEIGHT+8,2,2);
    g.fillRect(x%Game.WIDTH+8, y%Game.HEIGHT+8,2,2);
    g.fillRect(x%Game.WIDTH+14, y%Game.HEIGHT+8,2,2);
    g.fillRect(x%Game.WIDTH+3, y%Game.HEIGHT+10,2,2);
    g.fillRect(x%Game.WIDTH+6, y%Game.HEIGHT+10,2,2);
    g.fillRect(x%Game.WIDTH+12, y%Game.HEIGHT+10,2,2);
    g.fillRect(x%Game.WIDTH+13, y%Game.HEIGHT+10,2,2);
    g.fillRect(x%Game.WIDTH+1, y%Game.HEIGHT+12,2,2);
    g.fillRect(x%Game.WIDTH+7, y%Game.HEIGHT+12,2,2);
    g.fillRect(x%Game.WIDTH+10, y%Game.HEIGHT+12,2,2);
    g.fillRect(x%Game.WIDTH+14, y%Game.HEIGHT+12,2,2);
    g.fillRect(x%Game.WIDTH+5, y%Game.HEIGHT+14,2,2);
    g.fillRect(x%Game.WIDTH+6, y%Game.HEIGHT+14,2,2);
    g.fillRect(x%Game.WIDTH+10, y%Game.HEIGHT+14,2,2);
    g.fillRect(x%Game.WIDTH+15, y%Game.HEIGHT+14,2,2);
  }
}
