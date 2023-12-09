package mastermind;

import javax.swing.*;
import java.awt.*;

public class ViewGame extends JFrame implements MastermindObserver {
    GameController controller;
    JPanel mainPanel = new JPanel();
    JPanel avaibleColors = new JPanel();

    public ViewGame(GameController controller)
    {
        super("Mastermind");
        this.controller = controller;
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new GridLayout(5, 2));
        System.out.println("view game créé");

        mainPanel.setLayout(new BorderLayout());

        mainPanel.add(avaibleColors, BorderLayout.CENTER);
        setContentPane(mainPanel);
        setVisible(true);
    }



    public void showAvaibleColors(Couleurs[] couleursDispo)
    {
        System.out.println("affaichage couleurs dispo");
        avaibleColors.setLayout(new GridLayout(1, couleursDispo.length));

        for (Couleurs couleurs : couleursDispo) {
            ImageIcon imageIcon = new ImageIcon("a31-mastermind/ProjetMastermind/assets/pions/" + couleurs + ".png");
            JLabel j = new JLabel(imageIcon);
            j.setPreferredSize(new Dimension(64,64));
            System.out.println(couleurs.toString());
            avaibleColors.add(j);
        }
    }
}
