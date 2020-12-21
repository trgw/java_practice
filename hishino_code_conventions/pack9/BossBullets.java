// ボスから発射される弾丸 数フレームごとに一定間隔で発射される
// 弾は船の方向に発射されるつもりだが、アルゴリズムが不適なため未実装（右下に進む）（考える必要あり）

package pack9;

import java.awt.Image;
import java.awt.Graphics;

public class BossBullets extends GameObject {
    public int state[];
    public boolean isAlive[];
    public int shot;
    public Image bossBulletImg;
    public int bossBulletsSpeed;
    ShootingGame sg;
    Ship ship;
    BossEnemy boss;

    public BossBullets() {
        super(20);
        
        state = new int[this.num];
        isAlive = new boolean[this.num];

        bossBulletsSpeed = 10;

        sg   = new ShootingGame();
        ship = new Ship(sg.panelWidth, sg.panelHeight);
        boss = new BossEnemy();
    }

    public void move(int argPanelWidth, int argPanelHeight, int speed) {
        int dx = ship.X[0] - boss.X[0];
        int dy = ship.Y[0] - boss.Y[0];
        int r  = (int) Math.sqrt(dx * dx + dy * dy);
        for (int i = 0; i < this.num; i++) {
            if (this.isAlive[i]) {
                if (argPanelHeight - 5 < this.Y[i] || this.Y[i] < 0 || argPanelWidth - 10 < this.X[i] || this.X[i] < 0) {
                    this.isAlive[i] = false;
                } else {
                    this.X[i] += (dx / Math.abs(dx)) * bossBulletsSpeed * Math.abs((double)dx/r);
                    this.Y[i] += (dy / Math.abs(dy)) * bossBulletsSpeed * Math.abs((double)dy/r);
                }
            }
        }
    }

    public void discharge(int bossX, int bossY) {
        boolean bossBulletHasLaunched = false;
        for (int i = 0; i < this.num; i++) {
            if (!this.isAlive[i] && !bossBulletHasLaunched) {
                this.X[i] = bossX + 16;
                this.Y[i] = bossY + 32 + 1;
                this.isAlive[i] = true;
                bossBulletHasLaunched = true;
            }
        }
    }

    
    public void runDischarge(int bossX, int bossY) {
        if (shot == 4) {
            discharge(bossX, bossY);
            shot = 0;
        } else {
            shot++;
        }
    }

    public void draw(Graphics g, MyPanel argMpanel) {
        for (int i = 0; i < this.num; i++) {
            if (/*state[i] == 1*/ this.isAlive[i]) {
                g.drawImage(bossBulletImg,
                            this.X[i] - (bossBulletImg.getWidth(argMpanel) / 2),
                            this.Y[i] - bossBulletImg.getHeight(argMpanel),
                            argMpanel);
            }
        }
    }
}
