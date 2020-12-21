package pack9;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class Ship extends GameObject {
    public int life;
    public int score;
    boolean shiftFlag;
    int xx, yy;
    Image image;

    ShootingGame sg = new ShootingGame();
  
    //戦闘機の初期化処理
    Ship(int xSize, int ySize){
        super(1);
        life = 100;
        score = 0;
        X[0] = (xSize / 2) - 17;
        Y[0] =  ySize - 80;
    }

    //戦闘機の移動処理
    void move(int xSize, int ySize, int speed){
        if (yy < Y[0] - 32) speed = 9;
        else speed = 6;

        if (shiftFlag) {
        if (yy < ySize & yy > 100)  Y[0] = yy - 16;
        }
        if (xx < xSize & xx > 1)  X[0] = xx - 16;
    }

    //戦闘機の描画処理
    void draw(Graphics g, MyCanvas canvas){
        Dimension d = canvas.getSize();
        g.drawImage(image, X[0], Y[0], canvas);
        g.setColor(Color.yellow);
        g.drawString("Score = " + score + "  deathNum = " + sg.deathNum, 5, 20);
        g.drawString("life = " + life + "%", d.width - 80, 20);
    }

    //マウス座標を取得
    void getMousePoint(int mouseX, int mouseY) { //プログラミング規定的には不適
        this.xx = mouseX;
        this.yy = mouseY;
    }
}
