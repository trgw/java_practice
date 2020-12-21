package pack9;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.Image;
import javax.imageio.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ShootingGame {
    boolean outFlag; //マウスがキャンバス外にあるとき衝突判定を行わないようにするためのフラグ
    int timeInc;     //次の敵の作成までの時間
    
    Ship ship;
    Stars stars;
    Bullets bullets;
    Enemies enemies;
    BossEnemy boss;
    BossBullets bossBullets;
  
    public int xSize = 640, ySize = 480;
    public int speed = 6;
  
    MainJFrame mj;
    MyCanvas canvas;

    int deathNum;

    public void init (MainJFrame mJFrame, MyCanvas myCanvas) { // Code Convention: 元：public void init (MainJFrame mj, MyCanvas canvas)
        this.mj = mJFrame;
        this.canvas = myCanvas;
    
        outFlag = false;
    
        timeInc = 0;
        ship    = new Ship(xSize, ySize);
        enemies = new Enemies();
        boss    = new BossEnemy();
        stars   = new Stars(xSize, ySize);
        bullets = new Bullets();
        bossBullets   = new BossBullets();
        deathNum = 0;
    
        ship.image      = getImage("pack9/gif/ship.gif");
        enemies.image1  = getImage("pack9/gif/enemy1.gif");
        enemies.image2  = getImage("pack9/gif/enemy2.gif");
        enemies.exImage = getImage("pack9/gif/ex.gif");
        boss.bossImage  = getImage("pack9/gif/boss.gif");
        bullets.slow    = getImage("pack9/gif/b1.gif");
        bullets.fast    = getImage("pack9/gif/b2.gif");
        bossBullets.bb        = getImage("pack9/gif/bb.png");
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
        if (ship.life == 0) init(mj, canvas);  //shipの体力が0になった時点で再スタート
        timeInc++;
        if (timeInc == enemies.randomTime) {
            enemies.newEnemy(xSize, timeInc);
            timeInc = 0;
        }
        if (timeInc == boss.randomTime) {
            boss.newBoss(xSize, timeInc);
            timeInc = 0;
        }
        enemies.move(xSize, ySize, speed);
        boss.move(xSize, ySize, speed);
        stars.move(xSize, ySize, speed);
        bullets.move(xSize, ySize, speed);
        bossBullets.move(xSize, ySize, speed);
        collisions();  //衝突判定
    }

    public void draw(Graphics g) {
        stars.draw(g, canvas);
        enemies.draw(g, canvas);
        bullets.draw(g, canvas);
        boss.draw(g, canvas);
        bossBullets.draw(g, canvas);
        ship.draw(g, canvas);
    }

    public void collisions() {
        if (outFlag) {
            for (int i = 0; i < 5; i++) {
                
                for (int j = 0; j < bullets.num; j++) { // 船と弾丸？
                    if (bullets.Open[j] == 1) {
                        if (bullets.Y[j] > ship.Y[0] & bullets.Y[j] < ship.Y[0] + 32) {
                            if (bullets.X[j] > ship.X[0] & bullets.X[j] < ship.X[0] + 32) {
                                bullets.Open[j] = 0;
                                ship.life -= 5;
                            }
                        }
                    }
                    if (bullets.Open[j] == 1 & enemies.Open[i] == 1) { // 弾丸が敵に当たった時
                        if (bullets.Y[j] > enemies.Y[i] & bullets.Y[j] < enemies.Y[i] + 32) {
                            if (bullets.X[j] > enemies.X[i] & bullets.X[j] < enemies.X[i] + 32) {
                                enemies.Death[i] = 0;
                                enemies.Open[i]  = 2;
                                bullets.Open[j]  = 0;
                                ship.score++;
                                deathNum++; //自分で追加
                            }
                        }
                    }
                    if (bossBullets.Open[j] == 1) { // ボスが船に当たった時
                        if (bossBullets.Y[j] > ship.Y[0] & bossBullets.Y[j] < ship.Y[0] + 32) {
                            if (bossBullets.X[j] > ship.X[0] & bossBullets.X[j] < ship.X[0] + 32) {
                                bossBullets.Open[j] = 0;
                                ship.life -= 5;
                            }
                        }
                    }
                    if (bossBullets.Open[j] == 1) { // 上 0 → i 何が違う？
                        if (bossBullets.Y[j] > ship.Y[i] & bossBullets.Y[j] < ship.Y[i] + 32) {
                            if (bossBullets.X[j] > ship.X[i] & bossBullets.X[j] < ship.X[i] + 32) {
                                bossBullets.Open[j]  = 0;
                                ship.life -= 5;
                            }
                        }
                    }
                    if (/* bullets.Open[j] == 1 & */ boss.Open[i] == 1) { // 弾丸がボスに当たった時
                        if (bullets.Y[j] > boss.Y[i] & bullets.Y[j] < boss.Y[i] + 32) {
                            if (bullets.X[j] > boss.X[i] & bullets.X[j] < boss.X[i] + 32) {
                                boss.Death[i] = 0;
                                boss.Open[i]  = 2;
                                bullets.Open[j]  = 0;
                                ship.score += 5;
                            }
                        }
                    }
                }

                if (enemies.Open[i] == 1) { // 敵が船に当たった時
                    if ((ship.X[0] + 32 > enemies.X[i]) & (ship.X[0] < enemies.X[i] + 32)) {
                        if ((ship.Y[0] + 32 > enemies.Y[i]) & (ship.Y[0] < enemies.Y[i] + 36)) {
                            enemies.Death[i] = 0;
                            enemies.Open[i] = 2;
                            ship.life -= 5;
                        }
                    }
                }
                if (boss.Open[i] == 1) { // 船がボスに当たった時
                    if ((ship.X[0] + 32 > boss.X[i]) & (ship.X[0] < boss.X[i] + 32)) {
                        if ((ship.Y[0] + 32 > boss.Y[i]) & (ship.Y[0] < boss.Y[i] + 36)) {
                            boss.Death[i] = 0;
                            boss.Open[i] = 2;
                            ship.life -= 5;
                        }
                    }
                }
            }
        }
    }

    public void mouseDragged (MouseEvent e) {
        if (e.isControlDown()) bullets.controlFlag = true;
        else bullets.controlFlag = false;
        bullets.rDischarge(ship.X[0], ship.Y[0]);
    
        ship.getMousePoint(e.getX(), e.getY());
        if(e.isShiftDown()) ship.shiftFlag = true;
        else ship.shiftFlag = false;
        ship.move(xSize, ySize, speed);
    }

    public void mouseMoved (MouseEvent e) {
        ship.getMousePoint(e.getX(), e.getY());
        if(e.isShiftDown()) ship.shiftFlag = true;
        else ship.shiftFlag = false;
        ship.move(xSize, ySize, speed);
    }

    public void mouseEntered (MouseEvent e) {outFlag = true;}

    public void mouseExited (MouseEvent e) {outFlag = false;}

    public void mousePressed (MouseEvent e) {
        if (e.isControlDown()) bullets.controlFlag = true;
        else bullets.controlFlag = false;
        bullets.discharge(ship.X[0], ship.Y[0]);
    }
}
