package pack8;

import javax.swing.JFrame;

import org.w3c.dom.events.MouseEvent;

import java.awt.Graphics;
import java.awt.Color;

public class Breakout /* implements MouseEvent */ {

    Graphics g;
    int block_width;
    int block_height;
    int[] block_x;
    int[] block_y;
    int ball_x;
    int ball_y;
    Color c;

    Breakout() {
        setFrame();
        setBlock();
        setPaddle();
    }

    public void setFrame() {
        JFrame frame = new JFrame("Breakout");
        frame.setBounds(100, 100, 600, 900); // 500, 800
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //フレームを閉じた際にプログラムも終了させる
        frame.setVisible(true); //フレームが見えるようにする このコードがなければ閉じる操作をすることもなく CloseOperation でプログラムが終了する
    }
    
    public void setBlock() {
        for (int i = 0; i < 10; i++) {
            g.setColor(Color.red);
            g.drawRect(50 * i, 30, 50, 10);
            g.fillRect(50 * i, 30, 50, 10);
        }
    }

    public void setPaddle() {

    }

    public void setBall() {

    }




    public static void main(String args[]) {
        Breakout bo = new Breakout();
        bo.setFrame();
    }
}