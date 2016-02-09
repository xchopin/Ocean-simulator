
package geometrie;

import geometrie.Point;
import geometrie.Segment;

public class Triangle {
    private Point p1;
    private Point p2;
    private Point p3;
    private static double eps = 0.001D;

    public Triangle(Point var1, Point var2, Point var3) {
        this.p1 = var1;
        this.p2 = var2;
        this.p3 = var3;
    }

    public Triangle(Triangle var1) {
        this(new Point(var1.p1), new Point(var1.p2), new Point(var1.p3));
    }

    public Triangle(Segment var1, Segment var2) {
        this(var1.getP1(), var1.getP2(), var2.getP2());
    }

    public Point getP1() {
        return this.p1;
    }

    public Point getP2() {
        return this.p2;
    }

    public Point getP3() {
        return this.p3;
    }

    public double cote1() {
        return this.getP1().distance(this.getP2());
    }

    public double cote2() {
        return this.getP2().distance(this.getP3());
    }

    public double cote3() {
        return this.getP1().distance(this.getP3());
    }

    public double perimetre() {
        return this.cote1() + this.cote2() + this.cote3();
    }

    public boolean isocele() {
        boolean var1 = this.cote1() >= this.cote2() - eps && this.cote1() <= this.cote2() + eps;
        boolean var2 = this.cote2() >= this.cote3() - eps && this.cote2() <= this.cote3() + eps;
        boolean var3 = this.cote3() >= this.cote1() - eps && this.cote3() <= this.cote1() + eps;
        return var1 || var2 || var3;
    }

    public boolean equilateral() {
        boolean var1 = this.cote1() >= this.cote2() - eps && this.cote1() <= this.cote2() + eps;
        boolean var2 = this.cote2() >= this.cote3() - eps && this.cote2() <= this.cote3() + eps;
        return var1 && var2;
    }

    public String toString() {
        return "<" + this.getP1() + "," + this.getP2() + "," + this.getP3() + ">";
    }

    public void deplacer(double var1, double var3) {
        this.getP1().deplacer(var1, var3);
        this.getP2().deplacer(var1, var3);
        this.getP3().deplacer(var1, var3);
    }

    public static void main(String[] var0) {
        Point var1 = new Point(5.0D, 2.0D);
        Point var2 = new Point(1.0D, 1.0D);
        Point var3 = new Point(6.0D, 0.0D);
        Triangle var4 = new Triangle(var1, var2, var3);
        System.out.println("Triangle " + var4);
        System.out.println("cote1 " + var4.cote1());
        System.out.println("cote2 " + var4.cote2());
        System.out.println("cote3 " + var4.cote3());
        System.out.println("perimetre " + var4.perimetre());
        var4.deplacer(1.0D, 2.0D);
        System.out.println("equilateral " + var4.equilateral());
        System.out.println("isocele " + var4.isocele());
        System.out.println("perimetre " + var4.perimetre());
    }
}
