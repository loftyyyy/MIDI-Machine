import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Shape extends JPanel {
    Color color;

    public Shape(Color color){
        this.color = color;

    }


    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(3,3,40,40);

    }

}
