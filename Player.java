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
    int newY = Game.clamp(y, 0, Game.HEIGHT-Game.SQUARE*2-Game.SQUARE/2);
    int newX = Game.clamp(x, 0, Game.WIDTH-Game.SQUARE);
    if (newY == Game.HEIGHT-Game.SQUARE*2-Game.SQUARE/2) {
      Game.setTopRow(Game.getTopRow()+29);
      newY = 1;
    }
    else if (newY == 0) {
      Game.setTopRow(Game.getTopRow()-29);
      newY = Game.HEIGHT-Game.SQUARE*2-Game.SQUARE/2 - 1;
    }
    else if (newX == Game.WIDTH-Game.SQUARE) {
      Game.setLeftCol(Game.getLeftCol()+40);
      newX = 1;
    }
    else if (newX == 0) {
      Game.setLeftCol(Game.getLeftCol()-40);
      newX = Game.WIDTH-Game.SQUARE - 1;
    }
    y = newY;
    x = newX;
    collision();
  }

  private void collision(){
    for(int i = 0; i < handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);
      int minWidth = Game.getLeftCol()*Game.SQUARE;
      int maxWidth = Game.getLeftCol()*Game.SQUARE + Game.WIDTH - Game.SQUARE;
      int minHeight = Game.getTopRow()*Game.SQUARE;
      int maxHeight = Game.getTopRow()*Game.SQUARE + Game.HEIGHT - Game.SQUARE;
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
        if(tempObject.getId() == ID.Obstacle){
          if(getBounds().intersects(tempObject.getBounds())){
            System.out.println("collision");
            if (Math.abs((y - velY) - tempObject.getY()) < Game.SQUARE){
              x -= velX;
              velX = 0;
            }
            else if (Math.abs((x-velX) - tempObject.getX()) < Game.SQUARE){
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
    g.setColor(Color.white);
    g.fillRect(x, y, Game.SQUARE, Game.SQUARE);
  }
}
