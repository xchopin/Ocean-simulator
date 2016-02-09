package glaces;
import geometrie.*;
/**
 * Created by Xavier on 04/02/2016.
 */
public class Jeu implements IObservateur {

    private static Ocean sea;
    private ArcticImage fenetre;
    private static int tour = 0;

    Jeu() {
        this.sea = new Ocean();
        this.fenetre =  new ArcticImage(this.sea.getLargeur(), this.sea.getHauteur() ) ;
        this.sea.setColors();
        this.fenetre.setColors(this.sea.getColors());
        this.sea.joueur.deplacerPingouin(this.sea, this.fenetre);
        this.fenetre.addKeyListener(this.sea.joueur);
    }

    public static int getTour(){
        return tour;
    }

    public static void augmenterTour(){
        tour++;
    }

    public void update(){
       this.sea.setColors();
       this.fenetre.setColors(this.sea.getColors());
    }

    public void notifier()  {
        this.sea.setColors();
        this.fenetre.setColors(this.sea.getColors());
        this.sea.checkerPingouin();
    }

    public static void cycleDeLaVie(){
        if (tour % 20 == 0){
            sea.fondreIcebergs();
        }
    }

    public static void main(String[] args){
        /**         Ancien main
         System.out.println("Main: ");
         Point p1 = new Point(2,0);
         Point p2 = new Point(1,0);
         Iceberg2D i = new Iceberg2D(p1,p2);
         i.toString();
         */

        Jeu game = new Jeu();

        sea.joueur.ajouterObservateur(game);


    }


}
