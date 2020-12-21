// マウスの動きに合わせて動く
// shift を押していれば上下にも動く


package pack9;

import java.awt.Image;
import java.awt.Graphics;
import java.awt.Dimension;
import java.awt.Color;

public class Ship extends GameObject {
    public int life;
    public int score;
    public boolean shiftFlag;
    public int mouseX;
    public int mouseY;
    public Image image;
    public int deathNumber;
    ShootingGame sg;
  
    //戦闘機の初期化処理
    Ship(int argPanelWidth, int argPanelHeight) {
        super(1);
        life = 100;
        score = 0;
        deathNumber = 0;
        this.X[0] = (argPanelWidth / 2) - 17;
        this.Y[0] = argPanelHeight - 80;

        sg = new ShootingGame();
    }

    //戦闘機の移動処理
    void move(int argPanelWidth, int argPanelHeight, int speed){
        if (mouseY < this.Y[0] - shipHeight) {
            speed = 9;
        } else {
            speed = 6;
        }
        if (shiftFlag) {
            if (50 < mouseY && mouseY < argPanelHeight) {
                this.Y[0] = mouseY - shipHeight/2;
            }
        }
        if (1 < mouseX && mouseX < argPanelWidth) {
            this.X[0] = mouseX - shipWidth/2;
        }
    }

    //戦闘機の描画処理
    void draw(Graphics g, MyPanel argMpanel){
        Dimension d = argMpanel.getSize();
        g.drawImage(image, this.X[0], this.Y[0], argMpanel);
        g.setColor(Color.yellow);
        g.drawString("Score = " + score, 5, 20);
        g.setColor(Color.orange);
        g.drawString("You have beaten " + deathNumber/*sg.deathNum*/ + " normal enemies", 5, 40);
        g.drawString("life = " + life, d.width - 60, 20);
    }

    //マウス座標を取得
    void getMousePoint(int x, int y) { //プログラミング規定的には不適
        mouseX = x;
        mouseY = y;
    }
}
