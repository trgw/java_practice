package pack7;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class MyCanvas extends Canvas implements Runnable{
	Thread th;
	Image buffer;
	Graphics bufferg;
	Spring sp;
	boolean waitFlag;

	public MyCanvas(Spring sp){
		this.sp = sp;
		waitFlag = false;
		th = new Thread(this);
	}

	public void start(){
	  Dimension d =  getSize();
	  buffer = createImage(d.width, d.height);
	  th.start();
	}

	public void run(){ //<--oスレッドで連続的に実行する部分
	  try{
	    while(true){
	      if(!waitFlag){ //waitFlagはtrueの場合repaintメソッドとrungeメソッドを処理しないことで動作を止めることを意味しています．
	        repaint();  //o描画を行う
	        sp.runge();  //rungeメソッドは刻み幅秒後の変位xの値を推定します．
	      }
	      th.sleep(100);
	    }
	  }catch(Exception e){}
	}

	public void update(Graphics g){
	  paint(g);
	}

	public void paint(Graphics g){
	    if(bufferg == null) bufferg = buffer.getGraphics();
	    Dimension d = getSize();
	    drawBackGround(bufferg, d);    //o背景を塗りつぶす
	    drawAxis(bufferg, d);          //o軸を描画する
	    sp.setPoint();      // setPointメソッドはばねの分割点を計算します．
	    drawSpring(bufferg, d);
	    g.drawImage(buffer, 0, 0, this);
	}

	//-------- o画面上にシミュレーション結果を描画するためのメソッド群　---------------------
	public void drawSpring(Graphics g,Dimension d){ //drawSpringはキャンバスの中心を原点とするばねを描きます．
		g.setColor(Color.gray);
		for(int i = 0; i < sp.N - 1; i++)
			g.drawLine(d.width/2 + sp.n_x[i], d.height/2 - sp.n_y[i],
			                      d.width/2 + sp.n_x[i + 1], d.height/2 - sp.n_y[i + 1]);
			g.drawLine(d.width/2 + sp.n_x[sp.N - 1], d.height/2 - sp.n_y[sp.N - 1],
			                      d.width/2 + sp.n_x[sp.N - 1]+20,d.height/2 - sp.n_y[sp.N - 1]);
			g.setColor(Color.blue);
			g.fillOval(d.width/2 + sp.n_x[sp.N - 1] - 20 + 40, d.height/2 - sp.n_y[sp.N - 1] - 20, 40, 40);
	}

	public void drawBackGround(Graphics g,Dimension d){
	    g.setColor(Color.white);
	    g.fillRect(0,0,d.width,d.height);
	}

	public void drawAxis(Graphics g,Dimension d){
	    g.setColor(Color.lightGray);
	    g.drawRect(0,0,d.width-1,d.height-1);
	    g.drawLine(0,d.height/2,d.width,d.height/2);
	    g.drawLine(d.width/2,0,d.width/2,d.height-1);
	}
}