import java.awt.Graphics;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.Font;
import java.util.*;

public class WordBubble extends GameObject{

  public ArrayList<String> conversation;
  public int phrase = 0;
  public Handler handler;

  public WordBubble(int x, int y, ID id, Handler handler, ArrayList<String> conversation) {
    super(x, y, id);
    this.handler = handler;
    this.conversation = conversation;
  }

  /*public ArrayList<String> getConvo(){
    return conversation;
  }

  public void setConvo(ArrayList<String> conversation){
    this.conversation = conversation;
  }*/

  public int nextPhrase() {
    if(phrase < conversation.size() - 1){
      phrase++;
    }
    else {
      handler.removeBubble();
      phrase = 0;
    }
    return phrase;
  }

  public Rectangle getBounds(){
    return new Rectangle(Game.SQUARE*2, Game.HEIGHT - 3*Game.SQUARE, Game.WIDTH - Game.SQUARE*2, Game.SQUARE*2);
  }

  public void tick() {

  }

  public void render(Graphics g){
    g.setColor(Color.white);
    g.fillRect(Game.SQUARE*2, Game.HEIGHT - 4*Game.SQUARE, Game.WIDTH - Game.SQUARE*4, Game.SQUARE*2);
    g.setColor(Color.black);
    g.drawRect(Game.SQUARE*2, Game.HEIGHT - 4*Game.SQUARE, Game.WIDTH - Game.SQUARE*4, Game.SQUARE*2);
    g.setFont(Game.customfont);
    g.drawString(conversation.get(phrase), Game.SQUARE*3,Game.HEIGHT - 3*Game.SQUARE+5);
  }
}
