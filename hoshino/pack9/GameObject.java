package pack9;

import java.awt.Graphics;

public abstract class GameObject {
    int X[];
    int Y[];
    int num;

    GameObject(int number) { // Coding Convention: |GameObject(int num) {
        this.num = number;   //                    |    this.num = num;
        X = new int[number]; //         ←          |    X = new int[num];
        Y = new int[number]; //                    |    Y = new int[num];
    }

    abstract void move(int xSize, int ySize, int speed); // どういう意味？
    
    abstract void draw(Graphics g, MyCanvas canvas);
}
