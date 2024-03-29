package View;

import Controller.GameController;
import Model.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

public class ViewGame extends Views implements MastermindObserver {
    private GameController controller;
    private JPanel mainPanel = new JPanel();
    private JPanel tentativePanel = new JPanel();
    private JPanel avaibleColors = new JPanel();
    private JLabel lblNumManche;
    private JLabel lblScore;
    private JScrollPane scrollPane;

    private JLabel selectedPion;
    private JLabel[] pions;
    private JLabel[] emptyCells;
    private ImageIcon draggedIcon;
    private int width=1000;
    private int length=800;
    Color couleurTexte = new Color(241, 250, 238);
    Color couleurFond = new Color(69, 123, 157);
    Color couleurFondFonce = new Color(29, 53, 87);

    public ViewGame(GameController controller) {

        super("Mastermind");

        getContentPane().setBackground(couleurFondFonce);
        this.controller = controller;
        setSize(width, length);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        JPanel windowsPanel = new JPanel();
        windowsPanel.setLayout(new BoxLayout(windowsPanel, BoxLayout.PAGE_AXIS));
        windowsPanel.setBackground(couleurFondFonce);


        // Utilisation de BoxLayout pour mainPanel
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        //Et d'un scrollPane pour la barre de défilement
        scrollPane = new JScrollPane(mainPanel);
        scrollPane.setOpaque(false);

        scrollPane.setPreferredSize(new Dimension(width, 500));
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        //Utilisation d'un nouveau Layout pour afficher un titre en haut de la page

        JPanel pnlInfos = new JPanel(new BorderLayout());
        JPanel title = new JPanel(new FlowLayout());
        title.setBorder(BorderFactory.createEmptyBorder(20, 150, 0, 0));
        title.setBackground(couleurFondFonce);
        JLabel titre = new JLabel("Manche ");
        titre.setHorizontalAlignment(SwingConstants.CENTER);
        titre.setForeground(couleurTexte);

        Font customFont = loadCustomFont(Font.TRUETYPE_FONT, 40);
        setCustomFontForComponent(titre, customFont);
        title.add(titre);


        lblNumManche = new JLabel();
        lblNumManche.setFont(new Font("Dialog", Font.BOLD, 40));
        lblNumManche.setForeground(couleurTexte);
        Border paddingBorder = BorderFactory.createEmptyBorder(0, 0, 10, 0);
        lblNumManche.setBorder(paddingBorder);
        title.add(lblNumManche);

        pnlInfos.add(title, BorderLayout.CENTER);


        JPanel pnlScore = new JPanel(new FlowLayout());
        pnlScore.setBorder(BorderFactory.createEmptyBorder(20, 0, 0, 20));
        pnlScore.setBackground(couleurFondFonce);
        JLabel lblScoreTitre = new JLabel("Score ");
        lblScoreTitre.setHorizontalAlignment(SwingConstants.CENTER);
        lblScoreTitre.setForeground(couleurTexte);

        Font customFont2 = loadCustomFont(Font.TRUETYPE_FONT, 30);
        setCustomFontForComponent(lblScoreTitre, customFont2);
        pnlScore.add(lblScoreTitre);


        lblScore = new JLabel("0");
        lblScore.setFont(new Font("Dialog", Font.BOLD, 30));
        lblScore.setForeground(couleurTexte);
        lblScore.setBorder(paddingBorder);
        pnlScore.add(lblScore);

        pnlInfos.add(pnlScore, BorderLayout.LINE_END);



        windowsPanel.add(pnlInfos, BorderLayout.PAGE_START);


        windowsPanel.add(scrollPane, BorderLayout.PAGE_START); // Affichage des tentatives passées + indices

        // Création d'un box layout encapsulant les couleurs dispos et la tentative
        JPanel actionsJoueur = new JPanel();
        actionsJoueur.setLayout(new BoxLayout(actionsJoueur, BoxLayout.Y_AXIS));
        actionsJoueur.add(tentativePanel); // Tentative actuelle
        actionsJoueur.add(avaibleColors); // Couleurs dispos
        windowsPanel.add(actionsJoueur, BorderLayout.PAGE_END);

        windowsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        add(windowsPanel);
        setVisible(false);
    }

