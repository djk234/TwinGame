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
  public ArrayList<BufferedImage> front_images;
  public ArrayList<BufferedImage> back_images;
  public ArrayList<BufferedImage> left_images;
  public ArrayList<BufferedImage> right_images;
  //None
  public ArrayList<BufferedImage> no_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> no_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> no_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> no_right_images= new ArrayList<BufferedImage>();
  //NS
  public ArrayList<BufferedImage> ns_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_right_images= new ArrayList<BufferedImage>();
  //WS
  public ArrayList<BufferedImage> ws_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_right_images= new ArrayList<BufferedImage>();
  //EL
  public ArrayList<BufferedImage> el_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_right_images= new ArrayList<BufferedImage>();
  //RS
  public ArrayList<BufferedImage> rs_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> rs_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> rs_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> rs_right_images= new ArrayList<BufferedImage>();
  //SS
  public ArrayList<BufferedImage> ss_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ss_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ss_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ss_right_images= new ArrayList<BufferedImage>();
  //CS
  public ArrayList<BufferedImage> cs_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> cs_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> cs_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> cs_right_images= new ArrayList<BufferedImage>();
  //NS_RS
  public ArrayList<BufferedImage> ns_rs_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_rs_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_rs_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_rs_right_images= new ArrayList<BufferedImage>();
  //NS_SS
  public ArrayList<BufferedImage> ns_ss_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_ss_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_ss_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_ss_right_images= new ArrayList<BufferedImage>();
  //NS_CS
  public ArrayList<BufferedImage> ns_cs_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_cs_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_cs_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ns_cs_right_images= new ArrayList<BufferedImage>();
  //WS_RS
  public ArrayList<BufferedImage> ws_rs_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_rs_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_rs_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_rs_right_images= new ArrayList<BufferedImage>();
  //WS_SS
  public ArrayList<BufferedImage> ws_ss_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_ss_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_ss_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_ss_right_images= new ArrayList<BufferedImage>();
  //WS_CS
  public ArrayList<BufferedImage> ws_cs_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_cs_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_cs_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> ws_cs_right_images= new ArrayList<BufferedImage>();
  //EL_RS
  public ArrayList<BufferedImage> el_rs_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_rs_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_rs_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_rs_right_images= new ArrayList<BufferedImage>();
  //EL_SS
  public ArrayList<BufferedImage> el_ss_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_ss_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_ss_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_ss_right_images= new ArrayList<BufferedImage>();
  //EL_CS
  public ArrayList<BufferedImage> el_cs_front_images = new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_cs_back_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_cs_left_images= new ArrayList<BufferedImage>();
  public ArrayList<BufferedImage> el_cs_right_images= new ArrayList<BufferedImage>();


  public static String direction = "Down";
  public static int walk = 0;
  public static int img_index = 0;

  public Player(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    this.inventory = new ArrayList<Item>();
    //None
    addImages("p1_equip_none/p1_front_",no_front_images);
    addImages("p1_equip_none/p1_back_",no_back_images);
    addImages("p1_equip_none/p1_left_",no_left_images);
    addImages("p1_equip_none/p1_right_",no_right_images);
    front_images = no_front_images;
    back_images = no_back_images;
    left_images = no_left_images;
    right_images = no_right_images;
    //ns
    String ext = "ns";
    addImages("p1_"+ext+"/p1_front_",ns_front_images);
    addImages("p1_"+ext+"/p1_back_",ns_back_images);
    addImages("p1_"+ext+"/p1_left_",ns_left_images);
    addImages("p1_"+ext+"/p1_right_",ns_right_images);
    //ws
    ext = "ws";
    addImages("p1_"+ext+"/p1_front_",ws_front_images);
    addImages("p1_"+ext+"/p1_back_",ws_back_images);
    addImages("p1_"+ext+"/p1_left_",ws_left_images);
    addImages("p1_"+ext+"/p1_right_",ws_right_images);
    //el
    ext = "el";
    addImages("p1_"+ext+"/p1_front_",el_front_images);
    addImages("p1_"+ext+"/p1_back_",el_back_images);
    addImages("p1_"+ext+"/p1_left_",el_left_images);
    addImages("p1_"+ext+"/p1_right_",el_right_images);
    //rs
    ext = "rs";
    addImages("p1_"+ext+"/p1_front_",rs_front_images);
    addImages("p1_"+ext+"/p1_back_",rs_back_images);
    addImages("p1_"+ext+"/p1_left_",rs_left_images);
    addImages("p1_"+ext+"/p1_right_",rs_right_images);
    //ss
    ext = "ss";
    addImages("p1_"+ext+"/p1_front_",ss_front_images);
    addImages("p1_"+ext+"/p1_back_",ss_back_images);
    addImages("p1_"+ext+"/p1_left_",ss_left_images);
    addImages("p1_"+ext+"/p1_right_",ss_right_images);
    //cs
    ext = "cs";
    addImages("p1_"+ext+"/p1_front_",cs_front_images);
    addImages("p1_"+ext+"/p1_back_",cs_back_images);
    addImages("p1_"+ext+"/p1_left_",cs_left_images);
    addImages("p1_"+ext+"/p1_right_",cs_right_images);
    //ns_rs
    ext = "ns_rs";
    addImages("p1_"+ext+"/p1_front_",ns_rs_front_images);
    addImages("p1_"+ext+"/p1_back_",ns_rs_back_images);
    addImages("p1_"+ext+"/p1_left_",ns_rs_left_images);
    addImages("p1_"+ext+"/p1_right_",ns_rs_right_images);
    //ns_ss
    ext = "ns_ss";
    addImages("p1_"+ext+"/p1_front_",ns_ss_front_images);
    addImages("p1_"+ext+"/p1_back_",ns_ss_back_images);
    addImages("p1_"+ext+"/p1_left_",ns_ss_left_images);
    addImages("p1_"+ext+"/p1_right_",ns_ss_right_images);
    //ns_cs
    ext = "ns_cs";
    addImages("p1_"+ext+"/p1_front_",ns_cs_front_images);
    addImages("p1_"+ext+"/p1_back_",ns_cs_back_images);
    addImages("p1_"+ext+"/p1_left_",ns_cs_left_images);
    addImages("p1_"+ext+"/p1_right_",ns_cs_right_images);
    //ws_rs
    ext = "ws_rs";
    addImages("p1_"+ext+"/p1_front_",ws_rs_front_images);
    addImages("p1_"+ext+"/p1_back_",ws_rs_back_images);
    addImages("p1_"+ext+"/p1_left_",ws_rs_left_images);
    addImages("p1_"+ext+"/p1_right_",ws_rs_right_images);
    //ws_ss
    ext = "ws_ss";
    addImages("p1_"+ext+"/p1_front_",ws_ss_front_images);
    addImages("p1_"+ext+"/p1_back_",ws_ss_back_images);
    addImages("p1_"+ext+"/p1_left_",ws_ss_left_images);
    addImages("p1_"+ext+"/p1_right_",ws_ss_right_images);
    //ws_cs
    ext = "ws_cs";
    addImages("p1_"+ext+"/p1_front_",ws_cs_front_images);
    addImages("p1_"+ext+"/p1_back_",ws_cs_back_images);
    addImages("p1_"+ext+"/p1_left_",ws_cs_left_images);
    addImages("p1_"+ext+"/p1_right_",ws_cs_right_images);
    //el_rs
    ext = "el_rs";
    addImages("p1_"+ext+"/p1_front_",el_rs_front_images);
    addImages("p1_"+ext+"/p1_back_",el_rs_back_images);
    addImages("p1_"+ext+"/p1_left_",el_rs_left_images);
    addImages("p1_"+ext+"/p1_right_",el_rs_right_images);
    //el_ss
    ext = "el_ss";
    addImages("p1_"+ext+"/p1_front_",el_ss_front_images);
    addImages("p1_"+ext+"/p1_back_",el_ss_back_images);
    addImages("p1_"+ext+"/p1_left_",el_ss_left_images);
    addImages("p1_"+ext+"/p1_right_",el_ss_right_images);
    //el_cs
    ext = "el_cs";
    addImages("p1_"+ext+"/p1_front_",el_cs_front_images);
    addImages("p1_"+ext+"/p1_back_",el_cs_back_images);
    addImages("p1_"+ext+"/p1_left_",el_cs_left_images);
    addImages("p1_"+ext+"/p1_right_",el_cs_right_images);
  }

  public void addImages(String path, ArrayList<BufferedImage> images){
    for (int i = 0; i < 4; i++){
      try{
        images.add(ImageIO.read(new File("Images/p1/"+path+i+".png")));
      }
      catch(IOException ex){
      }
    }
  }
  public void equipShield(String item){
    for(int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).name.equals(item)){
        this.equip_shield = item;
        if (item.equals("RoundShield")){
          front_images = rs_front_images;
          if (this.equip_sword != null){
            if (this.equip_sword.equals("NoviceSword")){
              front_images = ns_rs_front_images;
            }
            else if (this.equip_sword.equals("WhiteSteelSword")){
              front_images = ws_rs_front_images;
            }
            else if (this.equip_sword.equals("SwordOfEternalLight")){
              front_images = el_rs_front_images;
            }
          }
        }
        else if (item.equals("StarShield")){
          front_images = ss_front_images;
          if (this.equip_sword != null){
            if (this.equip_sword.equals("NoviceSword")){
              front_images = ns_ss_front_images;
            }
            else if (this.equip_sword.equals("WhiteSteelSword")){
              front_images = ws_ss_front_images;
            }
            else if (this.equip_sword.equals("SwordOfEternalLight")){
              front_images = el_ss_front_images;
            }
          }
        }
        else if (item.equals("ClearGlassShield")){
          front_images = cs_front_images;
          if (this.equip_sword != null){
            if (this.equip_sword.equals("NoviceSword")){
              front_images = ns_cs_front_images;
            }
            else if (this.equip_sword.equals("WhiteSteelSword")){
              front_images = ws_cs_front_images;
            }
            else if (this.equip_sword.equals("SwordOfEternalLight")){
              front_images = el_cs_front_images;
            }
          }
        }
      }
    }
  }

  public void unequipShield(){
    this.equip_shield = null;
    front_images = no_front_images;
  }

  public void equipSword(String item){
    for(int i = 0; i < inventory.size(); i++) {
      if (inventory.get(i).name.equals(item)){
        this.equip_sword = item;
        if (item.equals("NoviceSword")){
          front_images = ns_front_images;
          if (this.equip_shield != null){
            if (this.equip_shield.equals("RoundShield")){
              front_images = ns_rs_front_images;
            }
            else if (this.equip_shield.equals("StarShield")){
              front_images = ns_ss_front_images;
            }
            else if (this.equip_shield.equals("ClearGlassShield")){
              front_images = ns_cs_front_images;
            }
          }
        }
        else if (item.equals("WhiteSteelSword")){
          front_images = ws_front_images;
          if (this.equip_shield != null){
            if (this.equip_shield.equals("RoundShield")){
              front_images = ws_rs_front_images;
            }
            else if (this.equip_shield.equals("StarShield")){
              front_images = ws_ss_front_images;
            }
            else if (this.equip_shield.equals("ClearGlassShield")){
              front_images = ws_cs_front_images;
            }
          }
        }
        else if (item.equals("SwordOfEternalLight")){
          front_images = el_front_images;
          if (this.equip_shield != null){
            if (this.equip_shield.equals("RoundShield")){
              front_images = el_rs_front_images;
            }
            else if (this.equip_shield.equals("StarShield")){
              front_images = el_ss_front_images;
            }
            else if (this.equip_shield.equals("ClearGlassShield")){
              front_images = el_cs_front_images;
            }
          }
        }
      }
    }
  }

  public void unequipSword(){
    this.equip_sword = null;
    front_images = no_front_images;
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
