// ボスについて
// 今回は一体だけ
// 普通の敵を3体倒したら出現する
// 弾を2回当てたら倒れる
// 船と接触しても、船のライフが減るだけでボスはダメージなし
// パネルの下端に来たら上に方向転換する

package pack9;

import java.awt.Image;
import java.awt.Graphics;

public class BossEnemy extends GameObject {
    public Image bossImage;
    public Image bossExImage;
    public int speed[];
    public int state[];
    public int type[];
    public int death[];
    public int slope[];
    public int life[];
    public int randomSeed;
    public int randomTime;
    public int deathWait;
    public int bosslife;
    private boolean up;

    BossEnemy() {
        super(1);
        
        speed = new int[this.num];
        state = new int[this.num];
        type  = new int[this.num];
        death = new int[this.num];
        slope = new int[this.num];
        life  = new int[this.num];
        bosslife = 10;
        for (int i = 0; i < this.num; i++) {
            life[i] = bosslife;
        }
        
        deathWait  = 12;
        randomSeed = 50;
        randomTime = (int) (((Math.random() * randomSeed) + 1) + 50);
    }

    public void newBoss(int argPanelWidth) {
        boolean oneBossHasLaunched = false;
        for (int i = 0; i < this.num; i++) {
            if (!oneBossHasLaunched && state[i] == notExist) {
                state[i] = exist;
                oneBossHasLaunched = true;
                speed[i] = (int) (( Math.random() * 5) + 1);
                type[i]  = (int) (( Math.random() * 2) + 1);
                slope[i] = (int) (((Math.random() * 3) + 1) - 1);
                int t    = (int) (( Math.random() * 2) + 1);
                if (t == 1) {
                    slope[i] = -(slope[i]);
                }
                this.X[i] = (int) ((Math.random() * (argPanelWidth - 32)) + 1);
                this.Y[i] = -32;
                randomTime = (int) (((Math.random() * randomSeed) + 1) + 50);
            }
        }
    }

    public void move(int argPanelWidth, int argPanelHeight, int argSpeed) {
        for (int i = 0; i < this.num; i++) {
            if (this.X[i] + slope[i] < 0) {
                slope[i] *= -1;
            }
            if (argPanelWidth - 32 < this.X[i] + slope[i]) {
                slope[i] *= -1;
            }
            this.X[i] += slope[i];
            int a = (int) ((Math.random() * 150) + 1);
            if (a == 3) {
                slope[i] *= -1; // 1/150 の確率で方向転換
            }
            if (a == 5 && Math.abs(slope[i]) < 8) {
                slope[i] *= -2;
            }
            if (a == 6 && Math.abs(slope[i]) > 0) {
                slope[i] /= -2;
            }
            if (state[i] > 0) {
                if (state[i] == exploding) {
                    if (death[i] == deathWait) {
                        state[i] = 0;
                    } else {
                        death[i]++;
                    }
                }
                
                if (!up) {
                    this.Y[i] += 10 + speed[i] + (argSpeed - 6);
                    if (this.Y[i] + 32 >= argPanelHeight) {
                        up = true;
                    }
                } else {
                    this.Y[i] -= 10 + speed[i] + (argSpeed - 6);
                    if (this.Y[i] <= 0) {
                        up = false;
                    }
                }
                
                /*
                if (argPanelHeight < this.Y[i]) {
                    state[i] = 0;
                } else {
                    this.Y[i] += speed[i] + (argSpeed - 6);
                }
                */
            }
            /* 機能せず
            bossBullet.discharge(this.X[i], this.Y[i]);
            */
        }
    }

    public void draw(Graphics g, MyPanel argMpanel) {
        for (int i = 0; i < this.num; i++) {
            if (state[i] == exploding) {
                g.drawImage(bossExImage, this.X[i], this.Y[i], argMpanel);
            }
            if (state[i] == exist) {
                g.drawImage(bossImage, this.X[i], this.Y[i], argMpanel);
            }
        }
    }
}
