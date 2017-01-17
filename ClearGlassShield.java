import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class ClearGlassShield extends Item {

  public int img_int = 0;

  public ClearGlassShield(int x, int y, ArrayList<BufferedImage> images, WordBubble bubble, Handler handler) {
    super(x, y, "ClearGlassShield", images, bubble, handler);
  }

  public void tick() {
    if (count < 0){

    }
    else {
      if (count%48 < 6) {
        img_int = 0;
      }
      else if (count%48 < 12){
        img_int = 1;
      }
      else if (count%48 < 18){
        img_int = 2;
      }
      else if (count%48 < 24){
        img_int = 3;
      }
      else if (count%48 < 30){
        img_int = 4;
      }
      else if (count%48 < 36){
        img_int = 5;
      }
      else if (count%48 < 42){
        img_int = 6;
      }
      else if (count%48 < 48){
        img_int = 7;
      }
      count++;
    }
  }

  public void render(Graphics g) {
    g.drawImage(images.get(img_int),x-Game.getPlayerX()+(Game.WIDTH/2-Game.SQUARE/2), y-Game.getPlayerY()+(Game.HEIGHT/2-Game.SQUARE/2)-15,null);
  }
}
