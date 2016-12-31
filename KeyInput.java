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
        // Go Up
        if(key == 38)
          tempObject.setVelY(-2);
        // Go Down
        else if(key == 40)
          tempObject.setVelY(2);
        // Go Left
        else if(key == 37)
          tempObject.setVelX(-2);
        // Go Right
        else if(key == 39)
          tempObject.setVelX(2);
      }
    }
    if(key == KeyEvent.VK_ESCAPE) System.exit(1);
  }

  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    for(int i = 0; i < handler.object.size(); i++) {
      GameObject tempObject = handler.object.get(i);

      if(tempObject.getId() == ID.Player){
        // Go Up
        if(key == 38) tempObject.setVelY(0);
        // Go Down
        else if(key == 40) tempObject.setVelY(0);
        // Go Left
        else if(key == 37) tempObject.setVelX(0);
        // Go Right
        else if(key == 39) tempObject.setVelX(0);
      }
    }
  }
}
