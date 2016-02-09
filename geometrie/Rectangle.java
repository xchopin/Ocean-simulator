
package geometrie;

import geometrie.Point;
import geometrie.Polygone;
import geometrie.Segment;

public class Rectangle extends Polygone {
    protected double longueur;
    protected double largeur;

    public Rectangle(Point... var1) {
        super(var1);
        this.longueur = var1[0].distance(var1[1]);
        this.largeur = var1[1].distance(var1[2]);
    }

    public Rectangle(Point var1, Point var2) {
        this.tableau = new Point[4];
        this.tableau[0] = var1;
        this.tableau[1] = new Point(var1.getAbscisse(), var2.getOrdonnee());
        this.tableau[2] = var2;
        this.tableau[3] = new Point(var2.getAbscisse(), var1.getOrdonnee());
        this.largeur = this.tableau[1].distance(this.tableau[2]);
    }

    public int nbrePoints() {
        return 4;
    }

    public double longueur() {
        return this.longueur;
    }

    public double largeur() {
        return this.largeur;
    }

    public double perimetre() {
        return 2.0D * this.largeur + 2.0D * this.longueur;
    }

    public double surface() {
        return this.longueur * this.largeur;
    }

    public Point centre() {
        Segment var1 = new Segment(this.tableau[0], this.tableau[2]);
        return var1.milieu();
    }

    public boolean convexe() {
        return true;
    }

    public boolean regulier() {
        return this.longueur == this.largeur;
    }
}
