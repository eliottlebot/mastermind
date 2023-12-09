package mastermind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.datatransfer.*;
import java.util.ArrayList;
import javax.swing.border.LineBorder;

public class ViewGame extends JFrame implements MastermindObserver {
    GameController controller;
    JPanel mainPanel = new JPanel();
    JPanel tentativePanel = new JPanel();
    JPanel avaibleColors = new JPanel();
    private JLabel selectedPion;


    private JLabel[] pions;
    private JLabel[] emptyCells;

    public ViewGame(GameController controller)
    {
        super("Mastermind");
        this.controller = controller;
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));



        add(mainPanel);//affichage des tentatives passées + indices
        add(tentativePanel);//tentative actuelle
        add(avaibleColors);//couleurs dispos
        setVisible(true);
    }

    public void init(int nbTentatives, int nbPionsCombinaison)
    {
        mainPanel.setLayout(new GridLayout(nbTentatives, 2));
        tentativePanel.setLayout(new GridLayout(1, nbPionsCombinaison + 1));
    }



    public void showAvaibleColors(Couleurs[] couleursDispo)
    {

        avaibleColors.setLayout(new GridLayout(1, couleursDispo.length));

        pions = new JLabel[couleursDispo.length];
        for (int i = 0; i < pions.length; i++) {
            pions[i] = new JLabel(new ImageIcon("assets/pions/" + couleursDispo[i] + ".png"));
            pions[i].setTransferHandler(new TransferHandler("icon"));
            pions[i].addMouseListener(new BallMouseListener());
            pions[i].setPreferredSize(new Dimension(64,64));
            pions[i].putClientProperty("couleur", couleursDispo[i]);
            avaibleColors.add(pions[i]);
        }
    }

    public void startTentative(int nbPionsCombi)
    {
        emptyCells = new JLabel[nbPionsCombi];
        for (int i = 0; i < nbPionsCombi; i++) {
            emptyCells[i] = new JLabel();
            emptyCells[i].setPreferredSize(new Dimension(50, 50));
            emptyCells[i].setBorder(new LineBorder(Color.BLACK));
            emptyCells[i].setTransferHandler(new TransferHandler("icon"));
            emptyCells[i].addMouseListener(new EmptyCellMouseListener());
            tentativePanel.add(emptyCells[i]);
        }

        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener( actionEvent  -> {
            Couleurs[] tentative = new Couleurs[nbPionsCombi];

            JPanel archiveTentative = new JPanel();
            archiveTentative.setLayout(new GridLayout(1, nbPionsCombi + 1));

            int i = 0;
            for (JLabel j: emptyCells)
            {
                ImageIcon originalIcon = (ImageIcon) j.getIcon();
                JLabel duplicatedLabel = new JLabel(originalIcon);
                archiveTentative.add(duplicatedLabel);
                tentative[i] = ((Couleurs)j.getClientProperty("couleur"));

                j.putClientProperty("couleur", null);
                j.setIcon(null);
                i++;
            }
            mainPanel.add(archiveTentative);

            try {
                controller.validerTentative(tentative);
            } catch (Exception e) {
            }

        });
        tentativePanel.add(validerButton);
    }

    class BallMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            selectedPion = (JLabel) e.getSource();
            TransferHandler handler = selectedPion.getTransferHandler();
            handler.exportAsDrag(selectedPion, e, TransferHandler.COPY);
        }
    }



    class EmptyCellMouseListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            JLabel emptyCell = (JLabel) e.getSource();
            TransferHandler handler = emptyCell.getTransferHandler();

            // Vérification si une boule est sélectionnée
            if (selectedPion != null) {
                ImageIcon icon = (ImageIcon) selectedPion.getIcon();
                emptyCell.setIcon(icon);
                emptyCell.putClientProperty("couleur", selectedPion.getClientProperty("couleur"));
            }
        }
    }


}
