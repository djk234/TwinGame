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
    return new Rectangle(x, y, Game.SQUARE, Game.SQUARE);
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(new Color(86, 163, 10));
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2, y-Game.getPlayerY()+Game.HEIGHT/2, Game.SQUARE, Game.SQUARE);

    g.setColor(new Color(66, 143, 0));
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2, y-Game.getPlayerY()+Game.HEIGHT/2,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+7*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+9*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+13*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+1*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+2*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+2*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+2*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+10*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+2*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+14*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+2*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+4*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+4*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+5*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+4*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+10*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+4*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+11*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+4*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2, y-Game.getPlayerY()+Game.HEIGHT/2+6*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+3*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+6*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+8*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+6*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+12*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+6*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+4*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+8*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+7*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+8*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+8*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+8*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+14*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+8*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+3*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+10*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+6*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+10*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+12*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+10*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+13*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+10*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+1*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+12*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+7*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+12*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+10*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+12*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+14*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+12*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+5*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+14*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+6*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+14*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+10*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+14*Game.SQUARE/16,2,2);
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2+15*Game.SQUARE/16, y-Game.getPlayerY()+Game.HEIGHT/2+14*Game.SQUARE/16,2,2);
  }
}
