package pack8.JSample;

import javax.swing.JFrame;
import javax.swing.JButton;
import java.awt.BorderLayout;

class JSample1_3{
  public static void main(String args[]){
    JFrame frame = new JFrame("MyTitle");
    frame.setBounds(100, 100, 600, 400);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JButton button = new JButton("Push");

    frame.getContentPane().add(button, BorderLayout.NORTH); //getContentPane().外しても変わらない
    frame.setVisible(true);
  }
}