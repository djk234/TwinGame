import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;

public class NPC extends GameObject {

  Handler handler;
  ArrayList<String> conversation;
  WordBubble bubble;

  public NPC(int x, int y, ID id, WordBubble bubble) {
    super(x, y, id);
    this.handler = handler;
    this.bubble = bubble;
  }

  public Rectangle getBounds(){
    return new Rectangle(x,y,Game.SQUARE,Game.SQUARE);
  }

  public Rectangle getSpeakBounds(){
    return new Rectangle(x-1,y-1,Game.SQUARE+2,Game.SQUARE+2);
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.setColor(Color.yellow);
    g.fillRect(x-Game.getPlayerX()+(Game.WIDTH/2-Game.SQUARE/2), y-Game.getPlayerY()+(Game.HEIGHT/2-Game.SQUARE/2), Game.SQUARE, Game.SQUARE);
  }
}
