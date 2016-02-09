package glaces;

import geometrie.Point;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Pingouin extends Observable implements KeyListener {

    private Point coinBasGauche;
    private Point coinHautDroit;
    private boolean detecteClavier;
    private static int taille = 5;
    private int vitesse;


    public Pingouin(Point p) {
        this.vitesse = 10;
        this.coinBasGauche = p;
        this.coinHautDroit= new Point(this.coinBasGauche.getAbscisse() + taille ,this.coinBasGauche.getOrdonnee() + taille );
    }

    public void run(){
        // deadmau5 ... lol.
        while (1<2){
            if(this.detecteClavier) {
                notifierObservateurs();
            }
        }
    }

    public Point getCoinBasGauche() {
        return this.coinBasGauche;
    }

    public Point getCoinHautDroit() {
        return this.coinHautDroit;
    }

    public static int getTaille() { return taille;}

    public boolean estSurIceberg(Iceberg2D i) {





        if (                                  // x
                ( ( this.getCoinBasGauche().getAbscisse() >=  i.coinEnBasAGauche().getAbscisse() ) || ( this.getCoinBasGauche().getAbscisse() >=  i.coinEnBasAGauche().getAbscisse() - (taille/2) ) || ( this.getCoinBasGauche().getAbscisse() >=  i.coinEnBasAGauche().getAbscisse() + (taille/2) ) )
                                                &&
                ( (this.getCoinHautDroit().getAbscisse() <= i.coinEnHautADroite().getAbscisse()) || ( this.getCoinHautDroit().getAbscisse() <= i.coinEnBasAGauche().getAbscisse() - (taille/2) ) || ( this.getCoinHautDroit()).getAbscisse() <=  i.coinEnBasAGauche().getAbscisse() + (taille/2) )

                                                &&

                                               //y
                ( this.getCoinBasGauche().getOrdonnee() >= i.coinEnBasAGauche().getOrdonnee()  )
                                                &&
                ( this.getCoinHautDroit().getOrdonnee() <= i.coinEnHautADroite().getOrdonnee() )

                ){


                return true;

        }else{
                return false;
                }
    }

    public Point[] getPosition() {

        Point res[] = { this.coinBasGauche,this.coinHautDroit };
        return res;

    }

    public void setPosition(Point p) {
        if ( (p.getAbscisse() >= 0 ) && (p.getAbscisse() + taille <= Ocean.largeur)
            && (p.getOrdonnee() >= 0)  && (p.getOrdonnee()+ taille <= Ocean.hauteur) ){

                this.coinBasGauche = p;
                this.coinHautDroit= new Point(this.coinBasGauche.getAbscisse() + taille ,this.coinBasGauche.getOrdonnee() + taille );
        }
    }

    public void moveLeft(){
        double  x =  this.getCoinBasGauche().getAbscisse() - vitesse;
        double  y =  this.getCoinBasGauche().getOrdonnee() ;
        this.setPosition(new Point(x, y));
    }

    public void moveRight(){
        double  x =  this.getCoinBasGauche().getAbscisse() + vitesse;
        double  y =  this.getCoinBasGauche().getOrdonnee() ;
        this.setPosition(new Point(x, y));
    }

    public void moveTop(){
        double  x =  this.getCoinBasGauche().getAbscisse();
        double  y =  this.getCoinBasGauche().getOrdonnee() - vitesse ;
        this.setPosition(new Point(x,y));
    }

    public void moveBottom(){
        double  x =  this.getCoinBasGauche().getAbscisse();
        double  y =  this.getCoinBasGauche().getOrdonnee() + vitesse ;
        this.setPosition(new Point(x, y));
    }



    public void deplacerPingouin(Ocean o, ArcticImage a) {
        o.setColors();
        a.setColors(o.getColors());

    }

    @Override
    public void keyTyped(KeyEvent event) {


    }

    @Override
    public void keyPressed(KeyEvent event) {
        Jeu.augmenterTour();
        Jeu.cycleDeLaVie();

        if ( event.getKeyCode() == KeyEvent.VK_Q) {
            this.moveLeft();
            this.notifierObservateurs();

        }else if ( event.getKeyCode() == KeyEvent.VK_D) {
            this.moveRight();
            this.notifierObservateurs();

        }else  if ( event.getKeyCode() == KeyEvent.VK_Z) {
            this.moveTop();
            this.notifierObservateurs();

        } else  if ( event.getKeyCode() == KeyEvent.VK_S) {
            this.moveBottom();
            this.notifierObservateurs();

        }else{
             System.exit(0);
    }


    }

    @Override
    public void keyReleased(KeyEvent event) {

    }


}