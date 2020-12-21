// 船から発射される弾丸
// タイプ1, 2があり、Ctrl を押していると 2 になる
// タイプで色が異なる
// タイプ2の方が速い

package pack9;

import java.awt.Image;
import java.awt.Graphics;

public class Bullets extends GameObject {
    public boolean isAlive[];
    public int type[];
    public int shot;
    public Image slow;
    public Image fast;
    public boolean controlFlag;
    private int slowSpeed;
    private int fastSpeed;

    public Bullets(){
        super(120);
        
        shot = 0;
        isAlive = new boolean[this.num];
        type = new int[this.num];

        slowSpeed = 2;
        fastSpeed = 4;
    }

    public void move (int argPanelWidth, int argPanelHeight, int speed) {
        for (int i = 0; i < this.num; i++) {
            if (this.isAlive[i]) {
                if (this.Y[i] < 5) {
                    this.isAlive[i] = false;
                } else {
                    if (type[i] == 1) {
                        this.Y[i] -= slowSpeed;
                    } else {
                        this.Y[i] -= fastSpeed;
                    }
                }
            }
        }
    }

    public void discharge(int shipX, int shipY){
        boolean discharged = false;
        for (int i = 0; i < this.num; i++) {
            if (!this.isAlive[i] && !discharged) {
                if (controlFlag) {
                    type[i] = 2;
                } else {
                    type[i] = 1;
                }
                this.X[i] = shipX + shipWidth/2;
                this.Y[i] = shipY - 5;
                this.isAlive[i] = true;
                discharged = true;
            }
        }
    }

    public void runDischarge(int shipX, int shipY) {
        if (shot == 4) {
            discharge(shipX, shipY);
            shot = 0;
        } else {
            shot++;
        }
    }

    public void draw(Graphics g, MyPanel argMpanel) {
        for (int i = 0; i < this.num; i++) {
            if (this.isAlive[i]) {
                if (type[i] == 1) {
                    g.drawImage(slow, this.X[i] - (slow.getWidth(argMpanel) / 2), this.Y[i] - slow.getHeight(argMpanel), argMpanel);
                } else {
                    g.drawImage(fast, this.X[i] - (fast.getWidth(argMpanel) / 2), this.Y[i] - fast.getHeight(argMpanel), argMpanel);
                }
            }
        }
    }
}
