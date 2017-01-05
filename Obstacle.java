import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Obstacle extends GameObject {

  public Obstacle(int x, int y, ID id) {
    super(x, y, id);
  }

  public Rectangle getBounds(){
    return new Rectangle(x, y, Game.SQUARE, Game.SQUARE);
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(new Color(80,60,50));
    g.fillRect(x-Game.getPlayerX()+Game.WIDTH/2-Game.SQUARE/2, y-Game.getPlayerY()+Game.HEIGHT/2-Game.SQUARE/2, Game.SQUARE, Game.SQUARE);
  }
}
