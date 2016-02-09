package glaces;

import java.awt.Component;
import java.awt.Dimension;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.image.BufferedImage;
import javax.swing.*;

public class ArcticImage extends JFrame {

    protected final static int OCEAN = 1007296;
    protected final static int GLACE = 16777215;
    protected final static int PINGU2 = 13731860;
    protected final static int PINGU3 = 13720084;
    protected final static int NEANT = 0;
    private JLabel jlabel;

    public ArcticImage(int largeur, int hauteur) {
        super("ArcticGame");
        System.out.println("largeur " + largeur);
        System.out.println("hauteur : " + hauteur);
        this.jlabel = new JLabel();
        this.jlabel.setPreferredSize(new Dimension(largeur, hauteur));
        this.add(this.jlabel, "Center");
        this.pack();
        this.setLocationRelativeTo((Component) null);
        this.setVisible(true);

        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    public void setColors(int[][] tab) {
        int largeur = tab.length;
        int hauteur = tab[0].length;
        BufferedImage image = new BufferedImage(largeur, hauteur, 1);

        for (int x = 0; x < largeur; ++x) {
            for (int y = 0; y < hauteur; ++y) {
                int couleur;
                switch (tab[x][y]) {
                    case 0:
                        couleur = OCEAN;
                        break;
                    case 1:
                        couleur = GLACE;
                        break;
                    case 2:
                        couleur = PINGU2;
                        break;
                    case 3:
                        couleur = PINGU3;
                        break;
                    default:
                        couleur = NEANT;
                }

                image.setRGB(x, y, couleur);
            }
        }

        this.jlabel.setIcon(new ImageIcon(image));
    }

    public void fermer() {
        this.dispose();
    }

}
