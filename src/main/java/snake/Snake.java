import svg.*;
import jus.util.*;
public class Snake
{
   private Dialogue d;
   private Ecran e;
   private Chemin serpent;
   private double x;
   private double y;
   private double angle;
   private int pas;
   public Snake()
   {
       d = new Dialogue();
       e = new Ecran();
       
       serpent = new Chemin();
       
       serpent.ajoutePt(100,100);
       serpent.ajoutePt(100,110);
       serpent.ajoutePt(100,120);
       serpent.ajoutePt(100,130);
       serpent.ajoutePt(100,140);
       serpent.ajoutePt(100,150);
       serpent.ajoutePt(100,160);
       serpent.ajoutePt(100,170);
       serpent.ajoutePt(100,180);
       serpent.ajoutePt(100,190);
       serpent.ajoutePt(100,200);
       x = 100;
       y = 200;
       serpent.couleurT("vert",1);
       serpent.epaisseurT(5);
       angle = -90;
       pas = 10;
   }
   
   public boolean perdu(){
       if (sorti() == true | mord() == true){
        return true;
        }
       else{
        return false;
          }
    
   }
   
   public boolean gagne(){
    return false;
   }
   public boolean sorti(){

       return false;
   }
   public boolean mord(){
    return false;
    }
   public void avance()
   {
       double fleche = 38;
       while(gagne() == false & perdu() == false){
           d.afficherln(e.toucheAppuyee());
       if (e.toucheAppuyee() == 38){
           fleche = 38;
       }
       else if (e.toucheAppuyee() == 37)
       {
           fleche = 37;
       }
       else if (e.toucheAppuyee() == 40)
       {
          fleche = 40;
        }
       else if (e.toucheAppuyee() == 39)
       {
           fleche = 39;
       }
       
       if (fleche == 38){
           serpent.ajoutePt(x,(y-pas));
           y = (y-pas);
       }
       else if (fleche == 37)
       {
           serpent.ajoutePt((x-pas),y);
           x = (x-pas);
       }
       else if (fleche == 40)
       {
          serpent.ajoutePt(x,(y+pas));
          y = (y+pas);
        }
       else if (fleche == 39)
       {
           serpent.ajoutePt((x+pas),y);
           x = (x+pas);
       }
       serpent.enlevePt(0);
       e.pause(200);
       
    }
   }
}
