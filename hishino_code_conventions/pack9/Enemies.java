// 左右に揺れながら落下する
// 1/150フレーム の確率で左右の方向を変える
// 弾を一つ当てたら倒れる
// 船がぶつかっても倒れる

package pack9;

import java.awt.Image;
import java.awt.Graphics;

public class Enemies extends GameObject {
    public Image expImg;
    public Image image1;
    public Image image2;
    public int speed[];
    public int state[];
    public int type[];
    public int death[];
    public int slope[];
    public int randomSeed;
    public int randomTime;
    public int deathWait;
    ShootingGame sg = new ShootingGame();
    Ship ship = new Ship(sg.panelWidth, sg.panelHeight);

    Enemies() {
        super(5); // X, Y の配列の長さ：敵の最大数
        
        speed = new int[this.num];
        state = new int[this.num];
        type  = new int[this.num];
        death = new int[this.num];
        slope = new int[this.num];
        
        deathWait  = 12;
        randomSeed = 50;
        randomTime = (int) (((Math.random() * randomSeed) + 1) + 50);
    }

    //敵の出現処理
    public void newEnemy(int argPanelWidth, int timeInc) {
        boolean oneEnemyHasLaunched = false; //これがないと一気に5体出現してしまう
        for (int i = 0; i < this.num; i++) {
            if (oneEnemyHasLaunched == false && state[i] == notExist) {
                state[i] = exist;
                oneEnemyHasLaunched = true;
                speed[i] = (int) (( Math.random() * 5) + 1); // スピード 1～5 の中でランダム
                type[i]  = (int) (( Math.random() * 2) + 1); // タイプ　 1 or 2
                slope[i] = (int) (((Math.random() * 3) + 1) - 1); // スロープ 0～3 の中でランダム
                int t    = (int) (( Math.random() * 2) + 1); // 1 or 2
                if (t == 1) { //+方向に行くか-方向に行くかは1/2
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
                slope[i] *= -1;
            }
            if (a == 5 & Math.abs(slope[i]) < 8) {
                slope[i] *= -2;
            }
            if (a == 6 & Math.abs(slope[i]) > 0) {
                slope[i] /= -2;
            }
            if (state[i] > 0) {
                if (state[i] == exploding) {
                    if (death[i] == deathWait) {
                        state[i] = notExist;
                    } else {
                        death[i]++;
                    }
                }
                if (argPanelHeight - 3 < this.Y[i] + enemyHeight) { //機能せず
                    ship.life -= 10;
                }
                if (argPanelHeight < this.Y[i]) {
                    state[i] = notExist;
                } else {
                    this.Y[i] += speed[i] + (argSpeed - 6);
                }
            }
        }
    }

    public void draw(Graphics g, MyPanel argMpanel) {
        for (int i = 0; i < this.num; i++) {
            if (state[i] == exploding) {
                g.drawImage(expImg, this.X[i], this.Y[i], argMpanel);
            }
            if (state[i] == exist) {
                if (type[i] == 1) {
                    g.drawImage(image1, this.X[i], this.Y[i], argMpanel);
                } else {
                    g.drawImage(image2, this.X[i], this.Y[i], argMpanel);
                }
            }
        }
    }
}
