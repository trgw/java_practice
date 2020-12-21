package Breakout;

import javax.swing.JFrame;

public class Main{
	public static void main(String[] args){
		JFrame frame = new JFrame();
		MainFrame panel = new MainFrame();
		frame.add(panel);
		
		frame.setTitle("Breakout");
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.pack();
		frame.setBounds(200, 200, MainFrame.WIDTH+15, MainFrame.HEIGHT+39);
		panel.start();
	}
}