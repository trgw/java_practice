package pack9;

import java.awt.Image;
import java.awt.Graphics;

public class Bullets extends GameObject {
    public int Open[];
    public int Type[];
    public int Shot;
    Image slow; //Coding Convention image1, 2 で enemy の画像と被っていたので修正
    Image fast; // 〃
    boolean controlFlag;

    public Bullets(){
        super(20);
        
        Shot = 0;
        Open = new int [num];
        Type = new int [num];
    }

    public void move (int xSize, int ySize, int speed) {
        for (int i = 0; i < num; i++) {
            if (Open[i] == 1) {
                if (Y[i] < 5) {
                    Open[i] = 0;
                } else {
                    if (Type[i] == 1) {
                        Y[i] -= 2;
                    } else {
                        Y[i] -= 4;
                    }
                }
            }
        }
    }

    public void discharge(int shipX, int shipY){
        int r = 0;
        for (int a = 0; a < num; a++) {
            if (Open[a] == 0 & r == 0) {
                if (controlFlag) Type[a] = 2;
                else  Type[a] = 1;
                
                X[a] = shipX + 16;
                Y[a] = shipY - 1;
                Open[a] = 1;
                r = 1;
            }
        }
    }

    public void rDischarge(int shipX, int shipY) {
        if (Shot == 4) {
            discharge(shipX, shipY);
            Shot = 0;
        } else Shot++;
    }

    public void draw(Graphics g, MyCanvas canvas) {
        for (int q = 0; q < num; q++) {
            if (Open[q] == 1) {
                if (Type[q] == 1) {
                    g.drawImage(slow, X[q] - (slow.getWidth(canvas) / 2), Y[q] - slow.getHeight(canvas), canvas);
                } else {
                    g.drawImage(fast, X[q] - (fast.getWidth(canvas) / 2), Y[q] - fast.getHeight(canvas), canvas);
                }
            }
        }
    }
}
