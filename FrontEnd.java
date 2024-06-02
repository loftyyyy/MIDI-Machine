import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;

public class FrontEnd {

    protected JFrame frame;
    protected JButton button;
    protected Graphics graphics;
    protected Label westLabel;
    protected JButton eastButton;
    protected Color currentColor = Color.green;
    protected Shape shape;


    public static void main(String[] args){
        FrontEnd obj = new FrontEnd();
        obj.window();


    }
    public void window(){
        shape = new Shape();
        shape.setColor(currentColor);

        frame = new JFrame();
        westLabel = new Label("West Label");
        eastButton = new JButton("Change Label");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        eastButton.setActionCommand("eastButton");
        button = new JButton("Hello");
        button.addActionListener( new ColorListener());
        eastButton.addActionListener(new LabelListener());

        frame.getContentPane().add(BorderLayout.SOUTH,button);
        frame.getContentPane().add(BorderLayout.CENTER, shape);
        frame.getContentPane().add(BorderLayout.WEST, westLabel);
        frame.getContentPane().add(BorderLayout.EAST, eastButton);
        frame.setSize(300,300);
        frame.setVisible(true);

    }

    public class LabelListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton sourceButton = (JButton) e.getSource();
            eastButton.setText("I've been edited");

        }
    }

    public class ColorListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e){
            JButton sourceButton = (JButton) e.getSource();
            currentColor = (currentColor == Color.BLUE) ? Color.RED : Color.BLUE;
            shape.setColor(currentColor);
            frame.repaint();
        }
    }

}

