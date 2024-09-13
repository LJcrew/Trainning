import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ExplosionAnimation extends JPanel {
    private Image[] explosionImages;
    private int currentFrame = 0;
    private Timer timer;
    private int x, y, width, height;

    public ExplosionAnimation(String imagePrefix, String imageExtension, int imageCount, int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.explosionImages = new Image[imageCount];

        // 加载爆炸图片集
        for (int i = 0; i < imageCount; i++) {
            String imagePath = imagePrefix + i + imageExtension;
            ImageIcon icon = new ImageIcon(imagePath);
            explosionImages[i] = icon.getImage();
        }

        // 设置定时器每 100 毫秒更新一次动画帧
        timer = new Timer(100, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                currentFrame++;
                if (currentFrame >= explosionImages.length) {
                    currentFrame = 0; // 可以改为停止定时器停止动画
                    timer.stop(); // 动画播放完成后停止
                }
                repaint(); // 重新绘制动画帧
            }
        });
    }

    // 启动动画
    public void startAnimation() {
        currentFrame = 0;
        timer.start();
    }

    // 绘制爆炸动画
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (explosionImages[currentFrame] != null) {
            g.drawImage(explosionImages[currentFrame], x, y, width, height, this);
        }
    }
}
