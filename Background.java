import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Background extends GameObject {

  public Background(int x, int y, ID id) {
    super(x, y, id);
  }

  public Rectangle getBounds(){
    return new Rectangle(x%Game.WIDTH, y%Game.HEIGHT,Game.SQUARE,Game.SQUARE);
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(new Color(100,200,100));
    g.fillRect(x%Game.WIDTH, y%Game.HEIGHT, Game.SQUARE, Game.SQUARE);
  }
}
