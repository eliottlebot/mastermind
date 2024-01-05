package mastermind;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.LineBorder;

public class ViewGame extends JFrame implements MastermindObserver {
    GameController controller;
    JPanel mainPanel = new JPanel();
    JPanel tentativePanel = new JPanel();
    JPanel avaibleColors = new JPanel();

    private JLabel selectedPion;
    private ImageIcon draggedIcon;
    private int nbPionsCombi;
    private int tentativeCount = 0;
    private int nbTentative;


    private JLabel[] pions;
    private JLabel[] emptyCells;

    public ViewGame(GameController controller)
    {
        super("Mastermind");
        this.controller = controller;
        setSize(700, 900);
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
        tentativeCount = 0;
        this.nbTentative = nbTentatives;
        nbPionsCombi = nbPionsCombinaison;
        mainPanel.setLayout(new GridLayout(nbTentatives, 1));
        tentativePanel.setLayout(new GridLayout(1, nbPionsCombinaison + 1));


        for (int i = 0; i < nbTentatives; i++)
        {
            JPanel fillPnl = new JPanel();
            fillPnl.setBorder(new LineBorder(Color.BLACK));
            fillPnl.setLayout(new GridLayout(1, 2));


            JPanel fillTenta = new JPanel();
            fillTenta.setBorder(new LineBorder(Color.BLACK));
            fillTenta.setLayout(new GridLayout(1, nbPionsCombinaison));


            JPanel fillIndice = new JPanel();
            fillIndice.setBorder(new LineBorder(Color.BLACK));
            fillIndice.setLayout(new GridLayout(1, nbPionsCombinaison));

            for(int j = 0; j < nbPionsCombinaison; j++)
            {
                JLabel jl = new JLabel(new ImageIcon("assets/pions/BLANC.png"));
                fillTenta.add(jl);

                JLabel jl2 = new JLabel(new ImageIcon("assets/indices/VIDE.png"));
                fillIndice.add(jl2);
            }
            fillPnl.add(fillTenta);
            fillPnl.add(fillIndice);


            mainPanel.add(fillPnl);
        }
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
            emptyCells[i].setPreferredSize(new Dimension(100, 100));
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
        tentativeCount++;
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
        //On ajoute la combinaison de couleurs au panel de la tentative
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
        //On ajoute le tableau d'indices au panel de la tentative
        archiveTentative.add(indicePanel);

        mainPanel.remove(nbTentative-tentativeCount);
        mainPanel.add(archiveTentative);
    }


    public void newManche(boolean isWin)
    {
        mainPanel.removeAll();
        tentativePanel.removeAll();
        String messageWin;

        if(isWin)
        {
            messageWin = "Gagné";
        }
        else
        {
            messageWin = "Perdu";
        }


        ImageIcon icon = new ImageIcon("assets/icone/mastermind.jpg");
        JOptionPane.showMessageDialog(null, messageWin, "Fin de la manche", JOptionPane.INFORMATION_MESSAGE, icon);
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
