// Ship, Bullets, Enemies, BossEnemy, BossBullets, Stars のスーパークラス

package pack9;

import java.awt.Graphics;

public abstract class GameObject {
    public int X[];
    public int Y[];
    public int num;
    public int notExist = 0; 
    public int exist = 1; 
    public int exploding = 2;
    public int shipWidth = 32;
    public int shipHeight = 32;
    public int enemyWidth = 32;
    public int enemyHeight = 32;
    public int bulletWidth = 10;
    public int bulletHeight = 5;

    GameObject(int number) {
        this.num = number;
        X = new int[number];
        Y = new int[number];
    }

    abstract void move(int argPanelWidth, int argPanelHeight, int argSpeed);
    
    abstract void draw(Graphics g, MyPanel argMpanel);
}
