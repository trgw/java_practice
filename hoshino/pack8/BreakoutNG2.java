package pack8;

import java.awt.event.KeyListener;
import java.awt.Rectangle;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Color;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;

public class BreakoutNG2 extends JPanel implements KeyListener {
    private Rectangle paddle;
    private int panelWidth;
    private int panelHeight;

    public BreakoutNG2() {
        addKeyListener(this);
    }

    @Override
    public void paintComponent (Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D)g;
        if (paddle == null) {
            paddle = new Rectangle(panelWidth, panelHeight);
            paddle.setLocation(getWidth()/2, getHeight() - 20);
        }
        g2d.setColor(Color.WHITE);
        g2d.fillRect(0, 0, getWidth(), getHeight());
        // グラデーションパターンの描画
    }

    @Override
    public void KeyPressed(KeyEvent e) {
        int pdx = 10;
        int keyCode = e.getKeyCode();
        switch (keyCode) {
            case KeyEvent.VK_LEFT:
            if (paddle.x < pdx) {
                paddle.setLocation(0, paddle.y);
            } else {
                paddle.setLocation(paddle.x - pdx, paddle.y);
            }
            repaint();
            break;

            case KeyEvent.VK_RIGHT:
            if (getWidth() - paddle.x - paddle.width < pdx) {
                paddle.setLocation(getWidth() - paddle.width, paddle.y);
            } else {
                paddle.setLocation(paddle.x + pdx, paddle.y);
            }
            repaint();
            break;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        
    }
}