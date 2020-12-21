package pack9;

import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class Stars extends GameObject {

    public Stars (int xSize, int ySize) {
        super(30);
        
        for (int a = 0; a < num; a++) {
            X[a] = (int) ((Math.random() * xSize - 1) + 1);
            Y[a] = (int) ((Math.random() * ySize - 1) + 1);
        }
    }
        
    public void move(int xSize, int ySize, int speed) {
        for (int a = 0; a < num; a++) {
            if (Y[a] + 1 > ySize - (speed * 2 )) {
                Y[a] = 0;
            } else {
                Y[a] += speed;
            }
        }
    }
        
    public void draw(Graphics g, MyCanvas myCanvas) { // Graphics g を引数に取る意味？, ShootingGame.java の drawメソッド で使用
        Dimension d = myCanvas.getSize();
        g.setColor(Color.black);
        g.fillRect(0, 0, d.width, d.height);

        g.setColor(Color.blue);
        for (int a = 0; a < num; a++) {
            g.drawRect(X[a], Y[a], 1, 1); //(x, y, width, height)
        }
    }
}
