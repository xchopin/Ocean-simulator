package glaces;
import geometrie.*;

/**
 * Classe: Un iceberg rectangulaire
 * @author: Xavier CHOPIN, Université de Lorraine - 2016
 */
public class Iceberg2D {

    private Point enBasAGauche ;
    private Point enHautADroite ;

    /**
     * @param g le coin en bas à gauche
     * @param d le coin en haut à droite
     * uniquement en coordonnées positives
     */
    public Iceberg2D(Point g, Point d) {
        super();
        this.enBasAGauche = g;
        this.enHautADroite = d;
    }

    /** Construction par fusion de deux icebergs qui se touchent
     * @param i1
     * @param i2
     */
    public Iceberg2D(Iceberg2D i1, Iceberg2D i2) {
        super();
        // si I2 est sur la droite est touche I1 qui se trouve sur la gauche , je devrais vérifier les coordonnées pour determiner le coté...
        this.enBasAGauche = i1.coinEnBasAGauche();
        this.enHautADroite = i2.coinEnHautADroite();
    }

    /**
     * @return le coin en bas à gauche
     */
    public Point coinEnBasAGauche() {
        return this.enBasAGauche ;
    }

    /**
     * @return le coin en haut à droite
     */
    public Point coinEnHautADroite() {
        return this.enHautADroite;
    }


    /**
     * @return hauteur
     */
    public double hauteur() {
        return ( this.coinEnHautADroite().getOrdonnee() - this.coinEnBasAGauche().getOrdonnee() );
    }

    /**
     * @return largeur
     */
    public double largeur() {
        return ( this.coinEnHautADroite().getAbscisse() - this.coinEnBasAGauche().getAbscisse() );
    }

    /**
     * @return surface totale
     */
    public double surface() {
        return ( largeur() * hauteur() );
    }

    /**
     * @param i
     * @return vrai si collision entre les deux icebergs
     */
    public boolean contient(Iceberg2D i) {


        if ( ( this.coinEnBasAGauche().getAbscisse() <= i.coinEnBasAGauche().getAbscisse() ) && ( i.coinEnHautADroite().getAbscisse()  <= this.coinEnHautADroite().getAbscisse() )

                &&

                (  ( this.coinEnBasAGauche().getOrdonnee() <= i.coinEnBasAGauche().getOrdonnee())  && ( i.coinEnHautADroite().getOrdonnee()  <= this.coinEnHautADroite().getOrdonnee() ))

                ){


            return true;

        }else{
            return false;
        }
    }




    /**
     * @param i
     * @return vrai si collision entre les deux icebergs
     */
    public boolean collision(Iceberg2D i) {

        return false;
    }

    /**
     * @param i
     * @return vrai si this est plus volumineux que i
     */
    public boolean estPlusGrosQue(Iceberg2D i) {
        if (this.largeur() > i.largeur() ){
            return true;
        }else{
            return false;
        }
    }

    public String toString() {
        String s = "Iceberg coin gauche (gauche): " + this.coinEnBasAGauche().toString() + " coin droit (haut): " + this.coinEnHautADroite().toString() ;
        return s;
    }

    /**
     * @return le point au centre de l'iceberg
     */
    public Point centre() {
        double x= (this.coinEnHautADroite().getAbscisse() - this.coinEnBasAGauche().getAbscisse() );
        double y = (this.coinEnHautADroite().getOrdonnee() - this.coinEnBasAGauche().getOrdonnee() );
        Point p = new Point(x,y);

        return p;

    }

    /** Réduction dans les quatre directions ; le centre ne bouge pas
     * @param fr dans ]0..1[ facteur de réduction
     */
    public void fondre(double fr) {

                // sinon trop petit pour le casser
            if (this.surface() > 50 * Pingouin.getTaille() ) {

                System.out.println(largeur() + " facteur de reduction: " + fr);
                this.casserBas(fr/2);
                this.casserHaut(fr/2);
                this.casserDroite(fr/2);
                this.casserGauche(fr/2);
                System.out.println("après: " + largeur());
            }

    }

    /**
     * Casser une partie à droite
     * @param fr dans ]0..1[ facteur de réduction
     */
    public void casserDroite(double fr) {
        double l = this.largeur() * fr;
        this.enHautADroite = new Point(this.coinEnHautADroite().getAbscisse() - l, this.coinEnHautADroite().getOrdonnee() );
    }

    /**
     * Casser une partie à gauche
     * @param fr dans ]0..1[ facteur de réduction
     */
    public void casserGauche(double fr) {
        double l = this.largeur() * fr;
        this.enBasAGauche = new Point(this.coinEnBasAGauche().getAbscisse() + l, this.coinEnBasAGauche().getOrdonnee() );
    }

    /**
     * Casser une partie en haut
     * @param fr dans ]0..1[ facteur de réduction
     */
    public void casserHaut(double fr) {
        double h = this.hauteur() * fr;
        this.enHautADroite = new Point(this.coinEnHautADroite().getAbscisse() ,  this.coinEnHautADroite().getOrdonnee() - h);
    }

    /**
     * Casser une partie en bas
     * @param fr dans ]0..1[ : définit le pourcentage supprimé
     */
    public void casserBas(double fr) {
        double h = this.hauteur() * fr;
        this.enBasAGauche = new Point(this.coinEnBasAGauche().getAbscisse() , this.coinEnBasAGauche().getOrdonnee() + h );
    }




}