import javax.swing.*;
public class FrontEnd {
    public static void main(String[] args){

        JFrame frame = new JFrame();

        JButton button = new JButton();
//        button.setSize(20,20);
        frame.getContentPane().add(button);
        frame.setSize(300,300);
        frame.setVisible(true);

    }
}

