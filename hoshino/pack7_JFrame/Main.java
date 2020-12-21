package pack7_JFrame;

import javax.swing.JFrame;
import java.awt.Dimension; //abstract window toolkit
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.Button;
import java.awt.event.ActionEvent;

public class Main extends JFrame implements ActionListener {
    MyCanvas canvas;
    Button b[];
    Spring sp;

    Main() {
        setTitle("Motion of a spring by RungeKutta");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(640, 480));
        // setResizable(false);
        pack();
        setLocationRelativeTo(null);
    }

    public void init() {
        setLayout(null);
        setBackground(Color.white);
        sp = new Spring();
        canvas = new MyCanvas(sp);
        canvas.setBounds(10, 10, 640, 240);
        add(canvas);
        canvas.start();
    
        b = new Button[3];
        b[0] = new Button("start");
        b[1] = new Button("stop");
        b[2] = new Button("init");
    
        for(int i = 0; i < 3; i++) {
          b[i].addActionListener(this);
          b[i].setBounds(660 + i * 40, 120, 40, 20);
          b[i].addActionListener(this);
          add(b[i]);
        }
    }

    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == b[0]) {
          canvas.waitFlag = false;
        } else if(e.getSource() == b[1]) {
          canvas.waitFlag = true;
        } else if(e.getSource() == b[2]) {
          sp.init();
        }
    }

    public static void main(String[] args) {
        Main m = new Main();
        m.init();
        m.setVisible(true);
    }
}
