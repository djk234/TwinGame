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
    ArrayList<Item> inventory = Game.getPlayer().getInventory();
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
    int item_x = Game.SQUARE*3;
    int item_y = Game.SQUARE*3;
    for (int i = 0; i < inventory.size(); i++){
      g.drawString(inventory.get(i).name,item_x,item_y);
      item_y += Game.SQUARE;
    }
  }
}
