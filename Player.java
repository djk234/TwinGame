import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.Rectangle;

public class Player extends GameObject {

  Handler handler;
  private boolean intersecting = false;

  public Player(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
  }

  public Rectangle getBounds(){
    return new Rectangle(x,y,Game.SQUARE,Game.SQUARE);
  }

  public void tick() {
    x += velX;
    y += velY;
    collision();
    Game.setPlayerX(x);
    Game.setPlayerY(y);
  }

  private void collision(){
    for(int i = 0; i < handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);
      int minWidth = Game.getPlayerX() - Game.WIDTH/2 - Game.SQUARE;
      int maxWidth = Game.getPlayerX() + Game.WIDTH/2 + Game.SQUARE;
      int minHeight = Game.getPlayerY() - Game.HEIGHT/2 - Game.SQUARE;
      int maxHeight = Game.getPlayerY() + Game.HEIGHT/2 + Game.SQUARE;
      if (tempObject.getX() >= minWidth && tempObject.getX() < maxWidth && tempObject.getY() >= minHeight && tempObject.getY() < maxHeight){
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
            System.out.println(tempObject);
            if (Math.abs((y - velY) - tempObject.getY()) < Game.SQUARE){
              x -= velX;
              velX = 0;
            }
            else if (Math.abs((x-velX) - tempObject.getX()) < Game.SQUARE){
              y -= velY;
              velY = 0;
            }
            else {
              System.out.println("Both");
              x -= velX;
              y -= velY;
              velX = 0;
              velY = 0;
            }
          }
        }/*
        else if(tempObject.getId() == ID.NPC){
          if(getBounds().intersects(tempObject.getBounds())){
            System.out.println(tempObject);
            if (Math.abs((y - velY) - tempObject.getY()%Game.HEIGHT) < Game.SQUARE){
              x -= velX;
              velX = 0;
            }
            else if (Math.abs((x-velX) - tempObject.getX()%Game.WIDTH) < Game.SQUARE){
              y -= velY;
              velY = 0;
            }
            else {
              System.out.println("Both");
              x -= velX;
              y -= velY;
              velX = 0;
              velY = 0;
            }
          }
        }*/
      }
    }
  }

  public void render(Graphics g) {
    // Front Facing Still
    // Skin Color
    g.setColor(Color.white);
    g.fillRect(Game.STARTX,Game.STARTY,Game.SQUARE,Game.SQUARE);
  }
}
