package mastermind;

import javax.swing.*;
import java.awt.*;

public class ViewGame extends JFrame implements MastermindObserver {
    GameController controller;

    public ViewGame(GameController controller)
    {
        super("Mastermind");
        this.controller = controller;
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setLayout(new GridLayout(5, 2));
    }

    public void showAvaibleColors(Couleurs[] couleursDispo)
    {
        JPanel avaibleColors = new JPanel();
        avaibleColors.setLayout(new GridLayout(1, couleursDispo.length));
        for(int i = 0; i < couleursDispo.length; i++)
        {
            /*ImageIcon imageIcon = new ImageIcon("assets/pions/" + couleursDispo[i] + ".png");
            JLabel j = new JLabel(imageIcon);*/
            JLabel j = new JLabel(couleursDispo[i].toString());
            avaibleColors.add(j);
        }
        add(avaibleColors);
    }
}
