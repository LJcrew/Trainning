
import javax.swing.*;
import java.awt.*;

public class EnemyPlane extends Thread{
    public GameFrame gameFrame;
    public int x,y;
    public int width = 50;
    public int height = 50;
    public int speed = 2;

    public Image img = new ImageIcon("image/10032.png").getImage();

    public EnemyPlane(GameFrame gameFrame, int x, int y) {
        this.gameFrame = gameFrame;
        this.x = x;
        this.y = y;
    }

    public EnemyPlane(GameFrame gameFrame, int x, int y, int width, int height) {
        this.gameFrame = gameFrame;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    @Override
    public void run() {
        while (true) {
            if (hit()) {
                System.out.println("hitting");
                this.speed = 0;
                this.img = new ImageIcon("image/3003201.png").getImage();

//                ExplosionAnimation explosionAnimation = new ExplosionAnimation("image/300320",".png",5,this.x,this.y,this.width,this.height);
////                gameFrame.add(explosionAnimation);
//                explosionAnimation.startAnimation();
                System.out.println("hitting2");
                try {
                    this.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                gameFrame.EP.remove(this);
                break;
            }
            if (this.y >= 760){
                break;
            }
            try {
                this.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //碰撞检测
    public boolean hit(){
        Rectangle myRect = new Rectangle(this.x,this.y,this.width,this.height);
        Rectangle rect = null;
        for (int i = 0; i < gameFrame.bullets.size(); i++) {
            Bullet bullet = gameFrame.bullets.get(i);
//            System.out.println("test hit");
            rect = new Rectangle(bullet.x,bullet.y-1,bullet.width,bullet.height);
            //碰撞检测
            if (myRect.intersects(rect)){
                return true;
            }
        }
        return false;
    }
}
