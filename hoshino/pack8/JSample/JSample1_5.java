package pack8;

import javax.swing.*;
import java.awt.*;

class JSample1_5{
  public static void main(String args[]){
    JFrame frame = new JFrame("MyTitle");
    frame.setBounds(100, 100, 600, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton btn1 = new JButton("North");
    JButton btn2 = new JButton("South");
    JButton btn3 = new JButton("West");
    JButton btn4 = new JButton("East");
    JButton btn5 = new JButton("Center");

    frame.getContentPane().add(btn1, BorderLayout.NORTH);
    frame.getContentPane().add(btn2, BorderLayout.SOUTH);
    frame.getContentPane().add(btn3, BorderLayout.WEST);
    frame.getContentPane().add(btn4, BorderLayout.EAST);
    frame.getContentPane().add(btn5, BorderLayout.CENTER);

    frame.setVisible(true);
  }
}