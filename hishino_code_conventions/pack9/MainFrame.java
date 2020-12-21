// 動作の順番：MainFrame init() → MyFrame init(), run() → ShootingGame run()...

package pack9;

import javax.swing.JFrame;
import java.awt.event.ActionListener;
import java.awt.Dimension;
import java.awt.Button;
import java.awt.Color;
import java.awt.event.ActionEvent;
//import java.awt.Window;

public class MainFrame extends JFrame implements ActionListener {
    MyPanel mpanel;
    ShootingGame shoot;
    Button b[];

    MainFrame() {
        setTitle("shoot enemy!");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(650, 600));
        // setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void init(){
        setLayout(null);
        setBackground(Color.lightGray);

        shoot = new ShootingGame();
        mpanel = new MyPanel(shoot);
        mpanel.setBounds(0, 0, shoot.panelWidth, shoot.panelHeight); 
        add(mpanel);
        shoot.init(this, mpanel);

        b = new Button[3];
        b[0] = new Button("start");
        b[1] = new Button("stop");
        b[2] = new Button("init");

        for(int i = 0; i < 3; i++) {
            b[i].addActionListener(this);
            b[i].setBounds(250 + i * 40, 510, 40, 20);
            b[i].addActionListener(this);
            add(b[i]);
        }
        mpanel.start();
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == b[0]) {
            mpanel.waitFlag = false;
        } else if (e.getSource() == b[1]) {
            mpanel.waitFlag = true;
        } else if (e.getSource() == b[2]) {
            shoot.init(this, mpanel);
        }
    }

    public static void main(String[] args) {
        MainFrame m = new MainFrame();
        m.init();
        m.setVisible(true);
    }
}
