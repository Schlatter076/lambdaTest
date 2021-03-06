package loyer.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Random;

public class GraphicsTest {

  private Frame frame = new Frame("位图测试");
  private final String RECT_SHAPE = "rect";
  private final String OVAL_SHAPE = "oval";
  private Button rect = new Button(RECT_SHAPE);
  private Button oval = new Button(OVAL_SHAPE);
  private MyCanvas canvas = new MyCanvas();
  private String shape = "";
  
  byte[] b = {0x01, 0x20, 0x33, 0x45, 0x67, 0x78, 0x23};
  public void init() {
    frame.setFont(new Font("宋体", Font.PLAIN, 14));
    
    frame.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
      
    });
    Panel panel = new Panel();
    rect.setFont(new Font("宋体", Font.PLAIN, 12));
    rect.addActionListener(e -> {
      shape = RECT_SHAPE;
      canvas.repaint();        
    });
    oval.setFont(new Font("宋体", Font.PLAIN, 12));
    oval.addActionListener(e -> {
      shape = OVAL_SHAPE;
      canvas.repaint();
    });
    panel.add(rect);
    panel.add(oval);
    canvas.setPreferredSize(new Dimension(300, 200));
    frame.add(canvas);
    frame.add(panel, BorderLayout.SOUTH);
    frame.pack();
    frame.setVisible(true);
  }
  
  public static void main(String[] args) {

    new GraphicsTest().init();
  }

  class MyCanvas extends Canvas {

    private static final long serialVersionUID = 1L;

    @Override
    public void paint(Graphics g) {
    
      /*
      //使用Graphics画图
      g.fillRect(0, 0, 400, 400);
      //设置颜色：红
      g.setColor(new Color(255, 0, 0));
      g.fillArc(20, 20, 100, 100, 30, 120);
      //设置颜色：绿
      g.setColor(new Color(0, 255, 0));
      g.fillArc(20, 20, 100, 100, 150, 120);
      //设置颜色：蓝
      g.setColor(new Color(0, 0, 255));
      g.fillArc(20, 20, 100, 100, 270, 120);
      //设置颜色：黑
      g.setColor(new Color(255, 255, 255));
      g.setFont(new Font("宋体", Font.PLAIN, 17));
      //画出三个字符串
      g.drawString("red:climb", 200, 60);
      g.drawString("green:swim", 200, 100);
      g.drawString("blue:jump", 200, 140);//*/
      
      Random ran = new Random();
      if(shape.equals(RECT_SHAPE)) {
        g.setColor(Color.GREEN);  //设置画笔颜色
        //画一个矩形
        g.drawRect(ran.nextInt(200), ran.nextInt(120), 40, 60);
        
      }
      if(shape.equals(OVAL_SHAPE)) {
        g.setColor(Color.RED);  //设置画笔颜色
        //画一个矩形
        g.fillOval(ran.nextInt(200), ran.nextInt(120), 40, 50);
        
      }
      //g.draw3DRect(ran.nextInt(200), ran.nextInt(120), 40, 50, true);
      //g.drawLine(20, 10, 40, 60);
      //g.drawLine(40, 60, 60, 80);
      //g.drawBytes(b, 0, b.length, 10, 15);//*/
    }

  }
}
