package main.grid;

import javax.swing.*;


public class TestJWindow {

    public static void main(String[] args){
        SwingUtilities.invokeLater(() -> {
            //On crée une nouvelle instance de notre JDialog
            JDialog dialog = new JDialog();
            dialog.setSize(300, 200);//On lui donne une taille
            dialog.setTitle("Puissane4"); //On lui donne un titre
            dialog.setVisible(true);//On la rend visible
            dialog.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //On dit à l'application de se fermer lors du clic sur la croix
        });
    }

}

