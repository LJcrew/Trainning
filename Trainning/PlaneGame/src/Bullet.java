import javax.swing.*;
import java.awt.*;

public class Bullet {
    //在面板上的坐标
    int x, y;
    int width = 30, height = 30;
    //定义飞机默认的速度
    int speed = 10;

    Image image = new ImageIcon("image/30021.png").getImage();

    public Bullet(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public Bullet(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
}
