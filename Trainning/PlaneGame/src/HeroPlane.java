import javax.swing.*;
import java.awt.*;

public class HeroPlane extends Thread{

    int x = 200, y = 600;
    int width = 50, height = 50;
    int speed = 10;

    boolean up,down,left,right;

    Image image = new ImageIcon("image/10012.png").getImage();

    public HeroPlane() {
    }

    public HeroPlane(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        while(true){
            if (up){
                y -= speed;
            }
            if (down){
                y += speed;
            }
            if (left){
                x -= speed;
            }
            if (right){
                x += speed;
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
