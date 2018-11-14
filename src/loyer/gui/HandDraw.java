package loyer.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MenuItem;
import java.awt.Panel;
import java.awt.PopupMenu;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class HandDraw {
  
  private final int AREA_WIDTH = 500;
  private final int AREA_HEIGHT = 400;
  private int preX = -1;
  private int preY = -1;  //保存上一次鼠标的坐标值
  PopupMenu popMenu = new PopupMenu();
  MenuItem redItem = new MenuItem("Red");
  MenuItem greenItem = new MenuItem("Green");
  MenuItem blueItem = new MenuItem("Blue");
  //创建一个位图对象
  BufferedImage img = new BufferedImage(AREA_WIDTH, AREA_HEIGHT, BufferedImage.TYPE_INT_RGB);
  Graphics g = img.getGraphics();
  Frame f = new Frame("手绘程序");
  
  MyCanvas canvas = new MyCanvas();
  //用来保存画笔颜色
  private Color foreColor = new Color(255, 0, 0);
  
  //系统剪贴板
  private Clipboard clipBoard = Toolkit.getDefaultToolkit().getSystemClipboard();
  //保存剪贴进来的图像对象
  List<Image> list = new ArrayList<>();
  
  public void init() {
    //定义鼠标右键事件
    ActionListener menuListener = e -> {
      if(e.getActionCommand().equals("Green"))
        foreColor = new Color(0, 255, 0);
      if(e.getActionCommand().equals("Red"))
        foreColor = new Color(255, 0, 0);
      if(e.getActionCommand().equals("Blue"))
        foreColor = new Color(0, 0, 255);
    };
    redItem.addActionListener(menuListener);
    greenItem.addActionListener(menuListener);
    blueItem.addActionListener(menuListener);
    popMenu.add(redItem);
    popMenu.add(greenItem);
    popMenu.add(blueItem);
    canvas.add(popMenu);
    //将位图对象背景填充成白色
    g.fillRect(0, 0, AREA_WIDTH, AREA_HEIGHT);
    canvas.setPreferredSize(new Dimension(AREA_WIDTH, AREA_HEIGHT));
    //监听鼠标移动动作
    canvas.addMouseMotionListener(new MouseMotionAdapter() {

      @Override
      public void mouseDragged(MouseEvent e) {
        if(preX > 0 && preY > 0) {
          //设置画笔颜色
          g.setColor(foreColor);
          g.drawLine(preX, preY, e.getX(), e.getY());
        }
        //将当前事件鼠标的点保存起来
        preX = e.getX();
        preY = e.getY();
        //重绘位图对象
        canvas.repaint();
      }
      
    });
    canvas.addMouseListener(new MouseAdapter() {

      @Override
      public void mouseReleased(MouseEvent e) {
        if(e.isPopupTrigger())
          popMenu.show(canvas, e.getX(), e.getY());
        
        //松开后，将上一次鼠标位置清空
        preX = -1;
        preY = -1;
      }
      
    });
    f.add(canvas);
    f.addWindowListener(new WindowAdapter() {

      @Override
      public void windowClosing(WindowEvent e) {
        System.exit(0);
      }
      
    });
    Panel panel = new Panel();
    Button copy = new Button("Copy");
    Button paste = new Button("Paste");
    copy.addActionListener(e -> {
      //将Image对象封装成ImageSelection对象
      ImageSelection contens = new ImageSelection(img);
      //将contents放入剪贴板内
      clipBoard.setContents(contens, null);      
    });
    paste.addActionListener(e -> {
      if(clipBoard.isDataFlavorAvailable(DataFlavor.imageFlavor)) {
        //取出剪贴板的内容，添加到list中
        try {
          list.add((Image) clipBoard.getData(DataFlavor.imageFlavor));
          canvas.repaint();
        } catch (UnsupportedFlavorException e1) {
          e1.printStackTrace();
        } catch (IOException e1) {
          e1.printStackTrace();
        }        
      }
    });
    panel.add(copy);
    panel.add(paste);
    f.add(panel, BorderLayout.SOUTH);
    f.pack();
    f.setVisible(true);
    
  }
  public static void main(String[] args) {
    new HandDraw().init();
  }
  
  class MyCanvas extends Canvas {

    private static final long serialVersionUID = 1L;

    @Override
    public void paint(Graphics g) {
      g.drawImage(img, 0, 0, null);  //将图像绘制到画布上
      //将剪贴板中所有图像画出来
      for(Image img : list) {
        g.drawImage(img, 0, 0, null);
      }
    }
    
    
  }

}
