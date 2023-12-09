package mastermind;
import javax.swing.*;
import java.awt.*;
public class ViewStart extends JFrame{
    GameController controller;


    public ViewStart(GameController controller)
    {
        super("Mastermind");
        this.controller = controller;
        setSize(400, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);


        setParameters();
        setVisible(true);
    }

    public void setParameters()
    {
        JPanel parametre = new JPanel();
        parametre.setLayout(new GridLayout(5, 2));

        JLabel lblNbManches = new JLabel();
        lblNbManches.setText("Nombre de manches : ");


        JLabel lblNbPionsDispo = new JLabel();
        lblNbPionsDispo.setText("Nombre de pions disponibles : ");

        JLabel lblNbPionsCombinaison = new JLabel();
        lblNbPionsCombinaison.setText("Nombre de combinaisons : ");

        JLabel lblNbTentatives = new JLabel();
        lblNbTentatives.setText("Nombre de tentatives : ");


        JTextField txtNbManches = new JTextField();

        JTextField txtNbPionsDispo = new JTextField();

        JTextField txtNbPionsCombinaison = new JTextField();

        JTextField txtNbTentatives = new JTextField();


        parametre.add(lblNbManches);
        parametre.add(txtNbManches);

        parametre.add(lblNbPionsDispo);
        parametre.add(txtNbPionsDispo);

        parametre.add(lblNbPionsCombinaison);
        parametre.add(txtNbPionsCombinaison);

        parametre.add(lblNbTentatives);
        parametre.add(txtNbTentatives);


        JButton validerButton = new JButton("Valider");
        validerButton.addActionListener( actionEvent  -> {
            int nbManches = Integer.parseInt(txtNbManches.getText());
            int nbPionsDispo = Integer.parseInt(txtNbPionsDispo.getText());
            int nbPionsCombi = Integer.parseInt(txtNbPionsCombinaison.getText());
            int nbTenta = Integer.parseInt(txtNbTentatives.getText());

            try {
                this.dispose();
                controller.createPartie(nbManches, nbPionsDispo, nbPionsCombi, nbTenta);
            } catch (Exception e) {
            }

        });
        parametre.add(validerButton);
        this.add(parametre);
    }
}
