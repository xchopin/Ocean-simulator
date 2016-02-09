

package geometrie;

import geometrie.Point;

public class Segment {
    protected Point p1;
    protected Point p2;

    public Segment(Point var1, Point var2) {
        this.p1 = var1;
        this.p2 = var2;
    }

    public Segment(Segment var1) {
        this(new Point(var1.p1), new Point(var1.p2));
    }

    public Point getP1() {
        return this.p1;
    }

    public Point getP2() {
        return this.p2;
    }

    public Point milieu() {
        double var1 = (this.p1.getAbscisse() + this.p2.getAbscisse()) / 2.0D;
        double var3 = (this.p1.getOrdonnee() + this.p2.getOrdonnee()) / 2.0D;
        return new Point(var1, var3);
    }

    public double longueur() {
        return this.getP1().distance(this.getP2());
    }

    public String toString() {
        return "<" + this.getP1() + "," + this.getP2() + ">";
    }

    public double positionRelative(Point var1) {
        double var2 = this.p1.getAbscisse();
        double var4 = this.p2.getAbscisse();
        double var6 = this.p1.getOrdonnee();
        double var8 = this.p2.getOrdonnee();
        double var10 = var1.getAbscisse();
        double var12 = var1.getOrdonnee();
        return (var4 - var2) * (var12 - var8) - (var10 - var4) * (var8 - var6);
    }

    public void deplacer(double var1, double var3) {
        this.getP1().deplacer(var1, var3);
        this.getP2().deplacer(var1, var3);
    }

    public static void main(String[] var0) {
        Point var1 = new Point(1.0D, 3.0D);
        Point var2 = new Point(2.0D, 4.0D);
        Point var3 = new Point(3.0D, 4.0D);
        Point var4 = new Point(4.0D, 3.0D);
        Point var5 = new Point(3.0D, 3.0D);
        Point var6 = new Point(2.0D, 2.0D);
        Segment var7 = new Segment(var1, var2);
        Segment var8 = new Segment(var2, var3);
        Segment var9 = new Segment(var3, var4);
        Segment var10 = new Segment(var4, var5);
        Segment var11 = new Segment(var5, var6);
        Segment var12 = new Segment(var6, var1);
        System.out.println("Segment s1=" + var7);
        System.out.println("Segment s2=" + var8);
        System.out.println("Segment s3=" + var9);
        System.out.println("Segment s4=" + var10);
        System.out.println("Segment s5=" + var11);
        System.out.println("Segment s6=" + var12);
        boolean var13 = var11.positionRelative(var1) > 0.0D;
        System.out.println("Position relative de S3 par rapport à p1=" + var13);
        boolean var14 = var8.positionRelative(var3) > 0.0D;
        System.out.println("Position relative de S2 par rapport à p3=" + var14);
        var14 = var9.positionRelative(var4) > 0.0D;
        System.out.println("Position relative de S3 par rapport à p4=" + var14);
        var14 = var10.positionRelative(var5) > 0.0D;
        System.out.println("Position relative de S4 par rapport à p5=" + var14);
        var14 = var11.positionRelative(var6) > 0.0D;
        System.out.println("Position relative de S5 par rapport à p6=" + var14);
        var14 = var12.positionRelative(var1) > 0.0D;
        System.out.println("Position relative de S6 par rapport à p1=" + var14);
    }
}
