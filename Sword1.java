import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.util.*;
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

public class Sword1 extends Item {

  public static BufferedImage img;
  public static BufferedImage img0;
  public static BufferedImage img1;
  public static BufferedImage img2;
  public static BufferedImage img3;
  public static BufferedImage img4;
  public static BufferedImage img5;
  public static BufferedImage img6;
  public static BufferedImage img7;
  public static BufferedImage img8;
  public int count = -1;

  public Sword1(int x, int y, ID id, WordBubble bubble) {
    super(x, y, id, bubble);/*
    try{
      this.img0 = ImageIO.read(new File("Images/swords/sword1_0.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img1 = ImageIO.read(new File("Images/swords/sword1_1.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img2 = ImageIO.read(new File("Images/swords/sword1_2.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img3 = ImageIO.read(new File("Images/swords/sword1_3.png"));
    }
    catch(IOException ex){
    }
    try{
      this.img4 = ImageIO.read(new File("Images/swords/sword1_4.png"));
    }*/
  }

  public void tick() {
  }

  public void render(Graphics g) {
    g.drawImage(this.img,x-Game.getPlayerX()+(Game.WIDTH/2-Game.SQUARE/2), y-Game.getPlayerY()+(Game.HEIGHT/2-Game.SQUARE/2)+10,null);
  }
}
