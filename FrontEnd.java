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


    public static void main(String[] args){
        FrontEnd obj = new FrontEnd();
        obj.window();


    }
    public void window(){

        frame = new JFrame();
        button = new JButton("Hello");
        button.addActionListener( this);
        frame.getContentPane().add(button);
        frame.setSize(300,300);
        frame.setVisible(true);





    }

    public void actionPerformed(ActionEvent e){
        button.setText("bitch");


    }
}

