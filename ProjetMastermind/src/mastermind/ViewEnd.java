package mastermind;

import javax.swing.*;
import java.awt.*;

public class ViewEnd extends JFrame {

    public ViewEnd()
    {
        super("Mastermind");
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(false);
    }
    public void end(String nomJoueur, int score)
    {
        JPanel stats = new JPanel();
        stats.setLayout(new GridLayout(5, 1));

        JLabel lblNbManches = new JLabel();
        lblNbManches.setText("Fin de partie - " + nomJoueur);


        JLabel lblNbPionsDispo = new JLabel();
        lblNbPionsDispo.setText("Score - " + score);




        stats.add(lblNbManches);

        stats.add(lblNbPionsDispo);


        JButton rejouerButton = new JButton("Rejouer");
        rejouerButton.addActionListener( actionEvent  -> {

            try {
                //faire rejouer ici
            } catch (Exception e) {
            }

        });
        stats.add(rejouerButton);
        this.add(stats);

        setVisible(true);
    }
}
