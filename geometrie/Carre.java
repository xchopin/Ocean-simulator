
package geometrie;

import geometrie.Point;
import geometrie.Rectangle;

public class Carre extends Rectangle {
    private double cote;

    public Carre(Point... var1) {
        super(var1);
        this.cote = this.largeur;
    }

    public double cote() {
        return this.cote;
    }

    public double perimetre() {
        return 4.0D * this.cote;
    }

    public double surface() {
        return this.cote * this.cote;
    }

    public boolean regulier() {
        return true;
    }
}
