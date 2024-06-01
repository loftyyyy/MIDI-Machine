import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;

public class Shape extends JPanel {
    protected int x;
    protected int y;
    protected int width;
    protected int height;


    public Shape(int x, int y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void paintComponent(Graphics g){
            g.setColor(Color.green);
            g.fillRect(x,y,width,height);

    }

}
