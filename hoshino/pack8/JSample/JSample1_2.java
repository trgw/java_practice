package pack8.JSample;

import javax.swing.JFrame;

class JSample1_2{
  public static void main(String args[]){
    JFrame frame = new JFrame("MyTitle");
    frame.setBounds(100, 100, 600, 900); //bound：境界
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); //フレームを閉じた際にプログラムも終了させる
    frame.setVisible(true); //フレームが見えるようにする このコードがなければ閉じる操作をすることもなく CloseOperation でプログラムが終了する
  }
}