package mastermind;
import javax.swing.*;
import javax.swing.plaf.basic.BasicScrollBarUI;
import java.awt.*;

public class CustomScrollBarUI extends BasicScrollBarUI {

    // Rayon des bords arrondis
    private final int arcSize = 20;

    // Méthode pour dessiner la texture de la barre de défilement
    @Override
    protected void paintThumb(Graphics g, JComponent c, Rectangle thumbBounds) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(new Color(193,193,193)); // Couleur de fond de la barre de défilement

        // Dessin d'un rectangle avec des bords arrondis
        g2.fillRoundRect(thumbBounds.x, thumbBounds.y, thumbBounds.width, thumbBounds.height, arcSize, arcSize);
    }

    // Méthode pour dessiner la texture de la piste de la barre de défilement
    @Override
    protected void paintTrack(Graphics g, JComponent c, Rectangle trackBounds) {
        g.setColor(new Color(255,255,255)); // Couleur de fond de la piste de la barre de défilement
        g.fillRect(trackBounds.x, trackBounds.y, trackBounds.width, trackBounds.height);
    }

    // Méthode pour définir les dimensions du bouton de la barre de défilement (les flèches)
    @Override
    protected Dimension getMinimumThumbSize() {
        return new Dimension(10, 50);
    }

    // Méthode pour définir les dimensions du bouton de la barre de défilement (les flèches)
    @Override
    protected Dimension getMaximumThumbSize() {
        return new Dimension(10, 50);
    }
    // Désactiver l'affichage des flèches
    @Override
    protected JButton createDecreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
    }

    @Override
    protected JButton createIncreaseButton(int orientation) {
        return new JButton() {
            @Override
            public Dimension getPreferredSize() {
                return new Dimension(0, 0);
            }
        };
    }
}