import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class FrontEnd implements ActionListener{

    protected JFrame frame;
    protected JButton button;
    protected Graphics graphics;
    protected Label westLabel;
    protected JButton eastButton;


    public static void main(String[] args){
        FrontEnd obj = new FrontEnd();
        obj.window();


    }
    public void window(){
        Shape shape = new Shape(70,70,100,100);

        frame = new JFrame();
        westLabel = new Label("West Label");
        eastButton = new JButton("Change Label");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eastButton.setActionCommand("eastButton");
        button = new JButton("Hello");
        button.addActionListener( this);
        eastButton.addActionListener(e -> westLabel.setText("Hello world"));

        frame.getContentPane().add(BorderLayout.SOUTH,button);
        frame.getContentPane().add(BorderLayout.CENTER, shape);
        frame.getContentPane().add(BorderLayout.WEST, westLabel);
        frame.getContentPane().add(BorderLayout.EAST, eastButton);
        frame.setSize(300,300);
        frame.setVisible(true);





    }

    public void actionPerformed(ActionEvent e){
//        button.setText("bitch");
//        if(e.getActionCommand().equals("eastButton")){
//            westLabel.setText("Hello");
//        }
        System.out.println("hi");
        frame.repaint();


    }
}

