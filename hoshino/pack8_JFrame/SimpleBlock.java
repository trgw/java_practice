package pack8_JFrame;

import javax.swing.JFrame;
import java.awt.MediaTracker;
import java.awt.Image;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Canvas;
import java.awt.Font;

public class SimpleBlock {
    MediaTracker mt;
    Image ball;                             // 表示するボールの絵を入れる変数
    int num = 3;                            // ボールの残数
    int bx, by;                             // ボールの位置（ｘ座標、ｙ座標）
    int rx = 120, ry = 280;                 // ラケットの位置（ｘ座標、ｙ座標）
    int ballWidth = 16, ballHeight = 16;    // ボールの絵の幅、高さ
    int racketWidth = 50, racketHeight = 8; // ラケットの絵の幅、高さ
    int margin = 10;                        // 端まで行かないよう余裕を取る
    int score;                              // 点数
    boolean blockFlag[] = new boolean[30];  // ブロックがあるかどうかの配列
    int blockX[] = new int[30];             // ブロックのｘ座標
    int blockY[] = new int[30];             // ブロックのｙ座標
    int blockWidth = 16, blockHeight = 12;  // ブロックの幅、高さ
    int oldx = 0;
    Thread kicker = null;                   // アニメーションのためのスレッド変数
    int SPEED = 4;                          //ボールの進む量
    int dx, dy;                             //ボールの進む量（ｘ増分、ｙ増分）
    Dimension d;                            //Canvasのサイズ

    JFrame jf;
    MyCanvas mc = new MyCanvas(this);
    
    public void init(JFrame jframe, MyCanvas canvas){
        d  = canvas.getSize();
        dx = SPEED;
        dy = SPEED;
    
        mt = new MediaTracker(canvas);
        mt.addImage(ball, 0);
    
        //ブロックの位置（ｘ、ｙ座標）の設定
        int k = 0;  //ブロック番号
        int yy;     // 段毎のｙ座標
        for(int i = 0; i < 3; i++) {
            yy = i * (blockHeight + 3) + margin * 3 ;
            for(int j = 0; j < 10; j++) {
                blockX[k] = j * (blockWidth + 4) + margin + 2;
                blockY[k] = yy;
                blockFlag[k] = true; // ブロックがある
                k += 1;
            }
        }
    
        num = 3;
        score = 0;
    
        /* ボールの初期位置の設定 */
        bx = margin + (int)(Math.random() * (float)(d.width - (margin * 2 + ballWidth + 1)));
        by = 130;
    }

    public void mouseMoved(MouseEvent e) {
        rx = e.getX();	// ラケットの位置の更新
        /* ラケットがコートを出ないための処理 */
        if (rx < margin) rx = margin;
        if (rx + racketWidth > d.width - margin) rx = d.width - margin - racketWidth;
    }

    public void run() {
        /*全てのイメージの読み込みを待つ */
        try {
            mt.waitForID(0);   //ID番号0の画像が読み込まれるまで待機します
        } catch (InterruptedException e) {}
    
        racketColProcess();    //ボールとラケットとの衝突処理
        wallColProcess();      //ボールと壁との衝突処理
        blockColProcess();     //ボールとブロックの衝突処理
    
        bx += dx;
        by += dy;
    }

    // ブロックに当たったときの処理
    public void blockColProcess() {
        for(int i = 0; i < 30; i++ ) {
            if (blockFlag[i] == true) {
                if (by + ballHeight >= blockY[i] && by <= blockY[i] + blockHeight
                        && bx + ballWidth >= blockX[i] && bx <= blockX[i] + blockWidth) {
                    dy = -dy;             // ブロックに当たったら反転
                    score += 1;           // 得点の加算
                    if (SPEED <= 10) SPEED++;
                    blockFlag[i] = false; // ブロックを消す
                }
            }
        }
    }

    // ボールがラケットに当たったときの処理
    public void racketColProcess(){
        if (by + ballHeight >= ry && by + ballHeight <= ry + racketHeight && bx + ballWidth >= rx && bx <= rx + racketWidth) {
            dy = -SPEED;                                                        // ラケットに当たったら上へ返す
            if (bx < rx || bx + ballWidth > rx + racketWidth) {                 // ラケットの端に当たった時
                oldx = dx;
                if (oldx == 0) {                                                // 垂直に来たボール
                    if (bx < rx) dx = -SPEED;                                   // 左端に当たったら左斜め上に返す
                    else if (bx + ballWidth > rx + racketWidth) dx = +SPEED;    // 右端に当たったら右斜め上に返す
                } else dx = 0;                                                  // 斜めに来たボールは垂直に返す
            }
        }
    }

    // 左端、右端、上端に来たときの壁との衝突処理
    public void wallColProcess(){
        if (bx < 0 + margin) dx = SPEED;                          // 左端に来たら反転
        else if (bx + ballWidth > d.width - margin) dx = -SPEED;  // 右端に来たら反転
        else if (by < 0 + margin) dy = SPEED;                     // 上端に来たら反転
        else if (by + ballHeight > d.height - margin) {           // ラケットの下へ行ったときの処理
            // 下端に来たらボールを初期位置へ
            bx = margin + (int)(Math.random() * (float)(d.width - (margin*2 + ballWidth + 1)));
            by = 150;
            --num; // = num - 1; // ボールの残数を減らす
            mc.waitFlag = true;
        }
    }

    public void drawBackGround(Graphics g, Dimension d){
        g.setColor(Color.orange);
        g.fillRect(0, 0, d.width, d.height);
        g.setColor(Color.gray);
        g.fillRect(margin, margin, d.width - margin*2, d.height - margin*2);
    }

    public void drawBall(Graphics g, Canvas canvas){
        /* 読み込み中メッセージの表示 */
        if (mt.checkID(0) == false) {
            g.setColor(Color.black);
            g.fillRect(0, 0, d.width, d.height);
            g.setColor(Color.yellow);
            g.setFont(new Font("TimesRoman", Font.PLAIN, 14));
            g.drawString("Loading...", 40, 50);
            return;
        }
        else g.drawImage(ball, bx, by, canvas); // ボールを描く
    }

    public void drawBlock(Graphics g) {
        /* ブロックを描く */
        for(int i = 0; i < 30; i++) {
            if (i < 10) g.setColor(Color.blue);                             // 1段目は青
            else if (10 <= i && i < 20) g.setColor(Color.red);              // 2段目は赤
            else if (20 <= i) g.setColor(Color.pink);                       // 3段目はピンク

            if (blockFlag[i] == true)
                g.fillRect(blockX[i], blockY[i], blockWidth, blockHeight);  // ブロックがあれば、ブロックを描く
        }
    }

    public void drawRacket(Graphics g){
        /* ラケットを描く */
        g.setColor(Color.white);
        g.fillRect(rx, ry, racketWidth, racketHeight);
    }

    public void drawScore(Graphics g) {
        g.setColor(Color.black);
        g.drawString("Score : " + score, 24, 24);  // 点数の表示
        if (num <= 0) {                            //ボールの残数が無くなった場合
            g.setColor(Color.red);
            g.drawString("GAME OVER !", 40, 160);
        } else if (score == 30) {                  //　全てのブロックをくずした場合
            g.setColor(Color.black);
            g.drawString("PERFECT !", 40, 160);
        }
    }
}
