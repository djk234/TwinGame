import java.awt.Graphics;
import java.awt.Color;

public class HUD {

  public static int HEALTH = 100;
  public static final int HEALTH_MAX = 100;

  public void tick(){
    HEALTH = Game.clamp(HEALTH,0,100);
  }

  public void render(Graphics g){
    g.setColor(Color.gray);
    g.fillRect(15,15,HEALTH_MAX,16);
    g.setColor(Color.red);
    g.fillRect(15,15,HEALTH,16);
    g.setColor(Color.white);
    g.drawRect(15,15,HEALTH_MAX,16);
  }
}
