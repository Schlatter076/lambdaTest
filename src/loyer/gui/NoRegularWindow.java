package loyer.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Paint;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.geom.Ellipse2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

public class NoRegularWindow extends JFrame implements ActionListener {

  private static final long serialVersionUID = 1L;
  private JFrame transWin = new JFrame("透明窗口");
  private JFrame gradientWin = new JFrame("渐变透明窗口");
  private JFrame bgWin = new JFrame("背景窗口");
  private JFrame shapeWin = new JFrame("椭圆窗口");
  
  public NoRegularWindow() {
    
    super("不规则窗口测试");
    
    try {
      //将界面风格设置成和系统一置
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
    }//*/
    
    setLayout(new FlowLayout());
    JButton transButt = new JButton("透明窗口");
    JButton gradientButt = new JButton("渐变透明窗口");
    JButton bgButt = new JButton("背景窗口");
    JButton shapeButt = new JButton("椭圆窗口");
    transButt.addActionListener(this);
    gradientButt.addActionListener(this);
    bgButt.addActionListener(this);
    shapeButt.addActionListener(this);
    add(transButt);
    add(gradientButt);
    add(bgButt);
    add(shapeButt);
    
    //设置透明窗口
    transWin.setLayout(new GridBagLayout());
    transWin.setSize(300, 200);
    transWin.add(new JButton("透明窗口的按钮"));
    transWin.setOpacity(0.65f); //透明度
    //设置渐变透明的窗口
    gradientWin.setBackground(new Color(0, 0, 0, 0));
    gradientWin.setSize(new Dimension(300, 200));
    JPanel panel = new JPanel() {
      private static final long serialVersionUID = 1L;

      @Override
      protected void paintComponent(Graphics g) {
        if(g instanceof Graphics2D) {
          final int R = 240;
          final int G = 240;
          final int B = 240;
          //创建一个渐变画笔
          Paint p = new GradientPaint(0.0f, 0.0f, new Color(R, G, B, 0), 0.0f, getHeight(), new Color(R, G, B, 255), true);
          Graphics2D gd = (Graphics2D) g;
          gd.setPaint(p);
          gd.fillRect(0, 0, getWidth(), getHeight());
        } 
      }      
    };
    //使用panel做为窗口的容器
    gradientWin.setContentPane(panel);
    panel.setLayout(new GridBagLayout());
    gradientWin.add(new JButton("渐变窗口的按钮"));
    //设置有背景图片的窗口
    bgWin.setBackground(new Color(0, 0, 0, 0));
    bgWin.setSize(new Dimension(300, 200));
    //使用一个bgPanel做背景
    JPanel bgPanel = new JPanel() {
      private static final long serialVersionUID = 1L;

      @Override
      protected void paintComponent(Graphics g) {
        try {
          Image bg = ImageIO.read(new File("src/img8.jpg"));
          g.drawImage(bg, 0, 0, getWidth(), getHeight(), null);
        } catch (IOException e) {
          e.printStackTrace();
        }
      }
    };
    bgWin.setContentPane(bgPanel);
    bgPanel.setLayout(new GridBagLayout());
    bgWin.add(new JButton("背景窗口的按钮"));
    //设置椭圆形窗口
    shapeWin.setLayout(new GridBagLayout());
    shapeWin.setUndecorated(true);
    shapeWin.setOpacity(0.7f);
    //添加监听器，动态改变窗口形状
    shapeWin.addComponentListener(new ComponentAdapter() {

      @Override
      public void componentResized(ComponentEvent e) { //窗口大小被改变时
        //设置窗口形状
        shapeWin.setShape(new Ellipse2D.Double(0, 0, shapeWin.getWidth(), shapeWin.getHeight()));
      }
    });
    shapeWin.setSize(300, 200);
    shapeWin.add(new JButton("椭圆窗口按钮"));
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    pack();
    setVisible(true);    
  }
  
  @Override
  public void actionPerformed(ActionEvent e) {
    switch(e.getActionCommand()) {
    
    case "透明窗口" :
      transWin.setVisible(true);
      break;
    case "渐变透明窗口" :
      gradientWin.setVisible(true);
      break;
    case "背景窗口" :
      bgWin.setVisible(true);
      break;
    case "椭圆窗口" :
      shapeWin.setVisible(true);
      break;
    }
  }

  public static void main(String[] args) {

    EventQueue.invokeLater(new Runnable() {
      
      @Override
      public void run() {
        setDefaultLookAndFeelDecorated(true);
        new NoRegularWindow();
      }
    });
  }

}
