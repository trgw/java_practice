// パネルの背景
// 青い点々が落下する
// 当たり判定なし

package pack9;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class Stars extends GameObject {

    public Stars (int argPanelWidth, int argPanelHeight) {
        super(30);
        
        for (int i = 0; i < this.num; i++) {
            this.X[i] = (int) ((Math.random() * argPanelWidth  - 1) + 1);
            this.Y[i] = (int) ((Math.random() * argPanelHeight - 1) + 1);
        }
    }
        
    public void move(int argPanelWidth, int argPanelHeight, int speed) {
        for (int i = 0; i < this.num; i++) {
            if (argPanelHeight - (speed * 2) < this.Y[i] + 1) {
                this.Y[i] = 0;
            } else {
                this.Y[i] += speed;
            }
        }
    }
        
    public void draw(Graphics g, MyPanel argMpanel) { //ShootingGame.java の drawメソッド で使用
        Dimension d = argMpanel.getSize();
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.blue);
        for (int i = 0; i < this.num; i++) {
            g.drawRect(this.X[i], this.Y[i], 1, 1); //(x, y, width, height)
        }
    }
}
