import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;
import java.util.Vector;

public class GameFrame extends JFrame{

    HeroPlane heroPlane;

    //定义子弹的集合
    Vector<Bullet> bullets = new Vector<>();
    //定义敌机的集合
    Vector<EnemyPlane> EP = new Vector<>();

    GameFrame frame;
    public GameFrame(){
        frame = this;

        //创建英雄机
        heroPlane = new HeroPlane();
        //启动监听键盘动作线程
        heroPlane.start();
        //设置窗体的宽高
        this.setSize(500,760);
        //标题
        this.setTitle("雷霆战机");
        this.setResizable(false);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        //窗体可见
        this.setVisible(true);

        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    repaint();
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Random r = new Random();
                while (true){
                    EnemyPlane enemyPlane = new EnemyPlane(frame,r.nextInt(500),0);
                    enemyPlane.start();
                    EP.add(enemyPlane);
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }).start();
    }

    public void paint(Graphics g){
//        System.out.println("绘制画板");
        //画背景
        BufferedImage image = (BufferedImage) this.createImage(this.getSize().width,this.getSize().height);
        //高效缓存的画笔
        Graphics bi = image.getGraphics();
        bi.drawImage(new ImageIcon("image/MAP02_01.png").getImage(),0,0,null);
        bi.drawImage(heroPlane.image,heroPlane.x,heroPlane.y,heroPlane.width, heroPlane.height, null);
        //发射子弹
        for (int i = 0; i < bullets.size(); i++) {
            Bullet bullet = bullets.get(i);
            if (bullet.y > 0) {
                bi.drawImage(bullet.image, bullet.x, bullet.y -= bullet.speed, bullet.width, bullet.height, null);
            }else {
                bullets.remove(bullet);
            }
        }
        //画敌机
        for (int i = 0; i < EP.size(); i++) {
            EnemyPlane enemyPlane = EP.get(i);
            if (enemyPlane.y < 760) {
                bi.drawImage(enemyPlane.img,enemyPlane.x,enemyPlane.y += enemyPlane.speed,enemyPlane.width,enemyPlane.height, null);
            }else {
                EP.remove(enemyPlane);
            }
        }

        //生效
        g.drawImage(image,0,0,null);
    }

    public static void main(String[] args) {
        GameFrame frame = new GameFrame();
        Player player = new Player(frame);
        frame.addKeyListener(player);

    }
}
