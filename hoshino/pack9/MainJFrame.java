package pack9;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
//import java.awt.Window;

public class MainJFrame extends JFrame implements ActionListener {
    MyCanvas canvas;
    ShootingGame shoot;
    Button b[];

    MainJFrame() {
        setTitle("shoot enemy!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(900, 600));
        // setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void init(){
        setLayout(null);
        setBackground(Color.lightGray);

        shoot = new ShootingGame();
        canvas = new MyCanvas(shoot);
        canvas.setBounds(0, 0, shoot.xSize, shoot.ySize); 
        add(canvas);
        shoot.init(this, canvas);

        b = new Button[3];
        b[0] = new Button("start");
        b[1] = new Button("stop");
        b[2] = new Button("init");

        for(int i = 0; i < 3; i++) {
            b[i].addActionListener(this);
            b[i].setBounds(650 + i * 40, 120, 40, 20);
            b[i].addActionListener(this);
            add(b[i]);
        }
        canvas.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b[0]) {
            canvas.waitFlag = false;
        } else if (e.getSource() == b[1]) {
            canvas.waitFlag = true;
        } else if (e.getSource() == b[2]) {
            shoot.init(this, canvas);
        }
    }

    public static void main(String[] args) {
        MainJFrame mjf = new MainJFrame();
        mjf.init();
        mjf.setVisible(true);
    }
}
