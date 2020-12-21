package pack9;

import java.awt.Image;
import java.awt.Graphics;

public class BossBullets extends GameObject {
    public int Open[];
    public int Type[];
    public int Shot;
    Image bb;

    public BossBullets() {
        super(20);
        
        Open = new int [num];
    }

    public void move(int xSize, int ySize, int speed) {
        for (int i = 0; i < num; i++) {
            if (Open[i] == 1) {
                if (Y[i] > xSize - 5) Open[i] = 0;
                else Y[i] += 3;
            }
        }
    }

    public void discharge(int bossX, int bossY) {
        int r = 0;
        for (int a = 0; a < num; a++) {
            if (Open[a] == 0 & r == 0) {
                
                X[a] = bossX + 16;
                Y[a] = bossY - 1;
                Open[a] = 1;
                r = 1;
            }
        }
    }

    public void draw(Graphics g, MyCanvas canvas) {
        for (int q = 0; q < num; q++) {
            if (Open[q] == 1) {
                g.drawImage(bb, X[q] - (bb.getWidth(canvas) / 2), Y[q] - bb.getHeight(canvas), canvas);
            }
        }
    }
}
