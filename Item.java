import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public abstract class Item {

  protected int x, y;
  protected WordBubble bubble;
  protected ID id;
  protected ArrayList<ImageIO> images;

  public Item(int x, int y, ID id, String name, WordBubble bubble){
    this.x = x;
    this.y = y;
    this.id = id;
    this.bubble = bubble;
    this.images = new ArrayList<ImageIO>();
    int numPics = new File("Images/item/"+name).listFiles().length;
    for(int i = 0; i < numPics; i++) {
      try{
        this.images.add(ImageIO.read(new File("Images/item/"+name+"/"+name+"_"+i)));
      }
      catch(IOException ex){
      }
    }
  }

  public abstract void tick();

  public abstract void render(Graphics g);

  public void setX(int x){
    this.x = x;
  }

  public void setY(int y){
    this.y = y;
  }

  public int getX(){
    return x;
  }

  public int getY(){
    return y;
  }

  public void setId(ID id){
    this.id = id;
  }

  public ID getId(){
    return id;
  }
}
