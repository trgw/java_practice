package pack9;

import java.awt.Image;
import java.awt.Graphics;

public class BossEnemy extends GameObject {
    Image bossImage;
    Image bossExImage;
    public int Speed[];
    public int Open[]; //
    public int Type[]; //
    public int Death[];
    public int Slope[];//
    int randomSeed;
    int randomTime;
    int deathWait;

    BossEnemy() {
        super(5);
        
        Speed = new int[num];
        Open  = new int[num];
        Type  = new int[num];
        Death = new int[num];
        Slope = new int[num];
        
        deathWait  = 12;
        randomSeed = 50;
        randomTime = (int) (((Math.random() * randomSeed) + 1) + 50);
    }

    public void newBoss(int xSize, int timeInc) {
        int r = 0;
        for (int a = 0; a < num; a++) {
            if (r == 0 & Open[a] == 0) {
                Open[a] = 1;
                r = 1;
                Speed[a] = (int) (( Math.random() * 5) + 1);
                Type[a]  = (int) (( Math.random() * 2) + 1);
                Slope[a] = (int) (((Math.random() * 3) + 1) - 1);
                int t    = (int) (( Math.random() * 2) + 1);
                if (t == 1) Slope[a] = -(Slope[a]);
                
                X[a] = (int) ((Math.random() * (xSize - 32)) + 1);
                Y[a] = -32;
                randomTime = (int) (((Math.random() * randomSeed) + 1) + 50);
            }
        }
    }

    public void move(int xSize, int ySize, int speed) {
        double r = Math.random();
        for (int i = 0; i < num; i++) {
            if (X[i] + Slope[i] < 0)          Slope[i] *= -1;
            if (X[i] + Slope[i] > xSize - 32) Slope[i] *= -1;
            X[i] += Slope[i];
            if (0    < r && r < 0.4) Y[i] += Math.abs(Slope[i]);
            //if (0.25 < r && r < 0.5 ) Y[i] -= Slope[i];
            int a = (int) ((Math.random() * 150) + 1);
            if (a == 3)                          Slope[i] *= -1; // 1/150 の確率で
            if (a == 5 & Math.abs(Slope[i]) < 8) Slope[i] *= -2;
            if (a == 6 & Math.abs(Slope[i]) > 0) Slope[i] /= -2;
            if (Open[i] > 0) {
                if (Open[i] == 2) {
                    if (Death[i] == deathWait) {
                        Open[i] = 0;
                    } else {
                        Death[i]++;
                    }
                }
                if (Y[i] > ySize) Open[i] = 0;
                else Y[i] += Speed[i] + (speed - 6);
            }
        }
    }

    public void draw(Graphics g, MyCanvas canvas) {
        for (int i = 0; i < num; i++) {
            if (Open[i] == 2) {
                g.drawImage(bossExImage, X[i], Y[i], canvas);
            }
            if (Open[i] == 1) {
                g.drawImage(bossImage, X[i], Y[i], canvas);
            }
        }
    }
}
