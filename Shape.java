import javax.sound.midi.ControllerEventListener;
import javax.sound.midi.ShortMessage;
import javax.swing.*;
import java.awt.*;
import java.lang.reflect.Field;
import java.util.Random;

public class Shape extends JPanel implements ControllerEventListener {
    private boolean msg = false;
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

        Random random = new Random();
        int R = random.nextInt(250);
        int G = random.nextInt(250);
        int B = random.nextInt(250);
        g.setColor(new Color(R,G,B));

        int width = random.nextInt(200);
        int height = random.nextInt(400);

        g.fillRect(x,y,width,height);
        msg = false;

    }

    @Override
    public void controlChange(ShortMessage event) {
        msg = true;
        repaint();


    }
}
