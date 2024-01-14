package View;
import Controller.GameController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class ViewStart extends Views {
    private GameController controller;


    public ViewStart(GameController controller) {
        super("Paramètres du Mastermind");
        this.controller = controller;
        //setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLayout(new BorderLayout());

        setParameters();
    }

    public void setParameters()
    {
        loadCustomFont(Font.TRUETYPE_FONT, 20);
        Color textColor = new Color(0,0,0);
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
            JLabel texte = new JLabel(label);
            texte.setOpaque(false);
            texte.setForeground(textColor);
            leftPanel.add(texte);
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

        int i = 0;
        for (JTextField textField : textFields) {
            final int index = i;
            String defaultValue = textField.getText();
            textField.addFocusListener(new FocusAdapter() {
                @Override
                public void focusLost(FocusEvent e) {
                    JTextField textField = (JTextField) e.getSource(); // Récupère le champ de texte ayant perdu le focus
                    String text = textField.getText();
                    if (!isValidNumber(text, index)) {
                        textField.setText(defaultValue);
                    }
                }
            });
            textField.setColumns(10);
            gbc.gridx = 1; // Colonne 1 pour les champs de texte
            gbc.fill = GridBagConstraints.HORIZONTAL; // Remplissage horizontal pour les champs de texte
            textField.setForeground(Color.BLACK);
            rightPanel.add(textField, gbc);
            gbc.gridx = 0; // Réinitialiser à la colonne 0 pour les labels suivants
            i++;
        }

        JRadioButton rdbFacile = new JRadioButton("Facile");
        JRadioButton rdbClassique = new JRadioButton("Classique");
        rdbClassique.setSelected(true);
        JRadioButton rdbNumerique = new JRadioButton("Numérique");

        ButtonGroup btnGroup = new ButtonGroup();
        btnGroup.add(rdbFacile);
        btnGroup.add(rdbClassique);
        btnGroup.add(rdbNumerique);
        rdbFacile.setForeground(Color.BLACK); // Définir la couleur de la police
        rdbClassique.setForeground(Color.BLACK); // Définir la couleur de la police
        rdbNumerique.setForeground(Color.BLACK); // Définir la couleur de la police
        rdbFacile.setBackground(Color.WHITE); // Définir la couleur de la police
        rdbClassique.setBackground(Color.WHITE); // Définir la couleur de la police
        rdbNumerique.setBackground(Color.WHITE); // Définir la couleur de la police


        JPanel pnlrdb = new JPanel();
        pnlrdb.setBackground(Color.WHITE);
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
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 0, 20));
        mainPanel.setBackground(Color.WHITE);
        leftPanel.setBackground(Color.WHITE);
        rightPanel.setBackground(Color.WHITE);
        this.setVisible(true);

        JButton validerButton = new JButton("Valider");
        validerButton.setForeground(textColor);
        validerButton.setBackground(new Color(141, 153, 174));
        validerButton.setPreferredSize(new Dimension(this.getWidth(), 90));
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

    private boolean isValidNumber(String text, int typeField) {
        // Vérifier si le texte est un nombre
        try {
            if(typeField == 0)
            {
                return true;
            }
            int value = Integer.parseInt(text);
            return switch (typeField) {
                case 1 -> value > 0 && value < 6;
                case 2 -> value > 3 && value < 9;
                case 3 -> value > 1 && value < 7;
                case 4 -> value > 1 && value < 13;
                default -> false;
            };
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
