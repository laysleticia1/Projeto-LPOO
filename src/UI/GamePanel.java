package UI;

import javax.swing.*;
import java.awt.*;

public class GamePanel extends JPanel implements Runnable {

    final int originalTileSize = 16;
    final int scale = 3;
    final int tileSize = originalTileSize * scale;

    final int maxScreenCo = 16;
    final int maxScreenRow = 12;
    final int screenWidth = maxScreenCo * tileSize;
    final int screenHeight = maxScreenRow * tileSize;

    int FPS = 60;

    KeyHandler keyH = new KeyHandler();
    Thread gameThread;

    int playerX = 100;
    int playerY = 100;
    int playerSpeed = 4;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);
    }

    public void startGameThread() {

        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1000000000/FPS;

        double nextDrawTime = System.nanoTime() + drawInterval;

        while (gameThread != null) {

            long currentTime = System.nanoTime();

            update();

            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;

                if (remainingTime < 0) {
                    remainingTime = 0;
                }

                Thread.sleep((long) remainingTime);

                nextDrawTime += drawInterval;

        } catch (InterruptedException e) {
            e.printStackTrace();
            }
        }
    }

    public void update() {
        if (keyH.upPressed == true) {
            playerY -= playerSpeed;
            playerY = playerY - playerSpeed;
        }
        else if (keyH.downPressed == true) {
            playerY += playerSpeed;
        }
        else if (keyH.leftPressed == true) {
            playerX -= playerSpeed;
        }
        else if (keyH.rightPressed == true) {
            playerX += playerSpeed;
        }

    }
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.white);

        g2.fillRect(playerX, playerY, tileSize, tileSize);

        g2.dispose();
    }
}
