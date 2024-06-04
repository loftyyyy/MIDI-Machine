import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import static java.awt.Font.BOLD;

public class BorderLOut {

    public static void main(String[] args){
        BorderLOut borderLOut = new BorderLOut();
        borderLOut.createWindow();

    }

    public void createWindow(){
        JFrame window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JButton northButton = new JButton("NORTH");
        JButton eastButton = new JButton("EAST");
        JButton westButton = new JButton("WEST");
        JButton southButton = new JButton("SOUTH");
        JButton centerButton = new JButton("CENTER");

//        northButton.setFont(new Font("tahoma", BOLD, 23));

        window.getContentPane().add(BorderLayout.NORTH, northButton);
        window.getContentPane().add(BorderLayout.EAST, eastButton);
        window.getContentPane().add(BorderLayout.WEST, westButton);
        window.getContentPane().add(BorderLayout.SOUTH, southButton);
        window.getContentPane().add(BorderLayout.CENTER, centerButton);



        window.setLocationRelativeTo(null);
        window.setSize(600,600);
        window.setVisible(true);


    }
}
