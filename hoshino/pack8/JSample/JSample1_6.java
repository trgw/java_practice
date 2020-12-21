package pack8.JSample;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

class JSample1_6 extends JFrame implements ActionListener{
  public static void main(String args[]){
    JSample1_6 frame = new JSample1_6("MyTitle");
    frame.setVisible(true);
  }

  JSample1_6(String title){
    setTitle(title);
    setBounds(100, 100, 600, 400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    JPanel p = new JPanel();
    JButton btn = new JButton("Push");
    btn.addActionListener(this);

    p.add(btn);
    getContentPane().add(p, BorderLayout.CENTER);
  }

  public void actionPerformed(ActionEvent e){
    JLabel label = new JLabel("You have pushed a button");
    JOptionPane.showMessageDialog(this, label);
  }
}