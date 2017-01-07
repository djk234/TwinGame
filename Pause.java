import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.util.*;

public class Pause extends GameObject{

  public Pause(int x, int y, ID id) {
    super(x, y, id);
  }

  public Rectangle getBounds(){
    return new Rectangle(Game.getPlayerX()-Game.WIDTH/2+Game.SQUARE, Game.getPlayerY()-Game.HEIGHT/2+Game.SQUARE, Game.WIDTH - Game.SQUARE*2, Game.WIDTH - Game.SQUARE*2);
  }

  public void tick() {

  }

  public void render(Graphics g){
    g.setColor(Color.black);
    g.fillRect(Game.SQUARE*2, Game.SQUARE*2, Game.WIDTH - Game.SQUARE*4, Game.HEIGHT - Game.SQUARE*4);
    g.setColor(Color.white);
    g.drawRect(Game.SQUARE*2, Game.SQUARE*2, Game.WIDTH - Game.SQUARE*4, Game.HEIGHT - Game.SQUARE*4);
    g.setColor(Color.black);
    g.fillRect(Game.WIDTH/2-Game.SQUARE*2, Game.SQUARE, Game.SQUARE*4, Game.SQUARE*2);
    g.setColor(Color.white);
    g.drawRect(Game.WIDTH/2-Game.SQUARE*2, Game.SQUARE, Game.SQUARE*4, Game.SQUARE*2);
    g.setFont(Game.customfont);
    g.drawString("PAUSE", Game.WIDTH/2-Game.SQUARE+3, Game.SQUARE*2+5);
  }
}
