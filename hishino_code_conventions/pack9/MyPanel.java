// 動作の順番：MainFrame init() → MyPanel init(), run() → ShootingGame run()...

package pack9;

import java.awt.Canvas;
import java.awt.Image;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;

import java.awt.Dimension;
import java.awt.event.MouseEvent;

public class MyPanel extends JPanel implements Runnable {
    Thread th;
    Image buffer;
    Graphics bufferg;
    boolean waitFlag;

    ShootingGame shoot;

    public MyPanel(ShootingGame shoot){
        this.shoot = shoot;
        waitFlag = false;
        th = new Thread(this);

        //setDoubleBuffered(true);

        addMouseListener(new MyMouseAdapter(shoot));
        addMouseMotionListener(new MyMouseMotionListener(shoot));
    }

    public void start(){
        Dimension d = getSize(); //
        buffer = createImage(d.width, d.height); //
        th.start();
    }

    public void run(){
        try{
            while(true) {
                if(!waitFlag) {
                    repaint();
                    shoot.run();
                }
                th.sleep(50);
            }
        } catch(Exception e) {}
    }

    public void update(Graphics g){
        paint(g);
    }

    public void paint(Graphics g) {
        if (bufferg == null) {
            bufferg = buffer.getGraphics(); //
        }
        Dimension d = getSize(); //
        //bufferg = getGraphics();
        shoot.draw(bufferg);
        g.drawImage(buffer, 0, 0, this); //
    }
}

class MyMouseMotionListener implements MouseMotionListener {
    ShootingGame shoot;

    MyMouseMotionListener(ShootingGame argShoot){
        this.shoot = argShoot;
    }

    public void mouseDragged(MouseEvent e){
        shoot.mouseDragged(e);
    }

    public void mouseMoved (MouseEvent e) {
        shoot.mouseMoved(e);
    }
}

class MyMouseAdapter extends MouseAdapter {
    ShootingGame shoot;

    MyMouseAdapter(ShootingGame argShoot){
        this.shoot = argShoot;
    }

    public void mouseEntered (MouseEvent e) {
        shoot.mouseEntered(e);
    }

    public void mouseExited (MouseEvent e) {
        shoot.mouseExited(e);
    }

    public void mousePressed (MouseEvent e) {
        shoot.mousePressed(e);
    }
}