    public void init(Manche mancheActuelle, Integer typeIndice, int numManche)
    {
        lblScore.setText("0");
        //Je set le numéro de la manche.
        lblNumManche.setText(String.valueOf(numManche));

        setVisible(false);
        int nbTentatives = mancheActuelle.getNbTentativesMax();

        int nbPionsCombinaison = mancheActuelle.getNbPionsCombi();

        System.out.println(nbTentatives + " " + nbPionsCombinaison);

        tentativePanel.setLayout(new GridLayout(1, nbPionsCombinaison + 3));
        tentativePanel.setPreferredSize(new Dimension(width, 70));
        tentativePanel.setBackground(couleurFondFonce);


        for (int i = 0; i < nbTentatives; i++)
        {
            JPanel fillPnl = new JPanel();
            fillPnl.setBorder(new LineBorder(Color.BLACK));
            fillPnl.setLayout(new GridLayout(1, 2));


            JPanel fillTenta = new JPanel();
            fillTenta.setBorder(new LineBorder(Color.BLACK));
            fillTenta.setLayout(new GridLayout(1, nbPionsCombinaison));
            fillTenta.setBackground(couleurFond);


            JPanel fillIndice = new JPanel();
            fillIndice.setBorder(new LineBorder(Color.BLACK));
            fillIndice.setLayout(new GridLayout(1, nbPionsCombinaison));
            fillIndice.setBackground(couleurFond);

            for (int j = 0; j < nbPionsCombinaison; j++) {
                JLabel jl = new JLabel(new ImageIcon(getClass().getResource("/assets/pions/BLANC.png")));
                fillTenta.add(jl);
            }

            //En fonction du mode d'affichage des indices, on met soit des ronds vides, soit on affiche rien
            if(typeIndice<2) {
                for (int j = 0; j < nbPionsCombinaison; j++) {
                    JLabel jl2 = new JLabel(new ImageIcon(getClass().getResource("/assets/indices/VIDE.png")));
                    fillIndice.add(jl2);
                }
            }
            fillPnl.add(fillTenta);
            fillPnl.add(fillIndice);
            fillPnl.setPreferredSize(new Dimension(width-60, 70));
            fillPnl.setBackground(couleurFond);

            mainPanel.add(fillPnl);
        }
        setVisible(true);

        // Positionne la barre de défilement en bas
        JScrollBar verticalScrollBar = scrollPane.getVerticalScrollBar();
        verticalScrollBar.setValue(verticalScrollBar.getMaximum());
        verticalScrollBar.setUI(new CustomScrollBarUI());
    }



