package loyer.gui;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

import javax.swing.Timer;

public class PinBall {

  private final int TABLE_WIDTH = 300;  //桌面宽度
  private final int TABLE_HEIGHT = 400;  //桌面高度
  private final int RACKET_Y = 340;  //球拍垂直高度
  private final int RACKET_HEIGHT = 20;  //球拍高度
  private final int RACKET_WIDTH = 60;  //球拍宽度
  private final int BALL_SIZE = 16; //弹球的大小
  
  private Frame f = new Frame("弹球游戏");
  Random rand = new Random();
  private int ySpeed = 10; //小球纵向运动速度
  //返回一个-0.5~0.5的比率
  private double xyRate = rand.nextDouble() - 0.5;
  //小球横向运动速率
  private int xSpeed = (int) (ySpeed * xyRate * 2);
  //小球的两轴坐标
  private int ballX = rand.nextInt(200) + 20;
  private int ballY = rand.nextInt(10) + 20;
  private int racketX = rand.nextInt(200);
  private MyCanvas tableArea = new MyCanvas();
  Timer timer;
  private boolean isLose = false; //游戏是否结束标志
  
  public void init() {
    tableArea.setPreferredSize(new Dimension(TABLE_WIDTH, TABLE_HEIGHT));
    f.add(tableArea);
    KeyAdapter keyProcessor = new KeyAdapter() {

      @Override
      public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT) {
          if(racketX > 0)
            racketX -= 10;
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT) {
          if(racketX < TABLE_WIDTH - RACKET_WIDTH) 
            racketX += 10;
        }
      }
      
    };
    //为窗口和tableArea添加按键事件
    f.addKeyListener(keyProcessor);
    f.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
      
    });
    tableArea.addKeyListener(keyProcessor);
    
    //定义每0.1s执行一次的事件
    ActionListener taskPerformer = e -> {
      //如果小球碰到左边边框
      if(ballX <= 0 || ballX >= TABLE_WIDTH - BALL_SIZE) {
        xSpeed = -xSpeed; //反向
      }
      //如果小球高度超出球拍位置，且横向不在球拍范围内，游戏结束
      if(ballY >= RACKET_Y - BALL_SIZE && (ballX < racketX || ballX < racketX + RACKET_WIDTH)) {
        timer.stop();
        isLose = true;
        tableArea.repaint();
      }
      //如果小球位置在球拍范围之内，且到达球拍位置，小球反弹
      else if(ballY <= 0 || (ballY >= RACKET_HEIGHT - BALL_SIZE && ballX > racketX && ballX <= racketX + RACKET_WIDTH)) {
        ySpeed = -ySpeed;
      }
      //小球坐标增加
      ballX += xSpeed;
      ballY += ySpeed;
      tableArea.repaint();
    };
    timer = new Timer(100, taskPerformer);
    timer.start();
    f.pack();
    f.setVisible(true);
  }
  
  
  public static void main(String[] args) {

    new PinBall().init();
  }
  
  class MyCanvas extends Canvas {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    @Override
    public void paint(Graphics g) {

      if(isLose) {
        g.setColor(new Color(255, 0, 0));
        g.setFont(new Font("Times", Font.BOLD, 30));
        g.drawString("游戏结束！", 20, 200);
      }
      else {
        g.setColor(new Color(240, 240, 80));
        //绘制小球
        g.fillOval(ballX, ballY, BALL_SIZE, BALL_SIZE);
        //绘制球拍
        g.setColor(new Color(80, 80, 200));
        g.fillRect(racketX, RACKET_Y, RACKET_WIDTH, RACKET_HEIGHT);
      }
    }
    
    
  }

}
