package pack8;

public class BreakoutNG1 {
    int block_width;
    int block_height;
    int block_x[] = new int[10];
    int block_y[] = new int[10];
    boolean blockisAlive[] = new boolean[10];

    public void initGame() {
        block_width = 64;
        block_height = 30;

        for (int i = 0; i < 10; i++) {
            block_x[i] = block_width * i;
            block_y[i] = 0;
            blockisAlive[i] = true;
        }
    }

    public void updateGame() {
        for (int i = 0; i < 10; i++) {
            if (blockisAlive[i]) {
                if (/* ball is hit to a block */) {
                    blockisAlive[i] = false;
                }
            }
        }
    }

    public void drawGame() {
        gc.clearScreen();

        gc.drawImage(0, ball_x, ball_y);

        gc.setColor(255, 0, 0);
        
        for (int i = 0; i < 10; i++) {
            if (blockisAlive[i]) {
                gc.fillRect(block_x[i], block_y[i], block_width, block_height);
            }
        }
    }
}