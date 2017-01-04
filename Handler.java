import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

  LinkedList<GameObject> object = new LinkedList<GameObject>();

  public void tick(){
    for(int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      int minWidth = Game.getPlayerX() - Game.WIDTH/2 - Game.SQUARE;
      int maxWidth = Game.getPlayerX() + Game.WIDTH/2 + Game.SQUARE;
      int minHeight = Game.getPlayerY() - Game.HEIGHT/2 - Game.SQUARE;
      int maxHeight = Game.getPlayerY() + Game.HEIGHT/2 + Game.SQUARE;
      if (tempObject.getX() >= minWidth && tempObject.getX() <= maxWidth && tempObject.getY() >= minHeight && tempObject.getY() <= maxHeight || tempObject.getId() == ID.Player){
        tempObject.tick();
      }
    }
  }

  public void render(Graphics g){
    for(int i = 0; i < object.size(); i++) {
      GameObject tempObject = object.get(i);
      int minWidth = Game.getPlayerX() - Game.WIDTH/2 - Game.SQUARE;
      int maxWidth = Game.getPlayerX() + Game.WIDTH/2 + Game.SQUARE;
      int minHeight = Game.getPlayerY() - Game.HEIGHT/2 - Game.SQUARE;
      int maxHeight = Game.getPlayerY() + Game.HEIGHT/2 + Game.SQUARE;
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
