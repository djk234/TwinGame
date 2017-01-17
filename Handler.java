import java.util.ArrayList;
import java.util.LinkedList;
import java.awt.Graphics;

public class Handler {

  ArrayList<ArrayList<GameObject>> object = new ArrayList<ArrayList<GameObject>>();
  ArrayList<ArrayList<Chest>> chests = new ArrayList<ArrayList<Chest>>();
  ArrayList<ArrayList<NPC>> npcs = new ArrayList<ArrayList<NPC>>();
  WordBubble bubble = null;
  LinkedList<Item> item = new LinkedList<Item>();

  public void tick(){
    if (Game.getState() == State.Play){
      int startY = (Game.getPlayerY()/Game.SQUARE - Game.SCREENROWS/2);
      int endY = (Game.getPlayerY()/Game.SQUARE + Game.SCREENROWS/2+2);
      if (startY < 0) startY = 0;
      if (endY > object.size()) endY = object.size();
      for(int i = startY; i < endY; i++) {
        ArrayList<GameObject> tempRow = object.get(i);
        ArrayList<Chest> tempChests = chests.get(i);
        ArrayList<NPC> tempNPCS = npcs.get(i);
        int startX = (Game.getPlayerX()/Game.SQUARE - Game.SCREENCOLS/2);
        int endX = (Game.getPlayerX()/Game.SQUARE + Game.SCREENCOLS/2+2);
        if (startX < 0) startX = 0;
        if (endX > tempRow.size()) endX = tempRow.size();
        for(int j = startX; j < endX; j++){
          GameObject tempObject = tempRow.get(j);
          Chest tempChest = tempChests.get(j);
          NPC tempNPC = tempNPCS.get(j);
          if (tempObject != null){
            tempObject.tick();
          }
          if (tempChest != null) {
            tempChest.tick();
          }
          else if (tempNPC != null) {
            tempNPC.tick();
          }
        }
      }
      for(int j = 0; j < item.size(); j++){
        Item tempItem = item.get(j);
        tempItem.tick();
      }
      Game.player.tick();
      if (bubble != null) {
        bubble.tick();
      }
    }
    else {
      Game.getPause().tick();
    }
  }

  public void render(Graphics g){
    if (Game.getState() == State.Play){
      int startY = (Game.getPlayerY()/Game.SQUARE - Game.SCREENROWS/2);
      int endY = (Game.getPlayerY()/Game.SQUARE + Game.SCREENROWS/2+2);
      if (startY < 0) startY = 0;
      if (endY > object.size()) endY = object.size();
      for(int i = startY; i < endY; i++) {
        ArrayList<GameObject> tempRow = object.get(i);
        ArrayList<Chest> tempChests = chests.get(i);
        ArrayList<NPC> tempNPCS = npcs.get(i);
        int startX = (Game.getPlayerX()/Game.SQUARE - Game.SCREENCOLS/2);
        int endX = (Game.getPlayerX()/Game.SQUARE + Game.SCREENCOLS/2+2);
        if (startX < 0) startX = 0;
        if (endX > tempRow.size()) endX = tempRow.size();
        for(int j = startX; j < endX; j++){
          GameObject tempObject = tempRow.get(j);
          Chest tempChest = tempChests.get(j);
          NPC tempNPC = tempNPCS.get(j);
          if (tempObject != null){
            tempObject.render(g);
          }
          if (tempChest != null) {
            tempChest.render(g);
          }
          else if (tempNPC != null) {
            tempNPC.render(g);
          }
        }
      }
      for(int j = 0; j < item.size(); j++){
        Item tempItem = item.get(j);
        tempItem.render(g);
      }
      Game.player.render(g);
      if (bubble != null) {
        bubble.render(g);
      }
    }
    else {
      Game.getPause().render(g);
    }
  }
/*
  public void addObject(GameObject object, int r, int c){
    this.object.get(r).add();
  }

  public void removeObject(GameObject object, int r, int c){
    this.object.remove(object);
  }*/

  public void addBubble(WordBubble bubble){
    this.bubble = bubble;
  }

  public void removeBubble(){
    this.bubble = null;
  }

  public void addItem(Item item){
    this.item.add(item);
  }

  public void removeItem(Item item){
    this.item.remove(item);
  }
}
