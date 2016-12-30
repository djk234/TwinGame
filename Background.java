import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class Background extends GameObject {

  public static boolean passable;

  public Background(int x, int y, ID id, boolean passable) {
    super(x, y, id);
    this.passable = passable;
  }

  public Rectangle getBounds(){
    return new Rectangle(x-1,y-1,Game.SQUARE+2,Game.SQUARE+2);
  }

  public boolean getPassable(){
    return passable;
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(Color.green);
    g.fillRect(x, y, Game.SQUARE, Game.SQUARE);
    g.setColor(Color.gray);
    g.drawRect(x, y, Game.SQUARE, Game.SQUARE);
  }
}
