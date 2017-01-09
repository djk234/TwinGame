import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Chest extends GameObject {

  public static BufferedImage img;
  public static BufferedImage img0;
  public static BufferedImage img1;
  public static BufferedImage img2;
  public static BufferedImage img3;
  public static BufferedImage img4;
  public static BufferedImage img5;
  public static BufferedImage img6;
  public static BufferedImage img7;
  public static BufferedImage img8;
  public int count = -1;
  public boolean opened = false;
  public static Item item;

  public Chest(int x, int y, ID id, Item item) {
    super(x, y, id);
    this.item = item;
    try{
      this.img0 = ImageIO.read(new File("Images/chest/chest_0.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img1 = ImageIO.read(new File("Images/chest/chest_1.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img2 = ImageIO.read(new File("Images/chest/chest_2.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img3 = ImageIO.read(new File("Images/chest/chest_3.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img4 = ImageIO.read(new File("Images/chest/chest_4.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img5 = ImageIO.read(new File("Images/chest/chest_5.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img6 = ImageIO.read(new File("Images/chest/chest_6.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img7 = ImageIO.read(new File("Images/chest/chest_7.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img8 = ImageIO.read(new File("Images/chest/chest_8.png"));
    }
    catch(IOException ex){
    }
    this.img = this.img0;
  }

  public Rectangle getBounds(){
    return new Rectangle(x, y+Game.SQUARE/2, Game.SQUARE, Game.SQUARE/2);
  }

  public Rectangle getOpenBounds(){
    return new Rectangle(x, y+Game.SQUARE/2, Game.SQUARE, Game.SQUARE/2+2);
  }

  public static Item getItem(){
    return item;
  }

  public void startOpening(){
    this.count = 0;
  }

  public boolean getOpened(){
    return opened;
  }

  public void tick() {
    if (count < 0){

    }
    else if (count == 54){
      opened = true;
      System.out.println("Showing");
      item.startShowing();
      count = -1;
    }
    else {
      if (count%54 < 6) {
        this.img = this.img0;
      }
      else if (count%54 < 12){
        this.img = this.img1;
      }
      else if (count%54 < 18){
        this.img = this.img2;
      }
      else if (count%54 < 24){
        this.img = this.img3;
      }
      else if (count%54 < 30){
        this.img = this.img4;
      }
      else if (count%54 < 36){
        this.img = this.img5;
      }
      else if (count%54 < 42){
        this.img = this.img6;
      }
      else if (count%54 < 48){
        this.img = this.img7;
      }
      else if (count%54 < 54){
        this.img = this.img8;
      }
      count++;
    }
  }

  public void render(Graphics g) {
    g.drawImage(this.img,x-Game.getPlayerX()+(Game.WIDTH/2-Game.SQUARE/2), y-Game.getPlayerY()+(Game.HEIGHT/2-Game.SQUARE/2),null);
  }
}
