import javax.swing.*;
import javax.swing.plaf.basic.BasicOptionPaneUI;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.util.Random;

public class FrontEnd {

    protected JFrame frame;
    protected JButton button;
    protected Graphics graphics;
    protected Label westLabel;
    protected JButton eastButton;
    protected Color currentColor = Color.green;
    protected int x = 3;
    protected int y = 4;
    protected Shape shape;


    public static void main(String[] args){
        FrontEnd obj = new FrontEnd();
        obj.window();


    }
    public void window(){
        shape = new Shape();
        shape.setColor(currentColor);
        shape.setY(y);
        shape.setX(x);

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
    //TODO: Statics doesn't need objects!

    public class LabelListener  implements ActionListener {
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
            Random random = new Random();

            currentColor = (currentColor == Color.BLUE) ? Color.RED : Color.BLUE;
            shape.setColor(currentColor);
            shape.setY(random.nextInt(100));
            shape.setX(random.nextInt(100));
            frame.repaint();
        }
    }

}

