package pack8_JFrame;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.Dimension;
import java.awt.Color;
//import java.awt.Toolkit;
import java.awt.Image;
import javax.imageio.*;
import java.awt.event.ActionEvent;
//import javax.swing.ImageIcon;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainJFrame extends JFrame implements ActionListener {
    MyCanvas canvas;
    SimpleBlock SB;
    //Toolkit toolkit;
    String ballIconFilename = "pack8_JFrame/ball.gif";
    Button b[];
    Boolean initisPressed = false;

    MainJFrame() {
        setTitle("Breakout");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(340, 480));
        setLocationRelativeTo(null);
        // setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void init() {
        setLayout(null);
        setBackground(Color.white);
        SB = new SimpleBlock();
        canvas = new MyCanvas(SB);           //Canvasの定義
        canvas.setBounds(10, 10, 220, 320);  //Applet上のCanvasの位置と大きさを指定する
        add(canvas);
        //toolkit = getToolkit();
        SB.ball = getImage(ballIconFilename);
        SB.init(this, canvas);

        b = new Button[3];    //ボタンの定義
        b[0] = new Button("start");
        b[1] = new Button("stop");
        b[2] = new Button("init");

        for(int i = 0; i < 3; i++) {
            b[i].addActionListener(this);
            b[i].setBounds(60 + i * 40, 370, 40, 20);
            b[i].addActionListener(this);
            add(b[i]);
        }
        canvas.start();
    }

    /*
    private Image getImage(String filename) {
        ClassLoader cl = this.getClass().getClassLoader();
        return (new ImageIcon(cl.getResource(filename))).getImage();
    }
    */

    public Image getImage(String filename) {
        Image img;
        try {
            img = ImageIO.read(new File(filename));
            return img;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b[0]) {
            canvas.waitFlag = false;
        } else if (e.getSource() == b[1]) {
            canvas.waitFlag = true;
        } else if (e.getSource() == b[2]) {
            //canvas.waitFlag = true;
            SB.init(this, canvas);
            initisPressed = true;
        }
    }

    public static void main(String[] args) {
        MainJFrame mjf = new MainJFrame();
        mjf.init();
        mjf.setVisible(true);
    }
}
