import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.*;

public class Pause extends GameObject{

  ArrayList<BufferedImage> images;

  public Pause(int x, int y, ID id) {
    super(x, y, id);
    images = new ArrayList<BufferedImage>();
    try{
      images.add(ImageIO.read(new File("Images/item/novicesword/novicesword.png")));
    }
    catch(IOException ex){
    }
    try{
      images.add(ImageIO.read(new File("Images/item/whitesteelsword/whitesteelsword.png")));
    }
    catch(IOException ex){
    }
    try{
      images.add(ImageIO.read(new File("Images/item/swordofeternallight/swordofeternallight.png")));
    }
    catch(IOException ex){
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(Game.getPlayerX()-Game.WIDTH/2+Game.SQUARE, Game.getPlayerY()-Game.HEIGHT/2+Game.SQUARE, Game.WIDTH - Game.SQUARE*2, Game.WIDTH - Game.SQUARE*2);
  }

  public void tick() {

  }

  public void render(Graphics g){
    ArrayList<Item> inventory = Game.getPlayer().getInventory();
    int filler = (int)(Game.WIDTH - Game.SQUARE*4-Game.SQUARE*12)/4;
    g.setColor(Color.black);
    g.fillRect(Game.SQUARE*2, Game.SQUARE*2, Game.WIDTH - Game.SQUARE*4, Game.HEIGHT - Game.SQUARE*4);
    g.setColor(Color.white);
    g.drawRect(Game.SQUARE*2, Game.SQUARE*2, Game.WIDTH - Game.SQUARE*4, Game.HEIGHT - Game.SQUARE*4);
    g.setColor(Color.black);
    g.fillRect(Game.WIDTH/2-Game.SQUARE*2, Game.SQUARE, Game.SQUARE*4, Game.SQUARE*2);
    g.setColor(Color.white);
    g.drawRect(Game.WIDTH/2-Game.SQUARE*2, Game.SQUARE, Game.SQUARE*4, Game.SQUARE*2);
    g.drawRect(Game.SQUARE*2+filler,Game.SQUARE*4,Game.SQUARE*4,Game.SQUARE*4);
    g.drawRect(Game.SQUARE*6+filler*2,Game.SQUARE*4,Game.SQUARE*4,Game.SQUARE*4);
    g.drawRect(Game.SQUARE*10+filler*3,Game.SQUARE*4,Game.SQUARE*4,Game.SQUARE*4);
    for (int i = 0; i < inventory.size(); i++){
      if (inventory.get(i).name.contains("NoviceSword")){
        g.drawImage(images.get(0),Game.SQUARE*2+filler,Game.SQUARE*4+10,null);
      }
      else if (inventory.get(i).name.contains("WhiteSteelSword")){
        g.drawImage(images.get(1),Game.SQUARE*6+filler*2,Game.SQUARE*4+10,null);
      }
      else if (inventory.get(i).name.contains("SwordOfEternalLight")){
        g.drawImage(images.get(2),Game.SQUARE*10+filler*3,Game.SQUARE*4+10,null);
     }
    }
    g.setFont(Game.customfont);
    g.drawString("PAUSE", Game.WIDTH/2-Game.SQUARE+3, Game.SQUARE*2+5);
    int item_x = Game.SQUARE*3;
    int item_y = Game.SQUARE*3;
  }
}
