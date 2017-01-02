import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;

public class NPC extends GameObject {

  Handler handler;
  ArrayList<String> conversation;

  public NPC(int x, int y, ID id, ArrayList<String> conversation) {
    super(x, y, id);
    this.handler = handler;
    this.conversation = conversation;
  }

  public Rectangle getBounds(){
    return new Rectangle(x-1,y-1,Game.SQUARE+2,Game.SQUARE+2);
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x,y,Game.SQUARE,Game.SQUARE);
  }
}
