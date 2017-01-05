import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class KeyInput extends KeyAdapter {

  private Handler handler;
  public static NPC lastNPC = null;
  private static boolean alreadyTalking = false;

  public KeyInput(Handler handler){
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    // Go Up
    if(key == 38 && !alreadyTalking){
      Game.getPlayer().setVelY(-2);
      if(lastNPC != null && Game.getPlayer().checkTalking() == null){
        handler.removeObject(lastNPC.bubble);
        lastNPC = null;
      }
    }
    // Go Down
    else if(key == 40 && !alreadyTalking){
      Game.getPlayer().setVelY(2);
      if(lastNPC != null && Game.getPlayer().checkTalking() == null){
        handler.removeObject(lastNPC.bubble);
        lastNPC = null;
      }
    }
    // Go Left
    else if(key == 37 && !alreadyTalking){
      Game.getPlayer().setVelX(-2);
      if(lastNPC != null && Game.getPlayer().checkTalking() == null){
        handler.removeObject(lastNPC.bubble);
        lastNPC = null;
      }
    }
    // Go Right
    else if(key == 39 && !alreadyTalking){
      Game.getPlayer().setVelX(2);
      if(lastNPC != null && Game.getPlayer().checkTalking() == null){
        handler.removeObject(lastNPC.bubble);
        lastNPC = null;
      }
    }


    else if (key == KeyEvent.VK_ENTER) {
      NPC npc = Game.getPlayer().checkTalking();
      if (npc != null){
        if (alreadyTalking) {
          int startAgain = npc.bubble.nextPhrase();
          if (startAgain == 0) {
            alreadyTalking = false;
          }
        }
        else {
          alreadyTalking = true;
          handler.addObject(npc.bubble);
          lastNPC = npc;
        }
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
