package mastermind;
import javax.swing.*;
import java.awt.*;

public class ViewStart extends Views {
    GameController controller;
    JTextField[] textFields;


    public ViewStart(GameController controller)
    {
        super("Mastermind");
        this.controller = controller;
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());


        setParameters();
    }

    public void setParameters()
    {
        loadCustomFont(Font.TRUETYPE_FONT, 20);
        JPanel mainPanel = new JPanel(new GridLayout(1, 2)); // GridLayout avec 1 ligne et 2 colonnes

        JPanel leftPanel = new JPanel(new GridLayout(6, 1)); // GridLayout pour la colonne de gauche

        // Ajout de composants dans la colonne de gauche
        String[] labels = {
                "Nom du joueur",
                "Nombre de manches",
                "Nombre de pions disponibles",
                "Nombre de pions dans les combinaisons",
                "Nombre de tentatives",
                "Mode d'affichage des indices"
        };

        for (String label : labels) {
            leftPanel.add(new JLabel(label));
        }

        JPanel rightPanel = new JPanel(new GridBagLayout()); // GridBagLayout pour la colonne de droite

        // Ajout de composants dans la colonne de droite avec des contraintes pour ajuster l'alignement
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = GridBagConstraints.RELATIVE;
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(5, 10, 5, 5);

        JTextField[] textFields = {
                new JTextField(""),
                new JTextField("3"),
                new JTextField("8"),
                new JTextField("4"),
                new JTextField("10")
        };

        for (JTextField textField : textFields) {
            textField.setColumns(10);
            gbc.gridx = 1; // Colonne 1 pour les champs de texte
            gbc.fill = GridBagConstraints.HORIZONTAL; // Remplissage horizontal pour les champs de texte
            rightPanel.add(textField, gbc);
            gbc.gridx = 0; // Réinitialiser à la colonne 0 pour les labels suivants
        }

        JRadioButton rdbFacile = new JRadioButton("Facile");
        JRadioButton rdbClassique = new JRadioButton("Classique");
        rdbClassique.setSelected(true);
        JRadioButton rdbNumerique = new JRadioButton("Numérique");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdbFacile);
        btnGroup.add(rdbClassique);
        btnGroup.add(rdbNumerique);


        JPanel pnlrdb = new JPanel();
        pnlrdb.add(rdbFacile);
        pnlrdb.add(rdbClassique);
        pnlrdb.add(rdbNumerique);

        gbc.gridx = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        rightPanel.add(pnlrdb, gbc);
        gbc.gridx = 0;


        mainPanel.add(leftPanel);
        mainPanel.add(rightPanel);

        this.add(mainPanel);
        this.setVisible(true);

        JButton validerButton = new JButton("Valider");
        validerButton.setForeground(Color.WHITE);
        validerButton.setBackground(Color.BLUE);
        validerButton.setPreferredSize(new Dimension(this.getWidth(), 100));
        validerButton.addActionListener( actionEvent  -> {
            String nomJoueur = textFields[0].getText();
            int nbManches = Integer.parseInt(textFields[1].getText());
            int nbPionsDispo = Integer.parseInt(textFields[2].getText());
            int nbPionsCombi = Integer.parseInt(textFields[3].getText());
            int nbTenta = Integer.parseInt(textFields[4].getText());

            int typeIndice = 0;
            for (Component rdb: pnlrdb.getComponents())
            {
                if(((JRadioButton)rdb).isSelected())
                {
                    break;
                }
                typeIndice++;
            }

            try {
                this.dispose();
                controller.createPartie(nomJoueur, nbManches, nbPionsDispo, nbPionsCombi, nbTenta, typeIndice);
            } catch (Exception e) {
            }

        });
        this.add(validerButton, BorderLayout.SOUTH);

        setCustomFontForComponents(this);
        this.pack();
    }



}
