
package geometrie;

import geometrie.Point;

public class Droite {
    public static double epsilon = 1.0E-4D;
    protected double a;
    protected double b;

    public Droite(double var1, double var3) {
        this.a = var1;
        this.b = var3;
    }

    public Droite(Droite var1) {
        this.a = var1.coefficientA();
        this.b = var1.coefficientB();
    }

    public Droite(Point var1, Point var2) {
        double var3 = var1.getAbscisse() - var2.getAbscisse();
        this.a = (var1.getOrdonnee() - var2.getOrdonnee()) / var3;
        this.b = (var1.getAbscisse() * var2.getOrdonnee() - var1.getOrdonnee() * var2.getAbscisse()) / var3;
    }

    public String toString() {
        return Math.abs(this.coefficientA()) < epsilon?"y=" + this.coefficientB():"y=" + this.coefficientA() + "*x + " + this.coefficientB();
    }

    public double coefficientA() {
        return this.a;
    }

    public double coefficientB() {
        return this.b;
    }

    public boolean appartient(Point var1) {
        double var2 = var1.getOrdonnee() - this.coefficientA() * var1.getAbscisse() - this.coefficientB();
        return Math.abs(var2) < epsilon;
    }

    public Point intersection(Droite var1) {
        double var2 = var1.coefficientA() - this.coefficientA();
        double var4 = (var1.coefficientB() - this.coefficientB()) / var2;
        double var6 = (var1.coefficientA() * this.coefficientB() - this.coefficientA() * var1.coefficientB()) / var2;
        return new Point(var4, var6);
    }

    public boolean parallele(Droite var1) {
        return Math.abs(this.coefficientA() - var1.coefficientA()) < epsilon;
    }

    public boolean confondue(Droite var1) {
        return Math.abs(this.coefficientA()) < epsilon?Math.abs(var1.coefficientA()) < epsilon && Math.abs(this.coefficientB() - var1.coefficientB()) < epsilon:(Math.abs(var1.coefficientA()) < epsilon?false:(Math.abs(this.coefficientB()) >= epsilon?(Math.abs(var1.coefficientB()) < epsilon?false:Math.abs(this.coefficientA() / var1.coefficientA() - this.coefficientB() / var1.coefficientB()) < epsilon):Math.abs(var1.coefficientB()) < epsilon && Math.abs(this.coefficientA() - var1.coefficientA()) < epsilon));
    }

    public Point pointDAbscisse(double var1) {
        double var3 = this.coefficientA() * var1 + this.coefficientB();
        return new Point(var1, var3);
    }

    public Point pointDOrdonnee(double var1) {
        double var3 = (var1 - this.coefficientB()) / this.coefficientA();
        return new Point(var3, var1);
    }

    public static void main(String[] var0) {
        Droite var1 = new Droite(2.0D, 1.0D);
        System.out.println(var1);
        System.out.println(var1 + " et " + var1 + " sont confondues : " + var1.confondue(var1));
        Point var2 = new Point(0.0D, 1.0D);
        System.out.println(var2 + " appartient à " + var1 + " : " + var1.appartient(var2));
        System.out.println("<0.0,10.0> appartient à " + var1 + " : " + var1.appartient(new Point(0.0D, 10.0D)));
        Droite var3 = new Droite(var2, new Point(10.0D, 3.0D));
        System.out.println(var3);
        System.out.println(var1 + " et " + var3 + " sont confondues : " + var3.confondue(var1));
        System.out.println("intersection entre " + var1 + " et " + var3 + " : " + var3.intersection(var1));
        System.out.println("point d\'abscisse 3 dans " + var1 + " : " + var1.pointDAbscisse(3.0D));
        System.out.println("point d\'ordonnee 3 dans " + var1 + " : " + var1.pointDOrdonnee(3.0D));
    }
}
