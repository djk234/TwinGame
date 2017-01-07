import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Chest extends GameObject {

  public static BufferedImage img;

  public Chest(int x, int y, ID id) {
    super(x, y, id);
    try{
      this.img = ImageIO.read(new File("Images/chest/chest_0.png"));
    }
    catch(IOException ex){
      System.out.println("Fail");
    }
  }

  public Rectangle getBounds(){
    return new Rectangle(x, y+Game.SQUARE/2, Game.SQUARE, Game.SQUARE/2);
  }

  public Rectangle getOpenBounds(){
    return new Rectangle(x, y+Game.SQUARE/2, Game.SQUARE, Game.SQUARE/2+2);
  }

  public void tick() {

  }

  public void render(Graphics g) {
    g.drawImage(this.img,x-Game.getPlayerX()+(Game.WIDTH/2-Game.SQUARE/2), y-Game.getPlayerY()+(Game.HEIGHT/2-Game.SQUARE/2),null);
  }
}
