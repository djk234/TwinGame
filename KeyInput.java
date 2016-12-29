import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {

  private Handler handler;

  public KeyInput(Handler handler){
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if(tempObject.getId() == ID.Player){
        System.out.println(key);
        // Go Up
        if(key == 38) tempObject.setY(tempObject.getY() - 4);
        // Go Down
        else if(key == 40) tempObject.setY(tempObject.getY() + 4);
        // Go Left
        else if(key == 37) tempObject.setX(tempObject.getX() - 4);
        // Go Right
        else if(key == 39) tempObject.setX(tempObject.getX() + 4);

      }
    }
  }

  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();

  }
}
