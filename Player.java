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
  public boolean opening = false;
  public ArrayList<Item> inventory;
  public static BufferedImage img;
  public static BufferedImage img_walk_still;
  public static BufferedImage img_walk_left;
  public static BufferedImage img_walk_right;
  public static int walk = 0;

  public Player(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    this.inventory = new ArrayList<Item>();
    try{
      this.img = ImageIO.read(new File("Images/p1/p1_front_still.png"));
      this.img_walk_still = this.img;
    }
    catch(IOException ex){
    }
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
    if (direction.equals("Up")){
      try{
        this.img = ImageIO.read(new File("Images/p1/p1_back_still.png"));
        this.img_walk_still = this.img;
        this.img_walk_left = ImageIO.read(new File("Images/p1/p1_back_left.png"));
        this.img_walk_right = ImageIO.read(new File("Images/p1/p1_back_right.png"));
      }
      catch(IOException ex){
      }
    }
    else if (direction.equals("Down")){
      try{
        this.img = ImageIO.read(new File("Images/p1/p1_front_still.png"));
        this.img_walk_still = this.img;
        this.img_walk_left = ImageIO.read(new File("Images/p1/p1_front_left.png"));
        this.img_walk_right = ImageIO.read(new File("Images/p1/p1_front_right.png"));
      }
      catch(IOException ex){
      }
    }
    else if (direction.equals("Right")){
      try{
        this.img = ImageIO.read(new File("Images/p1/p1_right_still.png"));
        this.img_walk_still = this.img;
        this.img_walk_left = ImageIO.read(new File("Images/p1/p1_right_left.png"));
        this.img_walk_right = ImageIO.read(new File("Images/p1/p1_right_right.png"));
      }
      catch(IOException ex){
      }
    }
    else if (direction.equals("Left")){
      try{
        this.img = ImageIO.read(new File("Images/p1/p1_left_still.png"));
        this.img_walk_still = this.img;
        this.img_walk_left = ImageIO.read(new File("Images/p1/p1_left_left.png"));
        this.img_walk_right = ImageIO.read(new File("Images/p1/p1_left_right.png"));
      }
      catch(IOException ex){
      }
    }
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
      if ((walk%24 >= 0 && walk%24 < 6) || (walk%24 >= 12 && walk%24 < 18)){
        this.img = this.img_walk_still;
      }
      else if (walk%24 >= 6 && walk%24 < 12) {
        this.img = this.img_walk_left;
      }
      else if (walk%24 >= 18 && walk%24 <= 23) {
        this.img = this.img_walk_right;
      }
    }
    else {
      this.img = this.img_walk_still;
    }
    walk++;
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
    g.drawImage(this.img,Game.STARTX,Game.STARTY,null);
    g.setColor(new Color(70, 70, 70, 100));
    g.fillRect(Game.STARTX+6,Game.STARTY+27,10,1);
    g.fillRect(Game.STARTX+4,Game.STARTY+28,13,1);
    g.fillRect(Game.STARTX+4,Game.STARTY+29,13,1);
    g.fillRect(Game.STARTX+6,Game.STARTY+30,10,1);
  }
}
