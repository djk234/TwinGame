import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject {

  Handler handler;

  public BasicEnemy(int x, int y, ID id, Handler handler) {
    super(x, y, id);
    this.handler = handler;
    velX = 2;
    velY = 2;
  }

  public Rectangle getBounds(){
    return new Rectangle(x,y,Game.SQUARE,Game.SQUARE);
  }

  public void tick() {
    x += velX;
    y += velY;

    if (y <= 0 || y >= Game.HEIGHT - Game.SQUARE*2) velY *= -1;
    if (x <= 0 || x >= Game.WIDTH - Game.SQUARE) velX *= -1;

    collision();

  }

  private void collision(){
    for(int i = 0; i < handler.object.size(); i++){
      GameObject tempObject = handler.object.get(i);
      if(tempObject.getId() == ID.Obstacle){
        if(getBounds().intersects(tempObject.getBounds())){
          if (Math.abs((y - velY) - tempObject.getY()) < Game.SQUARE){
            velX *= -1;
          }
          else if (Math.abs((x-velX) - tempObject.getX()) < Game.SQUARE){
            velY *= -1;
          }
          else {
            velX *= -1;
            velY *= -1;
          }
        }
      }
    }
  }

  public void render(Graphics g) {
    g.setColor(Color.red);
    g.fillRect(x, y, Game.SQUARE, Game.SQUARE);
  }
}
