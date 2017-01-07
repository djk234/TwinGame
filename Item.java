import java.awt.Graphics;
import java.awt.Rectangle;

public abstract class Item {

  protected int x, y;
  protected WordBubble bubble;
  protected ID id;

  public GameObject(int x, int y, ID id, WordBubble bubble){
    this.x = x;
    this.y = y;
    this.id = id;
    this.bubble = bubble;
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
