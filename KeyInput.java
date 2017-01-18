import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.*;

public class KeyInput extends KeyAdapter {

  private Handler handler;
  public static NPC lastNPC = null;
  private static boolean alreadyTalking = false;
  public static boolean walking = false;

  public KeyInput(Handler handler){
    this.handler = handler;
  }

  public void keyPressed(KeyEvent e){
    int key = e.getKeyCode();
    State state = Game.getState();
    boolean opening = Game.getPlayer().getOpening();
    // Go Up
    if(key == 38 && !alreadyTalking && (state == State.Play) && !opening){
      this.walking = true;
      Game.getPlayer().setVelY(-2);
      Game.getPlayer().setImg("Up");
      if(lastNPC != null && Game.getPlayer().checkTalking() == null){
        handler.removeBubble();
        lastNPC = null;
      }
    }
    // Go Down
    else if(key == 40 && !alreadyTalking && (state == State.Play) && !opening){
      this.walking = true;
      Game.getPlayer().setVelY(2);
      Game.getPlayer().setImg("Down");
      if(lastNPC != null && Game.getPlayer().checkTalking() == null){
        handler.removeBubble();
        lastNPC = null;
      }
    }
    // Go Left
    else if(key == 37 && !alreadyTalking && (state == State.Play) && !opening){
      this.walking = true;
      Game.getPlayer().setVelX(-2);
      Game.getPlayer().setImg("Left");
      if(lastNPC != null && Game.getPlayer().checkTalking() == null){
        handler.removeBubble();
        lastNPC = null;
      }
    }

    else if(key == 37 && state == State.Pause){
      Pause.decSelectedItem();
    }

    // Go Right
    else if(key == 39 && !alreadyTalking && (state == State.Play) && !opening){
      this.walking = true;
      Game.getPlayer().setVelX(2);
      Game.getPlayer().setImg("Right");
      if(lastNPC != null && Game.getPlayer().checkTalking() == null){
        handler.removeBubble();
        lastNPC = null;
      }
    }

    else if(key == 39 && state == State.Pause){
      Pause.incSelectedItem();
    }

    // Pause
    else if(key == KeyEvent.VK_P){
      if (state == State.Play){
        Game.setState(State.Pause);
      }
      else {
        Game.setState(State.Play);
      }
    }

    else if (key == KeyEvent.VK_ENTER && (state == State.Play)) {
      this.walking = false;
      NPC npc = Game.getPlayer().checkTalking();
      Chest chest = Game.getPlayer().checkOpening();
      if (npc != null){
        if (alreadyTalking) {
          int startAgain = npc.bubble.nextPhrase();
          if (startAgain == 0) {
            alreadyTalking = false;
          }
        }
        else {
          alreadyTalking = true;
          handler.addBubble(npc.bubble);
          lastNPC = npc;
        }
      }
      if (chest != null && !chest.getOpened() && !opening){
        Game.getPlayer().setOpening(true);
        chest.startOpening();
      }
      else if (chest != null && chest.getOpened() && !chest.getItem().getShown()){
        int startAgain = chest.getItem().bubble.nextPhrase();
        if (startAgain == 0) {
          Game.getPlayer().addItem(chest.getItem());
          chest.getItem().stopShowing();
          Game.getPlayer().setOpening(false);
        }

      }
    }

    else if (key == KeyEvent.VK_ENTER && (state == State.Pause)) {
      if (Game.pause.page == 0){
        if (Game.pause.pageItem == 0){
          if (Game.getPlayer().equip_sword == null){
            Game.getPlayer().equipSword("NoviceSword");
          }
          else if (Game.getPlayer().equip_sword.equals("NoviceSword")){
            Game.getPlayer().unequipSword();
          }
        }
        else if (Game.pause.pageItem == 1){
          if (Game.getPlayer().equip_sword == null){
            Game.getPlayer().equipSword("WhiteSteelSword");
          }
          else if (Game.getPlayer().equip_sword.equals("WhiteSteelSword")){
            Game.getPlayer().unequipSword();
          }
        }
        else if (Game.pause.pageItem == 2){
          if (Game.getPlayer().equip_sword == null){
            Game.getPlayer().equipSword("SwordOfEternalLight");
          }
          else if (Game.getPlayer().equip_sword.equals("SwordOfEternalLight")){
            Game.getPlayer().unequipSword();
          }
        }
      }
      else if (Game.pause.page == 1){
        if (Game.pause.pageItem == 0){
          if (Game.getPlayer().equip_shield == null){
            Game.getPlayer().equipShield("RoundShield");
          }
          else if (Game.getPlayer().equip_sword.equals("RoundShield")){
            Game.getPlayer().unequipShield();
          }
        }
        else if (Game.pause.pageItem == 1){
          if (Game.getPlayer().equip_shield == null){
            Game.getPlayer().equipShield("StarShield");
          }
          else if (Game.getPlayer().equip_sword.equals("StarShield")){
            Game.getPlayer().unequipShield();
          }
        }
        else if (Game.pause.pageItem == 2){
          if (Game.getPlayer().equip_shield == null){
            Game.getPlayer().equipShield("ClearGlassShield");
          }
          else if (Game.getPlayer().equip_sword.equals("ClearGlassShield")){
            Game.getPlayer().unequipShield();
          }
        }
      }
      else if (Game.pause.page == 2){

      }
    }
    if(key == KeyEvent.VK_ESCAPE) System.exit(1);
  }

  public static boolean checkWalking(){
    return walking;
  }

  public void keyReleased(KeyEvent e){
    int key = e.getKeyCode();
    // Go Up
    if(key == 38) {
      this.walking = false;
      Game.getPlayer().setVelY(0);
    }
    // Go Down
    else if(key == 40) {
      this.walking = false;
      Game.getPlayer().setVelY(0);
    }
    // Go Left
    else if(key == 37) {
      this.walking = false;
      Game.getPlayer().setVelX(0);
    }
    // Go Right
    else if(key == 39) {
      this.walking = false;
      Game.getPlayer().setVelX(0);
    }
  }
}
