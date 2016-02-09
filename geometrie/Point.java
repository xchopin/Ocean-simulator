
package geometrie;

public class Point {
    private static final Point origine = new Point(0.0D, 0.0D);
    protected double abs;
    protected double ord;
    protected static int compteur;

    public Point(double a, double o) {
        this.abs = a;
        this.ord = o;
        ++compteur;
    }

    public Point(Point p) {
        this(p.abs, p.ord);
    }

    public String toString() {
        return "<" + this.abs + "," + this.ord + ">";
    }

    public double getAbscisse() {
        return this.abs;
    }

    public double getOrdonnee() {
        return this.ord;
    }

    public double rho() {
        return this.distance(origine);
    }

    public double teta() {
        double teta = Math.acos(this.abs / this.rho());
        return this.ord > 0.0D?teta:-teta;
    }

    public double determinant(Point p) {
        return this.abs * p.ord - this.ord * p.abs;
    }

    public double distance(Point p) {
        double d1 = (this.getAbscisse() - p.getAbscisse()) * (this.getAbscisse() - p.getAbscisse());
        double d2 = (this.getOrdonnee() - p.getOrdonnee()) * (this.getOrdonnee() - p.getOrdonnee());
        return Math.sqrt(d1 + d2);
    }

    public boolean equals(Object obj) {
        if(obj == null) {
            return false;
        } else if(this.getClass() != obj.getClass()) {
            return false;
        } else {
            Point other = (Point)obj;
            return Double.doubleToLongBits(this.abs) != Double.doubleToLongBits(other.abs)?false:Double.doubleToLongBits(this.ord) == Double.doubleToLongBits(other.ord);
        }
    }

    public static int getCount() {
        return compteur;
    }

    public void deplacer(double ca, double co) {
        this.abs += ca;
        this.ord += co;
    }

    public static void main(String[] args) {
        Point p = new Point(1.0D, 2.0D);
        p.deplacer(1.0D, 5.0D);
        double x = p.distance(p);
        p = new Point(6.0D, 7.0D);
        p.deplacer(1.0D, 2.0D);
        double d = p.distance(p);
        p.deplacer(1.0D, 2.0D);
        System.out.println(p.getAbscisse());
        System.out.println(p.getOrdonnee());
        double r = p.rho();
        double t = p.teta();
        System.out.println("r=" + r + " t=" + t);
    }
}
