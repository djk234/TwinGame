import java.awt.Graphics;
import java.awt.Color;

public class Player extends GameObject {

  public static final int SQUARE = 16;

  public Player(int x, int y, ID id) {
    super(x, y, id);
  }

  public void tick() {
    x += velX;
    y += velY;
  }

  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, SQUARE, SQUARE);
  }
}
