package glaces;

import geometrie.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
/**
 * @author: Xavier Chopin
 */

public class Ocean {

    private static int nb;
    public static int largeur = 500;
    public static int hauteur = 400;
    private static Iceberg2D[] tab;
    private static int[][] colors;
    static Pingouin joueur;
    private Random random = new Random();

    public Ocean(){


        /**   this.nb = 1;
         this.tab = new Iceberg2D[] {new Iceberg2D( new Point(50,0), new Point(10,100)  )};
         */

        this.joueur = new Pingouin ( new Point ( largeur - 1.5* Pingouin.getTaille(),hauteur - 1.5 * Pingouin.getTaille( ) ) ) ;
        this.genererIcebergs();
        System.out.println("Nombre d'icebergs avant le check des collisions: " + this.nb);
        this.checkerCollision();

        this.nb = this.tab.length;
        System.out.println("Nombre d'icebergs après " + this.nb);
        this.colors =  new int[this.largeur][this.hauteur];

    }

    public Ocean(int largeur,int hauteur,Iceberg2D[] tabIceberg){

        this.largeur = largeur;
        this.hauteur = hauteur;
        System.arraycopy( tabIceberg, 0, tab, 0, tabIceberg.length );

    }

    public void genererIcebergs() {

        this.nb =  (int) (random.nextDouble() * 80);

        while (this.nb == 0){
            this.nb =  (int) (random.nextDouble() * 70);
        }

        this.tab = new Iceberg2D[this.nb];
        Point p1;
        Point p2;

        for (int i = 0; i < this.nb ; i++){
            double x1=random.nextDouble() * this.largeur;
            double x2=random.nextDouble() * this.largeur;
            double y1=random.nextDouble() * this.hauteur;
            double y2=random.nextDouble() * this.hauteur;

            if (x1<x2) {
                if (y1<y2) {
                    p1=new Point( x1 , y1);
                    p2=new Point( x2 , y2);
                } else {
                    p1=new Point( x1 , y2);
                    p2=new Point( x2 , y1);
                }
            } else {
                if (y1<y2) {
                    p1=new Point( x2 , y1);
                    p2=new Point( x1 , y2);
                } else {
                    p1=new Point( x2 , y2);
                    p2=new Point( x1 , y1);
                }
            }

            this.tab[i] =  new Iceberg2D( p1 , p2 );
        }
    }


        // vérifie plutôt si un Iceberg est dans un autre afin de le fusionner, car la colision est bizarre à modéliser avec des rectangles
    public void checkerCollision(){

        ArrayList<Iceberg2D> list=new ArrayList<Iceberg2D>();
        boolean valide=false;
        for (int i = 0; i < this.nb ; i++){

            for (int j = 0; j < this.nb ; j++){
                valide=false;

                // pas obligé car il peut fusionner avec lui même ;)
                if ( i != j ){
                    //1er cas: i contient j
                    if (tab[i].contient(tab[j])){
                        valide=true;
                    }
                } else {
                    valide=true; // si i == j
                }


            }

            if (valide) {
                list.add(this.tab[i]);
            }
        }

        tab=new Iceberg2D[list.size()];
        tab=list.toArray(this.tab);

    }

    public void checkerPingouin(){

         if (this.tab.length > 0){

            ArrayList<Iceberg2D> list = new ArrayList<Iceberg2D>(Arrays.asList(this.tab));

            for (int i = 0; i < this.tab.length ; i++){

                        if (this.joueur.estSurIceberg(tab[i])){
                            list.remove(this.tab[i]);
                        }
            }

            this.tab = new Iceberg2D[list.size()];
            this.tab = list.toArray(this.tab);
            this.nb  = this.tab.length;


        }
    }

    /** Fond les icebergs avec un facteur random */
    public void fondreIcebergs(){
        double ran = random.nextDouble() * 10;
        double facteur;

        // petite règle pour éviter d'avoir le réchauffement climatique des années 2080..
        if (ran < 5){
             facteur = 0.1;
        }else if ( ran < 7 ){
             facteur = 0.2 ;
        }else{
             facteur = 0.3;
        }

        for (int i = 0 ; i < tab.length ; i++){
            tab[i].fondre(facteur);
        }

    }

    /** Retourne colors */
    public int [][] getColors() { return colors; }

    public static void setColors() {
        //Vérifier si le tab est pas trop petit sinon on va avoir un ArrayOutOfBound Exception !

                //Ocean

        for (int i = 0; i < largeur; i++) {
            for (int j = 0; j < hauteur; j++) {
                colors[i][j] = 0;
            }
        }

        for (int i = 0 ; i <tab.length ; i++){


                        //Icebergs

            int x = (int) tab[i].coinEnBasAGauche().getAbscisse();
            int x1 = (int) tab[i].coinEnHautADroite().getAbscisse();
            int y = (int) tab[i].coinEnBasAGauche().getOrdonnee();
            int y1 = (int) tab[i].coinEnHautADroite().getOrdonnee();

            for (int j = x ; j <= x1 ; j++){
                for (int j1 = y ; j1 <= y1 ; j1++){
                    colors[j][j1] = 1;
                }

            }

        }
                        //Pingouin

        int x1 = (int) joueur.getCoinHautDroit().getAbscisse();
        int x  = (int) joueur.getCoinBasGauche().getAbscisse();
        int y  = (int) joueur.getCoinBasGauche().getOrdonnee();
        int y1 = (int) joueur.getCoinHautDroit().getOrdonnee();

        for (int j = x ; j <= x1 ; j++){
            for (int j1 = y ; j1 <= y1 ; j1++){
                colors[j][j1] = 3;

            }

        }

    }

    public int getNb() {
        return nb;
    }

    public void setNb(int nb) {
        this.nb = nb;
    }

    public int getLargeur() {
        return largeur;
    }

    public void setLargeur(int largeur) {
        this.largeur = largeur;
    }

    public int getHauteur() {
        return hauteur;
    }

    public void setHauteur(int hauteur) {
        this.hauteur = hauteur;
    }

    public Iceberg2D[] getTab() {
        return tab;
    }

    public void setTab(Iceberg2D[] tab) {
        this.tab = tab;
    }
}

































