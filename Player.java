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

    x = Game.clamp(x, 0, Game.WIDTH-Game.SQUARE);
    y = Game.clamp(y, 0, Game.HEIGHT-Game.SQUARE*2-Game.SQUARE/2);

    collision();
  }

  private void collision(){
    for(int i = 0; i < handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);
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
          if (Math.abs(y - tempObject.getY()) < Game.SQUARE){
            System.out.println("sIde");
            x -= velX;
            velX = 0;
          }
          else if (Math.abs(x - tempObject.getX()) < Game.SQUARE){
            System.out.println("Top");
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

  public void render(Graphics g) {
    g.setColor(Color.white);
    g.fillRect(x, y, Game.SQUARE, Game.SQUARE);
  }
}
