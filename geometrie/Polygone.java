
package geometrie;

import geometrie.Carre;
import geometrie.Point;
import geometrie.Rectangle;
import geometrie.Segment;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class Polygone implements Cloneable, Iterable<Point> {
    protected Point[] tableau;
    protected int nbPoints;

    public Polygone(Point... tp) {
        assert tp.length >= 3 : "Argument incorrect";

        this.tableau = tp;
        this.nbPoints = tp.length;
    }

    public Polygone(Polygone pol) {
        assert pol != null : "Argument incorrect";

        this.tableau = new Point[pol.nbrePoints()];
        this.nbPoints = pol.nbrePoints();

        for(int k = 0; k < this.nbPoints; ++k) {
            this.tableau[k] = pol.getPoint(k);
        }

    }

    protected Polygone() {
    }

    public void deplacer(double ca, double co) {
        Iterator var5 = this.iterator();

        while(var5.hasNext()) {
            Point p = (Point)var5.next();
            p.deplacer(ca, co);
        }

    }

    public int nbrePoints() {
        return this.nbPoints;
    }

    public double perimetre() {
        double p = this.tableau[0].distance(this.tableau[this.nbPoints - 1]);

        for(int n = 0; n < this.nbPoints - 1; ++n) {
            p += this.tableau[n].distance(this.tableau[n + 1]);
        }

        return p;
    }

    public double surface() {
        System.out.println("Not yet implemented ...");
        double s = 0.0D;
        return s;
    }

    public double surfacePlusPetitRectangle() {
        double maxAbscisse = this.tableau[0].getAbscisse();
        double minAbscisse = maxAbscisse;
        double maxOrdonnee = this.tableau[0].getOrdonnee();
        double minOrdonnee = maxOrdonnee;

        for(int index = 1; index < this.tableau.length; ++index) {
            Point p = this.tableau[index];
            if(p.getAbscisse() < minAbscisse) {
                minAbscisse = p.getAbscisse();
            }

            if(p.getAbscisse() > maxAbscisse) {
                maxAbscisse = p.getAbscisse();
            }

            if(p.getOrdonnee() < minOrdonnee) {
                minOrdonnee = p.getOrdonnee();
            }

            if(p.getOrdonnee() > maxOrdonnee) {
                maxOrdonnee = p.getOrdonnee();
            }
        }

        return (maxAbscisse - minAbscisse) * (maxOrdonnee - minOrdonnee);
    }

    public boolean regulier() {
        double dref = this.getPoint(0).distance(this.getPoint(1));
        int k = 2;

        boolean regulier;
        for(regulier = true; regulier && k < this.nbrePoints(); ++k) {
            double d = this.getPoint(k - 1).distance(this.getPoint(k));
            regulier = d <= dref + 0.001D && d >= dref - 0.001D;
        }

        return regulier;
    }

    public boolean convexe() {
        int nb = this.nbrePoints();
        Segment s1 = new Segment(this.tableau[nb - 1], this.tableau[nb - 2]);
        boolean reference = s1.positionRelative(this.tableau[0]) > 0.0D;
        s1 = new Segment(this.tableau[0], this.tableau[nb - 1]);
        boolean convexe = s1.positionRelative(this.tableau[1]) > 0.0D == reference;

        for(int index = 0; convexe && index < this.tableau.length - 2; ++index) {
            s1 = new Segment(this.tableau[index + 1], this.tableau[index]);
            convexe = s1.positionRelative(this.tableau[index + 2]) > 0.0D == reference;
        }

        return convexe;
    }

    public String toString() {
        StringBuilder chaine = new StringBuilder("");
        Point[] var2 = this.tableau;
        int var3 = var2.length;

        for(int var4 = 0; var4 < var3; ++var4) {
            Point p = var2[var4];
            chaine.append(" " + p);
        }

        return chaine.toString();
    }

    public Point getPoint(int index) {
        assert index >= 0 && index < this.nbrePoints() : "Argument incorrect";

        return this.tableau[index];
    }

    public boolean equals(Object obj) {
        if(!(obj instanceof Polygone)) {
            return false;
        } else {
            Polygone poly = (Polygone)obj;
            if(poly.nbrePoints() != this.nbrePoints()) {
                return false;
            } else {
                Point premier = poly.getPoint(0);
                boolean trouve = false;
                int index = 0;

                while(!trouve && index < this.nbrePoints()) {
                    trouve = premier.equals(this.getPoint(index));
                    if(!trouve) {
                        ++index;
                    }
                }

                if(!trouve) {
                    return false;
                } else {
                    int indexObj = 1;
                    index = (index + 1) % this.nbrePoints();

                    boolean egal;
                    for(egal = true; egal && indexObj < poly.nbrePoints(); ++indexObj) {
                        egal = poly.getPoint(indexObj).equals(this.getPoint(index));
                        index = (index + 1) % this.nbrePoints();
                    }

                    return egal;
                }
            }
        }
    }

    public Object clone() {
        Point[] tp = new Point[this.nbrePoints()];

        for(int index = 0; index < this.nbrePoints(); ++index) {
            Point point = this.getPoint(index);
            tp[index] = new Point(point.getAbscisse(), point.getOrdonnee());
        }

        return new Polygone(tp);
    }

    public Iterator<Point> iterator() {
        return new Iterator() {
            int rang = 0;

            public boolean hasNext() {
                return this.rang < Polygone.this.nbrePoints();
            }

            public Point next() {
                if(this.rang == Polygone.this.nbrePoints()) {
                    throw new NoSuchElementException("Plus rien ");
                } else {
                    Point obj = Polygone.this.getPoint(this.rang);
                    ++this.rang;
                    return obj;
                }
            }

            public void remove() {
                throw new UnsupportedOperationException("Not supported yet.");
            }
        };
    }

    public static void main(String[] args) {
        Point p1 = new Point(1.0D, 3.0D);
        Point p2 = new Point(3.0D, 3.0D);
        Point p3 = new Point(3.0D, 1.0D);
        Point p4 = new Point(1.0D, 1.0D);
        Point p5 = new Point(0.0D, 2.0D);
        Point p66 = new Point(0.0D, 0.0D);
        Point p77 = new Point(1.0D, 0.0D);
        Point p8 = new Point(0.0D, 1.0D);
        System.out.println("\n-------------- Test de Polygone");
        Polygone pol0 = new Polygone(new Point[]{p1, p2, p3, p4, p5});
        System.out.println("Nombre de points " + pol0.nbrePoints());
        System.out.println("Les points " + pol0);
        Iterator pol1 = pol0.iterator();

        while(pol1.hasNext()) {
            Point tp1 = (Point)pol1.next();
            System.out.println(tp1);
        }

        Polygone pol11 = new Polygone(new Point[]{p66, p77, p4, p8});
        System.out.println("Nombre de points " + pol11.nbrePoints());
        System.out.println("Les points " + pol11);
        System.out.println("Périmètre " + pol11.perimetre());
        System.out.println();
        Point[] tp11 = new Point[]{p1, p2, p3, p4, p5};
        Polygone pol = new Polygone(tp11);
        System.out.println("Nombre de points " + pol.nbrePoints());
        System.out.println("Les points " + pol);
        System.out.println("Périmètre " + pol.perimetre());
        System.out.println("Surface " + pol.surface());
        System.out.println("Surface du plus petit rectangle " + pol.surfacePlusPetitRectangle());
        System.out.println("Convexe ? " + (pol.convexe()?"Oui":"Non"));
        System.out.println("Régulier ? " + (pol.regulier()?"Oui":"Non"));
        Polygone copie = (Polygone)((Polygone)pol.clone());
        System.out.println("Les points de la copie " + copie);
        System.out.println("Clone equals source ? " + (pol.equals(copie)?"Oui":"Non"));
        Polygone copie2 = new Polygone(pol);
        System.out.println("Les points de la copie constructeur " + copie2);
        System.out.println("Clone equals source ? " + (pol.equals(copie2)?"Oui":"Non"));
        System.out.println();
        Point[] tp1bis = new Point[]{p1, p2, p3, p5, p4};
        Polygone polb = new Polygone(tp1bis);
        System.out.println("Nombre de points " + polb.nbrePoints());
        System.out.println("Les points " + polb.toString());
        System.out.println("Périmètre " + polb.perimetre());
        System.out.println("Surface " + polb.surface());
        System.out.println("Surface du plus petit rectangle " + polb.surfacePlusPetitRectangle());
        System.out.println("Convexe ? " + (polb.convexe()?"Oui":"Non"));
        System.out.println("Régulier ? " + (polb.regulier()?"Oui":"Non"));
        polb.deplacer(3.0D, 10.0D);
        System.out.println("\n-------------- Test de Polygone convexe");
        pol0 = new Polygone(new Point[]{p1, p2, p3, p4});
        System.out.println("Les points " + pol0);
        System.out.println("Convexe ? " + (pol0.convexe()?"Oui":"Non"));
        System.out.println("\n-------------- Test de Rectangle");
        Point p6 = new Point(2.0D, 3.0D);
        Point p7 = new Point(2.0D, 1.0D);
        Point[] tp2 = new Point[]{p1, p6, p7, p4};
        Rectangle rec = new Rectangle(tp2);
        System.out.println("Nombre de points " + rec.nbrePoints());
        System.out.println("Les points " + rec);
        System.out.println("Périmètre " + rec.perimetre());
        System.out.println("Surface " + rec.surface());
        System.out.println("Centre " + rec.centre());
        System.out.println("Surface du plus petit rectangle " + rec.surfacePlusPetitRectangle());
        System.out.println("Convexe ? " + (rec.convexe()?"Oui":"Non"));
        System.out.println("Régulier ? " + (rec.regulier()?"Oui":"Non"));
        System.out.println("\n-------------- Test de Carre");
        Point[] tp3 = new Point[]{p1, p2, p3, p4};
        Carre car = new Carre(tp3);
        System.out.println("Nombre de points " + car.nbrePoints());
        System.out.println("Les points " + car);
        System.out.println("Périmètre " + car.perimetre());
        System.out.println("Surface " + car.surface());
        System.out.println("Centre " + car.centre());
        System.out.println("Surface du plus petit rectangle " + car.surfacePlusPetitRectangle());
        System.out.println("Convexe ? " + (car.convexe()?"Oui":"Non"));
        System.out.println("Régulier ? " + (car.regulier()?"Oui":"Non"));
        System.out.println("\n-------------- Et un peu de liaison dynamique");
        System.out.println("Nombre de points " + car.nbrePoints());
        System.out.println("Les points " + car);
        System.out.println("Périmètre " + car.perimetre());
        System.out.println("Surface " + car.surface());
        System.out.println("Centre " + ((Carre)car).centre());
        System.out.println("Surface du plus petit rectangle " + car.surfacePlusPetitRectangle());
        System.out.println("Convexe ? " + (car.convexe()?"Oui":"Non"));
        System.out.println("Régulier ? " + (car.regulier()?"Oui":"Non"));
    }
}
