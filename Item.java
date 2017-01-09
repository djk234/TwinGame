import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public abstract class Item {

  Handler handler;
  protected int x, y;
  protected int attack1, attack2;
  protected String description;
  protected WordBubble bubble;
  protected String name;
  protected ArrayList<BufferedImage> images;
  protected boolean shown = false;
  protected int count = -1;

  public Item(int x, int y, String name, ArrayList<BufferedImage> images, WordBubble bubble, Handler handler){
    this.x = x;
    this.y = y;
    this.attack1 = attack1;
    this.attack2 = attack2;
    this.name = name;
    this.bubble = bubble;
    this.images = images;
    this.description = description;
    this.handler = handler;
    /*
    this.images = new ArrayList<ImageIO>();
    int numPics = new File("Images/item/"+name).listFiles().length;
    for(int i = 0; i < numPics; i++) {
      try{
        this.images.add(ImageIO.read(new File("Images/item/"+name+"/"+name+"_"+i)));
      }
      catch(IOException ex){
      }
    }*/
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

  public int getAttack1(){
    return attack1;
  }

  public int getAttack2(){
    return attack2;
  }

  public String getDescription(){
    return description;
  }

  public ArrayList<BufferedImage> getPics(){
    return images;
  }

  public boolean getShown(){
    return shown;
  }

  public void setShown(boolean shown){
    this.shown = shown;
  }

  public void startShowing(){
    handler.addItem(this);
    handler.addObject(bubble);
    count = 0;
  }

  public void stopShowing(){
    handler.removeItem(this);
    shown = true;
    count = -1;
  }
}
