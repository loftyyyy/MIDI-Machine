import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Shape extends JPanel {
    public void setColor(Color color) {
        this.color = color;
    }

    Color color;
    int x;
    int y;

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(x,y,40,40);

    }

}