    public void showAvaibleColors(Couleurs[] couleursDispo)
    {

        avaibleColors.setLayout(new GridLayout(1, couleursDispo.length));
        avaibleColors.setPreferredSize(new Dimension(width, 70));
        avaibleColors.setBackground(couleurFondFonce);

        pions = new JLabel[couleursDispo.length];
        for (int i = 0; i < pions.length; i++) {
            pions[i] = new JLabel(new ImageIcon(getClass().getResource("/assets/pions/" + couleursDispo[i] + ".png")));
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
        validerButton.setForeground(Color.WHITE);
        validerButton.setBackground(Color.GREEN.darker().darker());
        validerButton.addActionListener( actionEvent  -> {

            Combinaison tentative = new Combinaison(nbPionsCombi);
            int i = 0;
            for (JLabel j: emptyCells)
            {
                tentative.setCouleur(((Couleurs)j.getClientProperty("couleur")), i);

                i++;
            }

            //Le bouton permet d'appeller la fonction de verification dans le game controller
            try {
                controller.validerTentative(tentative);
            }
            catch (Exception e) {
                System.out.println("Erreur tentative : " + tentative.getCombinaison().length);
                System.out.println(e.getMessage());
            }
        });
        tentativePanel.add(validerButton);


        JButton resetButton = new JButton("Reset tentative");
        resetButton.setForeground(Color.WHITE);
        resetButton.setBackground(Color.BLUE);
        resetButton.addActionListener( actionEvent  -> {

            //Le bouton permet d'appeller la méthode pour aller à la manche suivante dans le game controller
            try {
                for (JLabel j: emptyCells)
                {
                    j.putClientProperty("couleur", null);
                    j.setIcon(null);
                }
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        tentativePanel.add(resetButton);


        JButton giveUpButton = new JButton("Abandonner");
        giveUpButton.setForeground(Color.WHITE);
        giveUpButton.setBackground(Color.RED.darker().darker());
        giveUpButton.addActionListener( actionEvent  -> {

            //Le bouton permet d'appeller la méthode pour aller à la manche suivante dans le game controller
            try {
                controller.giveUpManche();
            }
            catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        tentativePanel.add(giveUpButton);
        //Changer la police des 3 boutons
        //loadCustomFont(Font.TRUETYPE_FONT, 20);

        //Container container = new Container();
        Component[] container = new Component[3];
        int i = 0;
        for(Component c : tentativePanel.getComponents())
        {
            if(c instanceof JButton)
            {
                container[i] = c;
                i++;
            }
        }

        loadCustomFont(Font.TRUETYPE_FONT, 15);
        //System.out.println("Appel changement police... : " + ((JButton)container[0]).getText() + " | " + ((JButton)container[1]).getText() + " | " +((JButton)container[2]).getText());
        setCustomFontForComponents(container);

        setVisible(true);


    }

    public void addTentativeUpdateIndice(Manche mancheActuelle, Tentative tentative, Indice[] indices, Integer typeIndice, int score)
    {
        lblScore.setText(String.valueOf(score));
        for (JLabel j: emptyCells)
        {
            j.putClientProperty("couleur", null);
            j.setIcon(null);
        }
        JPanel archiveTentative = new JPanel();
        archiveTentative.setLayout(new GridLayout(1, 2));

        JPanel tentativePanel = new JPanel();
        tentativePanel.setLayout(new GridLayout(1, mancheActuelle.getNbPionsCombi()));

        JPanel indicePanel = new JPanel();
        indicePanel.setBorder(new LineBorder(Color.BLACK));
        indicePanel.setLayout(new GridLayout(1, mancheActuelle.getNbPionsCombi()));


        for(int i = 0; i < tentative.getCombinaison().getLength(); i++)
        {
            ImageIcon originalIcon = new ImageIcon(getClass().getResource("/assets/pions/" + tentative.getCombinaison().getCombinaison()[i] + ".png"));
            JLabel duplicatedLabel = new JLabel(originalIcon);
            tentativePanel.add(duplicatedLabel);
        }
        //On ajoute la combinaison de couleurs au panel de la tentative
        tentativePanel.setBorder(new LineBorder(Color.BLACK));
        tentativePanel.setBackground(couleurFond);
        archiveTentative.add(tentativePanel);

        if(typeIndice==0){
            for (Indice index : indices) {
                JLabel j = new JLabel();
                switch (index) {
                    case NOIR -> j.setIcon(new ImageIcon(getClass().getResource("/assets/indices/NOIR.png")));
                    case BLANC -> j.setIcon(new ImageIcon(getClass().getResource("/assets/indices/BLANC.png")));
                    case VIDE -> j.setIcon(new ImageIcon(getClass().getResource("/assets/indices/VIDE.png")));
                }
                indicePanel.add(j);
            }
        }
        else if(typeIndice==1){
            int nbNoirs=0;
            int nbBlancs=0;
            for (Indice index : indices) {
                switch (index) {
                    case NOIR -> nbNoirs++;
                    case BLANC -> nbBlancs++;
                }
            }
            for(int i =0; i<nbNoirs; i++){
                JLabel j = new JLabel();
                j.setIcon(new ImageIcon(getClass().getResource("/assets/indices/NOIR.png")));
                indicePanel.add(j);
            }
            for(int i =0; i<nbBlancs; i++){
                JLabel j = new JLabel();
                j.setIcon(new ImageIcon(getClass().getResource("/assets/indices/BLANC.png")));
                indicePanel.add(j);
            }
            //On rempli le reste avec des indices "vides"
            for(int i = 0; i< indices.length - (nbNoirs+nbBlancs); i++){
                JLabel j = new JLabel();
                j.setIcon(new ImageIcon(getClass().getResource("/assets/indices/VIDE.png")));
                indicePanel.add(j);
            }
        }
        else if(typeIndice==2){
            int nbNoirs=0;
            int nbBlancs=0;
            for (Indice index : indices) {
                switch (index) {
                    case NOIR -> nbNoirs++;
                    case BLANC -> nbBlancs++;
                }
            }
            JLabel LabelNoirs = new JLabel("Pions bien placés : "+ nbNoirs);
            JLabel LabelBlancs = new JLabel("Pions dans la combinaison : "+ nbBlancs);
            indicePanel.add(LabelNoirs);
            indicePanel.add(LabelBlancs);
        }

        //On ajoute le tableau d'indices au panel de la tentative
        indicePanel.setBackground(couleurFond);
        indicePanel.setBorder(new LineBorder(Color.BLACK));
        archiveTentative.add(indicePanel);

        mainPanel.remove(mancheActuelle.getNbTentativesMax()-mancheActuelle.getNbTenta());
        mainPanel.add(archiveTentative);
    }


    public void newManche(boolean isWin, int nbTentatives)
    {
        mainPanel.removeAll();
        tentativePanel.removeAll();
        JLabel messageWin = new JLabel();
        //messageWin.setFont(loadCustomFont(Font.TRUETYPE_FONT, 30));

        if(isWin)
        {
            messageWin.setText("Gagné");
        }
        else
        {
            messageWin.setText("Perdu");
        }

        messageWin.setText(messageWin.getText() + " avec " + nbTentatives + " tentatives");


        ImageIcon icon = new ImageIcon(getClass().getResource("/assets/icone/mastermind.jpg"));
        JOptionPane.showMessageDialog(null, messageWin, "Fin de la manche", JOptionPane.INFORMATION_MESSAGE, icon);
        setVisible(false);
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
