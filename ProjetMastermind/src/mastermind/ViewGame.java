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
    private ImageIcon draggedIcon;
    private int nbPionsCombi;


    private JLabel[] pions;
    private JLabel[] emptyCells;

    public ViewGame(GameController controller)
    {
        super("Mastermind");
        this.controller = controller;
        setSize(600, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());
        mainPanel.setLayout(new GridLayout(8,1));


        this.add(mainPanel, BorderLayout.PAGE_START);//affichage des tentatives passées + indices
        add(tentativePanel, BorderLayout.CENTER);//tentative actuelle
        add(avaibleColors, BorderLayout.PAGE_END);//couleurs dispos
        setVisible(true);
    }

    public void init(int nbTentatives, int nbPionsCombinaison)
    {
        nbPionsCombi = nbPionsCombinaison;
        mainPanel.setLayout(new GridLayout(nbTentatives, 2));
        tentativePanel.setLayout(new GridLayout(1, nbPionsCombinaison + 1));
    }



    public void showAvaibleColors(Couleurs[] couleursDispo)
    {

        avaibleColors.setLayout(new GridLayout(1, couleursDispo.length));

        pions = new JLabel[couleursDispo.length];
        for (int i = 0; i < pions.length; i++) {
            pions[i] = new JLabel(new ImageIcon("assets/pions/" + couleursDispo[i] + ".png"));
            //pions[i].setTransferHandler(new TransferHandler("icon"));
            pions[i].addMouseListener(new PionMouseListener());
            pions[i].addMouseMotionListener(new PionsMouseMotionListener());
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
            //emptyCells[i].setTransferHandler(new TransferHandler("icon"));
            emptyCells[i].addMouseListener(new EmptyCellMouseListener());
            tentativePanel.add(emptyCells[i]);
        }

        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener( actionEvent  -> {
            Combinaison tentative = new Combinaison(nbPionsCombi);


            int i = 0;
            for (JLabel j: emptyCells)
            {
                tentative.setCouleur(((Couleurs)j.getClientProperty("couleur")), i);

                j.putClientProperty("couleur", null);
                j.setIcon(null);
                i++;
            }

            //Le bouton permet d'appeller la fonction de verification dans le game controller
            try {
                controller.validerTentative(tentative);
            }
            catch (Exception e) {
                System.out.println("tentative" + tentative.getCombinaison().length);
                System.out.println(e.getMessage());
            }
        });
        tentativePanel.add(validerButton);
    }

    public void addTentativeUpdateIndice(Tentative tentative, Indice[] indices)
    {
        JPanel archiveTentative = new JPanel();
        archiveTentative.setLayout(new GridLayout(1, 2));

        JPanel tentativePanel = new JPanel();
        tentativePanel.setLayout(new GridLayout(1, nbPionsCombi));

        JPanel indicePanel = new JPanel();
        indicePanel.setBorder(new LineBorder(Color.BLACK));
        indicePanel.setLayout(new GridLayout(1, nbPionsCombi));


        for(int i = 0; i < tentative.getCombinaison().getLength(); i++)
        {
            ImageIcon originalIcon = new ImageIcon("assets/pions/" + tentative.getCouleurs()[i] + ".png");
            JLabel duplicatedLabel = new JLabel(originalIcon);
            tentativePanel.add(duplicatedLabel);
        }
        archiveTentative.add(tentativePanel);

        for(int i = 0; i < indices.length; i++)
        {
            JLabel j = new JLabel();
            switch (indices[i])
            {
                case NOIR -> j.setIcon(new ImageIcon("assets/indices/NOIR.png"));
                case BLANC -> j.setIcon(new ImageIcon("assets/indices/BLANC.png"));
                case VIDE -> j.setIcon(new ImageIcon("assets/indices/VIDE.png"));
            }

            indicePanel.add(j);
        }

        archiveTentative.add(indicePanel);
        mainPanel.add(archiveTentative);
    }


    public void newManche(boolean isWin)
    {
        mainPanel.removeAll();

        JLabel messageWin = new JLabel();

        if(isWin)
        {
            messageWin.setText("Gagné");
        }
        else
        {
            messageWin.setText("Perdu");
        }

        add(messageWin);

        JButton continuer = new JButton("continuer");
        continuer.addActionListener( actionEvent  -> {
            this.remove(messageWin);
            this.remove(continuer);
        });
        add(continuer);
    }

    class PionMouseListener extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent e) {
            selectedPion = (JLabel)e.getSource();
            draggedIcon = (ImageIcon) selectedPion.getIcon();
        }
    }

    class EmptyCellMouseListener extends MouseAdapter {
        @Override
        public void mouseReleased(MouseEvent e) {
            JLabel emptyCell = (JLabel) e.getSource();
            if (selectedPion != null) {
                ImageIcon icon = (ImageIcon) selectedPion.getIcon();
                emptyCell.setIcon(icon);
                emptyCell.putClientProperty("couleur", selectedPion.getClientProperty("couleur"));
                draggedIcon = null;
                setCursor(Cursor.getDefaultCursor());
            }
        }
    }

    class PionsMouseMotionListener extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent e) {
            if (draggedIcon != null) {
                setCursor(Toolkit.getDefaultToolkit().createCustomCursor(
                        draggedIcon.getImage(), new Point(0, 0), "Custom Cursor"));
            }
        }
    }
}
