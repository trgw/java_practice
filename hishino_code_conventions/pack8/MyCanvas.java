package pack8_JFrame;

import java.awt.event.*;
import java.awt.*;

//ダブルバッファリングを備えたCanvasの作成
public class MyCanvas extends Canvas implements Runnable{
    Thread th;
    Image buffer;
    Graphics bufferg;
    boolean waitFlag;
    SimpleBlock SB;
    MainJFrame mj = new MainJFrame();

    public MyCanvas(SimpleBlock SB){
        this.SB = SB;
        waitFlag = true;
        th = new Thread(this);
        addMouseMotionListener(new MyMouseMotionAdapter(SB));
    }

    public void start(){
        //Dimension d =  getSize();
        //buffer = createImage(d.width, d.height);
        th.start();
    }

    public void run(){
        try{
            while(true){
                if (!waitFlag) {
                    repaint();
                    SB.run();
                }
                /*
                if (mj.initisPressed = true) {
                    waitFlag = true;
                }
                */
            th.sleep(30);
            }
        } catch(Exception e) {}
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g){
        //if(bufferg == null) bufferg = buffer.getGraphics();
        bufferg = getGraphics();
        Dimension d = getSize();
        SB.drawBackGround(bufferg, d);  //背景，ボール，ブロック，ラケット，スコア－の描画
        SB.drawBall(bufferg, this);
        SB.drawBlock(bufferg);
        SB.drawRacket(bufferg);
        SB.drawScore(bufferg);
        //g.drawImage(buffer, 0, 0, this);
        if (SB.score == 30 || SB.num <= 0) waitFlag = true;
    }
}

class MyMouseMotionAdapter extends MouseMotionAdapter {
    SimpleBlock SB;

    public MyMouseMotionAdapter(SimpleBlock SB) {
        this.SB = SB;
    }

    public void mouseMoved(MouseEvent e) {
        SB.mouseMoved(e);    //マウスの動きを読みとります．
    }
}