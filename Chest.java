import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;


public class Chest extends GameObject {

  ArrayList<BufferedImage> images;
  private int img = 0;
  public int count = -1;
  public boolean opened = false;
  Item item;

  public Chest(int x, int y, ID id, Item item) {
    super(x, y, id);
    this.item = item;
    images = new ArrayList<BufferedImage>();
    for (int i = 0; i < 9; i++) {
      try{
        images.add(ImageIO.read(new File("Images/chest/chest_"+i+".png")));
      }
      catch(IOException ex){
      }
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(x, y+Game.SQUARE/2, Game.SQUARE, Game.SQUARE/2);
  }

  public Rectangle getOpenBounds(){
    return new Rectangle(x, y+Game.SQUARE/2, Game.SQUARE, Game.SQUARE/2+2);
  }

  public Item getItem(){
    return item;
  }

  public void startOpening(){
    this.count = 0;
  }

  public boolean getOpened(){
    return opened;
  }

  public void tick() {
    if (this.count < 0){

    }
    else if (this.count == 54){
      opened = true;
      item.startShowing();
      count = -1;
    }
    else {
      if (this.count%54 < 6) {
        this.img = 0;
      }
      else if (this.count%54 < 12){
        this.img = 1;
      }
      else if (this.count%54 < 18){
        this.img = 2;
      }
      else if (this.count%54 < 24){
        this.img = 3;
      }
      else if (this.count%54 < 30){
        this.img = 4;
      }
      else if (this.count%54 < 36){
        this.img = 5;
      }
      else if (this.count%54 < 42){
        this.img = 6;
      }
      else if (this.count%54 < 48){
        this.img = 7;
      }
      else if (this.count%54 < 54){
        this.img = 8;
      }
      this.count++;
    }
  }

  public void render(Graphics g) {
    g.drawImage(images.get(img),x-Game.getPlayerX()+(Game.WIDTH/2-Game.SQUARE/2), y-Game.getPlayerY()+(Game.HEIGHT/2-Game.SQUARE/2),null);
  }
}
