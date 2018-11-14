package loyer.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.beans.PropertyVetoException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JCheckBoxMenuItem;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JToolBar;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import java.awt.Color;

public class JIternalFrameTest {

  private JFrame frame;
  private static final int DESKTOP_WIDTH = 480;
  private static final int DESKTOP_HEIGHT = 360;
  private static final int FRAME_DISTANCE = 30;
  private MyDesktop desktop = new MyDesktop();
  private int nextFrameX = 0;
  private int nextFrameY = 0;
  private int width = DESKTOP_WIDTH / 2;
  private int height = DESKTOP_HEIGHT / 2;
  
  private JMenu fileMenu = new JMenu("文件(F)");
  private JMenu windowMenu = new JMenu("窗口(W)");
  private Action newAction;
  private Action exitAction; //用以创建菜单和工具按钮  
  private JMenuBar menuBar = new JMenuBar();
  private JToolBar toolBar = new JToolBar();
  private JMenuItem nextItem = new JMenuItem("下一个");
  private JMenuItem cascadeItem = new JMenuItem("级联");
  private JMenuItem tileItem = new JMenuItem("平铺");

  /**
   * Launch the application.
   */
  public static void main(String[] args) {
    EventQueue.invokeLater(new Runnable() {
      public void run() {
        try {
          JIternalFrameTest window = new JIternalFrameTest();
          window.frame.pack();
          window.frame.setVisible(true);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    });
  }

  /**
   * Create the application.
   */
  public JIternalFrameTest() {
    initialize();
  }

  /**
   * Initialize the contents of the frame.
   */
  private void initialize() {
    
    try {
      //将界面风格设置成和系统一置
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
    }//*/
    frame = new JFrame("MDI 界面");
    frame.getContentPane().setBackground(new Color(245, 245, 245));
    frame.setLocation(400, 200);
    //frame.setPreferredSize(new Dimension(500, 400));
    Image img = Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("/frame.jpg"));
    frame.setIconImage(img);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    
    newAction = new AbstractAction("新建") {
      
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        final JInternalFrame iframe = new JInternalFrame("新文档", true, true, true, true); //可改变大小，可关闭，可最大化，可最小化
        iframe.getContentPane().add(new JScrollPane(new JTextArea(8, 40)));
        //将内部窗口添加到虚拟桌面
        desktop.add(iframe);
        //设置窗口原始位置
        iframe.reshape(nextFrameX, nextFrameY, width, height);
        //窗口可见，并尝试选中它
        iframe.show();
        //计算下一个窗口位置
        nextFrameX += FRAME_DISTANCE;
        nextFrameY += FRAME_DISTANCE;
        if(nextFrameX + width > desktop.getWidth()) nextFrameX = 0;
        if(nextFrameY + height > desktop.getHeight()) nextFrameY = 0;
      }
    };
    exitAction = new AbstractAction("退出") {
  
      private static final long serialVersionUID = 1L;

      @Override
      public void actionPerformed(ActionEvent e) {
        System.exit(0);
      }
    };
    menuBar.setBackground(new Color(255, 255, 255));
    frame.setJMenuBar(menuBar);
    menuBar.add(fileMenu);
    fileMenu.add(newAction);
    fileMenu.add(exitAction);
    toolBar.setBackground(new Color(245, 245, 245));
    toolBar.add(newAction);
    toolBar.add(exitAction);
    menuBar.add(windowMenu);
    nextItem.addActionListener(e -> desktop.selectNextWidow());
    windowMenu.add(nextItem);
    cascadeItem.addActionListener(e -> desktop.cascadeWindows(FRAME_DISTANCE, 0.75));
    windowMenu.add(cascadeItem);
    tileItem.addActionListener(e -> desktop.tileWindows());
    windowMenu.add(tileItem);
    
    final JCheckBoxMenuItem dragItem = new JCheckBoxMenuItem("仅显示拖动窗口的轮廓");
    dragItem.addActionListener(e -> {
      desktop.setDragMode(dragItem.isSelected() ? JDesktopPane.OUTLINE_DRAG_MODE : JDesktopPane.LIVE_DRAG_MODE);
    });
    windowMenu.add(dragItem);
    desktop.setPreferredSize(new Dimension(480, 360));
    frame.getContentPane().add(desktop);
    frame.getContentPane().add(toolBar, BorderLayout.NORTH);
  }
  /**
   * 自定义虚拟桌面窗口类
   * @author hw076
   *
   */
  class MyDesktop extends JDesktopPane {

    private static final long serialVersionUID = 1L;
    /**
     * 将内部窗口以级联形式呈现
     * @param offset  窗口的位移距离
     * @param scale 内部窗口与外部窗口的比率
     */
    public void cascadeWindows(int offset, double scale) {
      //级联显示时，内部窗口的大小
      int width = (int) (getWidth() * scale);
      int height = (int) (getHeight() * scale);
      //级联显示时每个窗口的位置
      int x = 0;
      int y = 0;
      for(JInternalFrame frame : getAllFrames()) {
        //取消窗口最大最小化
        frame.setMaximizable(false);
        try {
          frame.setIcon(false);
        } catch (PropertyVetoException e) {
          JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
        //将窗口重新放在合适的位置
        frame.reshape(x, y, width, height);
        x += offset;
        y += offset;
        //如果到了虚拟桌面边界
        if(x + width > getWidth())  x = 0;
        if(y + height > getHeight()) y = 0;        
      }
      
    }
    /**
     * 将所有窗口平铺
     */
    @SuppressWarnings("unused")
    public void tileWindows() {
      int frameCount = 0;
      for(JInternalFrame frame : getAllFrames()) {
        frameCount++;
      }
      //计算平铺所需要的行和列
      int rows = (int) Math.sqrt(frameCount);
      int cols = frameCount / rows;
      //需要增加到其他列中的窗口
      int extra = frameCount % rows;
      //计算平铺时窗口的大小
      int width = getWidth() / cols;
      int height = getHeight() / rows;
      //窗口的索引
      int x = 0;
      int y = 0;
      for(JInternalFrame frame : getAllFrames()) {
        frame.setMaximizable(false);
        try {
          frame.setIcon(false);
        } catch (PropertyVetoException e) {
          JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        }
        //将窗口放在指定位置
        frame.reshape(x * width, y * height, width, height);
        y++;
        //当排完一列
        if(y == rows) {
          y = 0;
          x++;
          //如果额外多出的窗口与剩余列数相同，则后面所有列都应该多排一个窗口
          if(extra == cols - x) {
            rows++;
            height = getHeight() / rows;
          }
        }
      }
    }
    /**
     * 选中下一个非图标窗口
     */
    public void selectNextWidow() {
      JInternalFrame[] frames = getAllFrames();
      for(int i = 0; i < frames.length; i++) {
        if(frames[i].isSelected()) {
          //找出下一个非最小化的非图标窗口，选中它
          int next = (i + 1) % frames.length;
          while(next != i) {
            //窗口非最小化
            if(!frames[next].isIcon()) {
              try {
                frames[next].setSelected(true);
              } catch (PropertyVetoException e) {
                JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
              }
              frames[next].toFront();
              frames[i].toBack();
            }
            next = (next + 1) % frames.length;
          }
        }
      }
    }
    
  }

}
