package pack8.JSample;

import javax.swing.*;
import java.awt.*;

class JSample1_4 {
  public static void main(String args[]){
    JFrame frame = new JFrame("MyTitle");
    frame.setBounds(100, 100, 600, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel p = new JPanel();
    JButton btn1 = new JButton("Save");
    JButton btn2 = new JButton("Cancel");
    JButton btn3 = new JButton("Help");

    p.add(btn1);
    p.add(btn2);
    p.add(btn3);

    frame.getContentPane().add(p, BorderLayout.SOUTH); // JFrame に JPanel を付加
    frame.setVisible(true);
  }
}