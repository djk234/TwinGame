import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Background extends GameObject {

  public static boolean passable;

  public Background(int x, int y, ID id) {
    super(x, y, id);
  }

  public Rectangle getBounds(){
    return new Rectangle(x-1,y-1,Game.SQUARE+2,Game.SQUARE+2);
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(new Color(100,200,100));
    g.fillRect(x, y, Game.SQUARE, Game.SQUARE);
    g.setColor(Color.gray);
    g.drawRect(x, y, Game.SQUARE, Game.SQUARE);
  }
}
