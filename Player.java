import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.util.ArrayList;

public class Player extends GameObject {

  Handler handler;
  private boolean intersecting = false;
  public String equip_shield = null;
  public String equip_sword = null;
  public String equip_special = null;
  public boolean opening = false;
  public ArrayList<Item> inventory;
  public ArrayList<BufferedImage> front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> right_images= new ArrayList<BufferedImage>();
  public static String direction = "Down";
  public static int walk = 0;
  public static int img_index = 0;

  public Player(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    this.inventory = new ArrayList<Item>();
    for (int i = 0; i < 4; i++){
      try{
        front_images.add(ImageIO.read(new File("Images/p1/p1_equip_none/p1_front_"+i+".png")));
      }
      catch(IOException ex){
      }
    }
    for (int i = 0; i < 4; i++){
      try{
        back_images.add(ImageIO.read(new File("Images/p1/p1_equip_none/p1_back_"+i+".png")));
      }
      catch(IOException ex){
      }
    }
    for (int i = 0; i < 4; i++){
      try{
        left_images.add(ImageIO.read(new File("Images/p1/p1_equip_none/p1_left_"+i+".png")));
      }
      catch(IOException ex){
      }
    }
    for (int i = 0; i < 4; i++){
      try{
        right_images.add(ImageIO.read(new File("Images/p1/p1_equip_none/p1_right_"+i+".png")));
      }
      catch(IOException ex){
      }
    }
  }

  public void equipShield(String item){
    for(int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).name.equals(item)){
        this.equip_shield = item;
      }
    }
  }

  public void unequipShield(){
    this.equip_shield = null;
  }

  public void equipSword(String item){
    for(int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).name.equals(item)){
        this.equip_sword = item;
      }
    }
  }

  public void unequipSword(){
    this.equip_sword = null;
  }

  public void equipSpecial(String item){
    for(int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).name.equals(item)){
        this.equip_special = item;
      }
    }
  }

  public void unequipSpecial(){
    this.equip_special = null;
  }

  public void addItem(Item item){
    this.inventory.add(item);
  }

  public ArrayList<Item> getInventory(){
    return inventory;
  }

  public boolean getOpening(){
    return opening;
  }

  public void setOpening(boolean opening){
    this.opening = opening;
  }

  public void setImg(String direction){
    this.direction = direction;
  }

  public Rectangle getBounds(){
    return new Rectangle(x,y,20,Game.SQUARE);
  }

  public void tick() {
    x += velX;
    y += velY;
    collision();
    Game.setPlayerX(x);
    Game.setPlayerY(y);
    if (KeyInput.checkWalking()) {
      // Still
      if (walk == 6){
        img_index++;
        walk = 0;
      }
      if (img_index == 4){
        img_index = 0;
      }
      walk++;
    }
    else {
      img_index = 0;
    }
  }

  public NPC checkTalking(){
    ArrayList<NPC> npcs = Game.getNPCS();
    for(int i = 0; i < npcs.size(); i++) {
      NPC tempNPC = npcs.get(i);
      if (getBounds().intersects(tempNPC.getSpeakBounds())){
        return tempNPC;
      }
    }
    return null;
  }

  public Chest checkOpening(){
    ArrayList<Chest> chests = Game.getChests();
    for(int i = 0; i < chests.size(); i++) {
      Chest tempChest = chests.get(i);
      if (getBounds().intersects(tempChest.getOpenBounds())){
        return tempChest;
      }
    }
    return null;
  }

  private void collision(){
    int startY = (y/Game.SQUARE - Game.SCREENROWS/2);
    int endY = (y/Game.SQUARE + Game.SCREENROWS/2+2);
    if (startY < 0) startY = 0;
    if (endY > handler.object.size()) endY = handler.object.size();
    for(int i = startY; i < endY; i++) {
      ArrayList<GameObject> tempRow = handler.object.get(i);
      ArrayList<Chest> tempChests = handler.chests.get(i);
      ArrayList<NPC> tempNPCS = handler.npcs.get(i);
      int startX = (x/Game.SQUARE - Game.SCREENCOLS/2);
      int endX = (x/Game.SQUARE + Game.SCREENCOLS/2+2);
      if (startX < 0) startX = 0;
      if (endX > tempRow.size()) endX = tempRow.size();
      for(int j = startX; j < endX; j++){
        GameObject tempObject = tempRow.get(j);
        Chest tempChest = tempChests.get(j);
        NPC tempNPC = tempNPCS.get(j);
        if (tempObject != null){
          if(tempObject.getId() == ID.BasicEnemy){
            if(getBounds().intersects(tempObject.getBounds())){
              if(!intersecting){
                intersecting = true;
                HUD.HEALTH -= 2;
              }
            }
            else {
              intersecting = false;
            }
          }
          else if(tempObject.getId() == ID.Obstacle){
            if(getBounds().intersects(tempObject.getBounds())){
              if (Math.abs((y - velY) - tempObject.getY()%Game.HEIGHT) < Game.SQUARE){
                x -= velX;
                velX = 0;
              }
              else if (Math.abs((x-velX) - tempObject.getX()%Game.WIDTH) < Game.SQUARE){
                y -= velY;
                velY = 0;
              }
              else {
                x -= velX;
                y -= velY;
                velX = 0;
                velY = 0;
              }
            }
          }
        }
        if (tempNPC != null){
          if(tempNPC.getId() == ID.NPC){
            if(getBounds().intersects(tempNPC.getBounds())){
              if (Math.abs((y - velY) - tempNPC.getY()%Game.HEIGHT) < Game.SQUARE){
                x -= velX;
                velX = 0;
              }
              else if (Math.abs((x-velX) - tempNPC.getX()%Game.WIDTH) < Game.SQUARE){
                y -= velY;
                velY = 0;
              }
              else {
                x -= velX;
                y -= velY;
                velX = 0;
                velY = 0;
              }
            }
          }
        }
        if (tempChest != null){
          if(getBounds().intersects(tempChest.getBounds())){
            if (Math.abs((y - velY) - tempChest.getY()%Game.HEIGHT) < Game.SQUARE/2){
              x -= velX;
              velX = 0;
            }
            else if (Math.abs((x-velX) - tempChest.getX()%Game.WIDTH) < Game.SQUARE){
              y -= velY;
              velY = 0;
            }
            else {
              x -= velX;
              y -= velY;
              velX = 0;
              velY = 0;
            }
          }
        }
      }
    }
  }

  public void render(Graphics g) {
    if (direction.equals("Up")){
      g.drawImage(this.back_images.get(img_index),Game.STARTX,Game.STARTY,null);
    }
    else if (direction.equals("Down")){
      g.drawImage(this.front_images.get(img_index),Game.STARTX,Game.STARTY,null);
    }
    else if (direction.equals("Left")){
      g.drawImage(this.left_images.get(img_index),Game.STARTX,Game.STARTY,null);
    }
    else {
      g.drawImage(this.right_images.get(img_index),Game.STARTX,Game.STARTY,null);
    }
    g.setColor(new Color(70, 70, 70, 100));
    g.fillRect(Game.STARTX+6,Game.STARTY+27,10,1);
    g.fillRect(Game.STARTX+4,Game.STARTY+28,13,1);
    g.fillRect(Game.STARTX+4,Game.STARTY+29,13,1);
    g.fillRect(Game.STARTX+6,Game.STARTY+30,10,1);
  }
}
