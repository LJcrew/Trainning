import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class Player extends KeyAdapter {

    GameFrame gameFrame;
    public Player(GameFrame gameFrame) {
        this.gameFrame = gameFrame;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
//        System.out.println(keyCode);
        //38,40,37,39
        switch (keyCode){
            case 38:
                gameFrame.heroPlane.up = true;
                break;
            case 40:
                gameFrame.heroPlane.down = true;
                break;
            case 37:
                gameFrame.heroPlane.left = true;
                break;
            case 39:
                gameFrame.heroPlane.right = true;
                break;
            case 66:
                addBullet();
                break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();
        //38,40,37,39
        switch (keyCode){
            case 38:
                gameFrame.heroPlane.up = false;
                break;
            case 40:
                gameFrame.heroPlane.down = false;
                break;
            case 37:
                gameFrame.heroPlane.left = false;
                break;
            case 39:
                gameFrame.heroPlane.right = false;
                break;
        }
    }

    /*
    装弹
     */
    public void addBullet(){
        gameFrame.bullets.add(new Bullet(gameFrame.heroPlane.x + 10,gameFrame.heroPlane.y - 20));
    }

    public void removeBullet(){

    }
}
