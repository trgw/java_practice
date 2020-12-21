// 動作の順番：MainFrame init() → MyPanel init(), run() → ShootingGame run()...
// ボスは敵を3体倒したときに一度だけ出ます。
//
// フィールド、メソッドともに多いので改善できたらしたい

package pack9;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Image;
import javax.imageio.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShootingGame {
    public final static int panelWidth  = 640;
    public final static int panelHeight = 480;
    public final static int speed = 6;
    public int deathNum;
    public boolean bossExist;
    MainFrame mainframe;
    MyPanel mpanel;
    Ship ship;
    Enemies enemy;
    Bullets bullet;
    BossEnemy boss;
    BossBullets bossBullet;
    Stars stars;
    private boolean outFlag; //マウスがキャンバス外にあるとき衝突判定を行わないようにするためのフラグ
    private int timeInc;     //次の敵の作成までの時間
    private int timeIncBoss;
    private int num2launchBoss = 10;
    private boolean bossHasLaunched;
    private int notExist = 0; 
    private int exist = 1; 
    private int exploding = 2;
    public int shipWidth = 32;
    public int shipHeight = 32;
    public int enemyWidth = 32;
    public int enemyHeight = 32;
    public int bulletWidth = 10;
    public int bulletHeight = 5;
    private int[] shipXCenter;
    private int[] shipYCenter;
    private int[] bulletXCenter;
    private int[] bulletYCenter;
    private int[] enemyXCenter;
    private int[] enemyYCenter;
    private int[] bossXCenter;
    private int[] bossYCenter;
    private int[] bossBulletXCenter;
    private int[] bossBulletYCenter;

    public void init (MainFrame argMainframe, MyPanel argMpanel) {
        this.mainframe = argMainframe;
        this.mpanel = argMpanel;
    
        timeInc = 0;
        ship    = new Ship(panelWidth, panelHeight);
        bullet  = new Bullets();
        enemy = new Enemies();
        boss    = new BossEnemy();
        bossBullet = new BossBullets();
        stars   = new Stars(panelWidth, panelHeight);

        shipXCenter = new int[ship.num];
        shipYCenter = new int[ship.num];
        bulletXCenter = new int[bullet.num];
        bulletYCenter = new int[bullet.num];
        enemyXCenter = new int[enemy.num];
        enemyYCenter = new int[enemy.num];
        bossXCenter = new int[boss.num];
        bossYCenter = new int[boss.num];
        bossBulletXCenter = new int[bossBullet.num];
        bossBulletYCenter = new int[bossBullet.num];
    
        ship.image       = getImage("pack9/gif/ship.gif");
        enemy.image1     = getImage("pack9/gif/enemy1.gif");
        enemy.image2     = getImage("pack9/gif/enemy2.gif");
        enemy.expImg     = getImage("pack9/gif/explosion.gif");
        boss.bossImage   = getImage("pack9/gif/boss.gif");
        boss.bossExImage = getImage("pack9/gif/explosion.gif");
        bullet.slow      = getImage("pack9/gif/b1.gif");
        bullet.fast      = getImage("pack9/gif/b2.gif");
        bossBullet.bossBulletImg = getImage("pack9/gif/bb.png");

        deathNum = 0;
        bossExist = false;
        bossHasLaunched = false;
    }

    public Image getImage(String filename) {
        Image img;
        try {
            img = ImageIO.read(new File(filename));
            return img;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void run() {
        //shipの体力が0になった時点で再スタート
        if (ship.life <= 0) {
            init(mainframe, mpanel);
        }

        timeInc++;
        if (timeInc == enemy.randomTime) {
            enemy.newEnemy(panelWidth, timeInc);
            timeInc = 0;
        }

        if (deathNum == num2launchBoss && !bossExist && !bossHasLaunched) {
            boss.newBoss(panelWidth);
            bossHasLaunched = true;
            bossExist = true;
        }
        // 数フレーム刻みでボスが弾を発射
        if (bossExist) {
            timeIncBoss++;
            if (timeIncBoss == 2) {
                bossBullet.runDischarge(boss.X[0], boss.Y[0]);
                timeIncBoss = 0;
            }
        }

        stars.move(panelWidth, panelHeight, speed);
        enemy.move(panelWidth, panelHeight, speed);
        bullet.move(panelWidth, panelHeight, speed);
        boss.move(panelWidth, panelHeight, speed);
        bossBullet.move(panelWidth, panelHeight, speed);

        for (int i = 0; i < ship.num; i++) {
            shipXCenter[i] = ship.X[i] + shipWidth/2;
            shipYCenter[i] = ship.Y[i] + shipHeight/2;
        }
        for (int i = 0; i < bullet.num; i++) {
            bulletXCenter[i] = bullet.X[i] + bulletWidth/2;
            bulletYCenter[i] = bullet.Y[i] + bulletHeight/2;
        }
        for (int i = 0; i < enemy.num; i++) {
            enemyXCenter[i] = enemy.X[i] + enemyWidth/2;
            enemyYCenter[i] = enemy.Y[i] + enemyHeight/2;
        }
        for (int i = 0; i < boss.num; i++) {
            bossXCenter[i] = boss.X[i] + enemyWidth/2;
            bossYCenter[i] = boss.Y[i] + enemyHeight/2;
        }
        for (int i = 0; i < bossBullet.num; i++) {
            bossBulletXCenter[i] = bossBullet.X[i] + bulletWidth/2;
            bossBulletYCenter[i] = bossBullet.Y[i] + bulletHeight/2;
        }

        collisions();  //衝突判定
    }

    public void draw(Graphics g) {
        stars.draw(g, mpanel);
        ship.draw(g, mpanel);
        enemy.draw(g, mpanel);
        bullet.draw(g, mpanel);
        boss.draw(g, mpanel);
        bossBullet.draw(g, mpanel);
    }


    public void collisions() {
        if (outFlag) {
            bulletsEnemies();
            bulletsShip();
            enemiesShip();
            shipBoss();
            bulletsBoss();
            bossbulletsBoss();
        }
    }

    /* collisions() ------------------------------------------------------ 始 */

    // 弾丸が敵に当たった時
    public void bulletsEnemies() {
        for (int i = 0; i < enemy.num; i++) {
            for (int j = 0; j < bullet.num; j++) {
                if (bullet.isAlive[j] && enemy.state[i] == exist) {
                    if (enemy.Y[i] < bulletYCenter[j] && bulletYCenter[j] < enemy.Y[i] + enemyHeight) {
                        if (enemy.X[i] < bulletXCenter[j] && bulletXCenter[j] < enemy.X[i] + enemyWidth) {
                            enemy.death[i] = 0;
                            enemy.state[i] = exploding;
                            bullet.isAlive[j] = false;
                            ship.score += 100;
                            deathNum++; // Shipクラス から sg.deathNum のようにこの値を取れなかった, ただ, このクラスの run() で用いている.
                            ship.deathNumber++;
                        }
                    }
                }
            }
        }
    }

    // 船と弾丸が当たった時
    public void bulletsShip() {
        for (int i = 0; i < bullet.num; i++) {
            if (bullet.isAlive[i]) {
                if (ship.Y[0] + shipHeight/4 < bulletYCenter[i] && bulletYCenter[i] < ship.Y[0] + 3 * shipHeight/4) {
                    if (ship.X[0] + shipWidth/4 < bulletXCenter[i] && bulletXCenter[i] < ship.X[0] + 3 * shipWidth/4) {
                        bullet.isAlive[i] = false;
                        if (bullet.type[i] == 1) {
                            ship.life -= 5;
                        } else {
                            ship.life -= 10;
                        }
                    }
                }
            }
        }
    }

    // 敵が船に当たった時
    public void enemiesShip() {
        for (int i = 0; i < enemy.num; i++) {
            if (enemy.state[i] == exist) {
                if (enemy.X[i] + enemyWidth/4 < shipXCenter[0] && shipXCenter[0] < enemy.X[i] + 3 * enemyWidth/4) {
                    if (enemy.Y[i] + enemyHeight/4 < shipYCenter[0] && shipYCenter[0] < enemy.Y[i] + 3 * enemyHeight/4) {
                        enemy.death[i] = 0;
                        enemy.state[i] = exploding;
                        ship.life -= 10;
                    }
                }
            }
        }
    }

    // 船がボスに当たった時 当たってもボスは倒れない
    public void shipBoss() {
        for (int i = 0; i < boss.num; i++) {
            if (boss.state[i] == exist) {
                if (boss.X[i] + enemyWidth/4 < shipXCenter[0] && shipXCenter[0] < boss.X[i] + 3 * enemyWidth/4) {
                    if (boss.Y[i] + enemyHeight/4 < shipYCenter[0] && shipYCenter[0] < boss.Y[i] + 3 * shipHeight/4) {
                        ship.life -= 30;
                    }
                }
            }
        }
    }

    // 弾丸がボスに当たった時
    public void bulletsBoss() {
        for (int i = 0; i < boss.num; i++) {
            for (int j = 0; j < bullet.num; j++) {
                if (bullet.isAlive[j] && boss.state[i] == exist) {
                    if (boss.Y[i] < bulletYCenter[j] && bulletYCenter[j] < boss.Y[i] + enemyHeight) {
                        if (boss.X[i] < bulletXCenter[j] && bulletXCenter[j] < boss.X[i] + enemyWidth) {
                            boss.life[i]--;
                            bullet.isAlive[j] = false;
                            if (boss.life[i] == 0) {
                                boss.death[i] = 0;
                                boss.state[i] = exploding;
                                bossExist = false;
                                ship.score += 10000;
                            }
                        }
                    }
                }
            }
        }
    }

    // ボスの弾と船が当たった時
    public void bossbulletsBoss() {
        for (int i = 0; i < bossBullet.num; i++) {
            if (bossBullet.isAlive[i]) { 
                if (ship.Y[0] + shipHeight/4 < bossBulletYCenter[i] && bossBulletYCenter[i] < ship.Y[0] + 3 * shipHeight/4) {
                    if (ship.X[0] + shipWidth/4 < bossBulletXCenter[i] && bossBulletXCenter[i] < ship.X[0] + 3 * shipWidth/4) {
                        bossBullet.isAlive[i] = false;
                        ship.life -= 15;
                    }
                }
            }
        }
    }

    /* collisions() ------------------------------------------------------ 終 */

    public void mouseDragged (MouseEvent e) {
        if (e.isControlDown()) {
            bullet.controlFlag = true;
        } else {
            bullet.controlFlag = false;
        }
        bullet.runDischarge(ship.X[0], ship.Y[0]);
    
        ship.getMousePoint(e.getX(), e.getY());
        if (e.isShiftDown()) {
            ship.shiftFlag = true;
        } else {
            ship.shiftFlag = false;
        }
        ship.move(panelWidth, panelHeight, speed);
    }

    // Shift が押されていた場合、船はY方向にも動くことができる
    public void mouseMoved (MouseEvent e) {
        ship.getMousePoint(e.getX(), e.getY());
        if (e.isShiftDown()) {
            ship.shiftFlag = true;
        } else {
            ship.shiftFlag = false;
        }
        ship.move(panelWidth, panelHeight, speed);
    }

    public void mouseEntered (MouseEvent e) {
        outFlag = true;
    }

    public void mouseExited (MouseEvent e) {
        outFlag = false;
    }

    // Ctrl を押していた場合、弾はタイプ2となる
    public void mousePressed (MouseEvent e) {
        if (e.isControlDown()) {
            bullet.controlFlag = true;
        } else {
            bullet.controlFlag = false;
        }
        bullet.discharge(ship.X[0], ship.Y[0]);
    }
}
