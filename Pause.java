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
  public static int page = 0;
  public static int pageItem = 0;


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
    try{
      images.add(ImageIO.read(new File("Images/item/roundshield/roundshield.png")));
    }
    catch(IOException ex){
    }
  }

  public static void incSelectedItem(){
    if (pageItem + 1 < 3){
      pageItem++;
    }
    else {
      page = (page + 1)%3;
      pageItem = 0;
    }

  }

  public static void decSelectedItem(){
    if (pageItem - 1 >= 0){
      pageItem--;
    }
    else {
      page = (page - 1+3)%3;
      pageItem = 2;
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
    g.drawRect(Game.SQUARE*2+filler,Game.SQUARE*6,Game.SQUARE*4,Game.SQUARE*4);
    g.drawRect(Game.SQUARE*6+filler*2,Game.SQUARE*6,Game.SQUARE*4,Game.SQUARE*4);
    g.drawRect(Game.SQUARE*10+filler*3,Game.SQUARE*6,Game.SQUARE*4,Game.SQUARE*4);
    g.setFont(Game.customfont);
    if (pageItem == 0){
      int thickness = 5;
      for (int j = 1; j < thickness; j++) {
        g.drawRect(Game.SQUARE*2+filler+j,Game.SQUARE*6+j,Game.SQUARE*4-(j)*2,Game.SQUARE*4-(j)*2);
      }
    }
    if (pageItem == 1){
      int thickness = 5;
      for (int j = 1; j < thickness; j++) {
        g.drawRect(Game.SQUARE*6+filler*2+j,Game.SQUARE*6+j,Game.SQUARE*4-(j)*2,Game.SQUARE*4-(j)*2);
      }
    }
    if (pageItem == 2){
      int thickness = 5;
      for (int j = 1; j < thickness; j++) {
        g.drawRect(Game.SQUARE*10+filler*3+j,Game.SQUARE*6+j,Game.SQUARE*4-(j)*2,Game.SQUARE*4-(j)*2);
      }
    }
    if (page == 0){
      g.drawString("SWORDS", Game.WIDTH/2-Game.SQUARE-4, Game.SQUARE*5);
      for (int i = 0; i < inventory.size(); i++){
        if (inventory.get(i).name.contains("NoviceSword")){
          g.drawImage(images.get(0),Game.SQUARE*2+filler,Game.SQUARE*6+10,null);
        }
        else if (inventory.get(i).name.contains("WhiteSteelSword")){
          g.drawImage(images.get(1),Game.SQUARE*6+filler*2,Game.SQUARE*6+10,null);
        }
        else if (inventory.get(i).name.contains("SwordOfEternalLight")){
          g.drawImage(images.get(2),Game.SQUARE*10+filler*3,Game.SQUARE*6+10,null);
       }
      }
    }
    if (page == 1){
      g.drawString("SHIELDS", Game.WIDTH/2-Game.SQUARE-10, Game.SQUARE*5);
      for (int i = 0; i < inventory.size(); i++){
        if (inventory.get(i).name.contains("RoundShield")){
          g.drawImage(images.get(3),Game.SQUARE*2+filler,Game.SQUARE*6,null);
        }
      }
    }
    if (page == 2){
      g.drawString("SPECIAL ITEMS", Game.WIDTH/2-Game.SQUARE-43, Game.SQUARE*5);
    }
    g.drawString("PAUSE", Game.WIDTH/2-Game.SQUARE+3, Game.SQUARE*2+5);
    int item_x = Game.SQUARE*3;
    int item_y = Game.SQUARE*3;
  }
}
