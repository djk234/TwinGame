import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

  LinkedList<GameObject> object = new LinkedList<GameObject>();

  public void tick(){
    for(int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      int minWidth = Game.getLeftCol()*Game.SQUARE;
      int maxWidth = Game.getLeftCol()*Game.SQUARE + Game.WIDTH - Game.SQUARE;
      int minHeight = Game.getTopRow()*Game.SQUARE;
      int maxHeight = Game.getTopRow()*Game.SQUARE + Game.HEIGHT - Game.SQUARE;
      if (tempObject.getX() >= minWidth && tempObject.getX() <= maxWidth && tempObject.getY() >= minHeight && tempObject.getY() <= maxHeight || tempObject.getId() == ID.Player){
        tempObject.tick();
      }
    }
  }

  public void render(Graphics g){
    for(int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      int minWidth = Game.getLeftCol()*Game.SQUARE;
      int maxWidth = Game.getLeftCol()*Game.SQUARE + Game.WIDTH - Game.SQUARE;
      int minHeight = Game.getTopRow()*Game.SQUARE;
      int maxHeight = Game.getTopRow()*Game.SQUARE + Game.HEIGHT - Game.SQUARE;
      if (tempObject.getX() >= minWidth && tempObject.getX() <= maxWidth && tempObject.getY() >= minHeight && tempObject.getY() <= maxHeight || tempObject.getId() == ID.Player){
        tempObject.render(g);
      }
    }
  }

  public void addObject(GameObject object){
    this.object.add(object);
  }

  public void removeObject(GameObject object){
    this.object.remove(object);
  }
}
