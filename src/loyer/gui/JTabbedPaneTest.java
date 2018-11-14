package loyer.gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTabbedPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class JTabbedPaneTest {

  private JTabbedPane tabPane;
  private JFrame jf;
  private ButtonPane leftPane;
  private ButtonPane rightPane;
  private JMenuBar menuBar;
  private JMenu fileMenu;
  private JMenuItem openItem;
  private JMenuItem exitItem;
  
  /**
   * @wbp.parser.entryPoint
   */
  public void init() {
    
    
    try {
      //将界面风格设置成和系统一置
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
    }//*/
    
    tabPane = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);
    leftPane = new ButtonPane("般若波罗密多心经", new String[] {"观自在菩萨", "行深般若波罗密多时", "照见五蕴皆空", "度一切苦厄"});
    leftPane.setBorder(new TitledBorder(new EtchedBorder(), "心经首段", TitledBorder.CENTER, TitledBorder.TOP));
    tabPane.addTab("般若波罗密多心经", leftPane);
    rightPane = new ButtonPane("般若波罗密多咒", new String[] {"揭谛揭谛", "波罗揭谛", "波罗僧揭谛", "菩提萨婆诃"});
    rightPane.setBorder(new TitledBorder(new EtchedBorder(), "咒语", TitledBorder.CENTER, TitledBorder.TOP));
    tabPane.addTab("般若波罗密多咒", rightPane);
    tabPane.setPreferredSize(new Dimension(500, 400));
    jf = new JFrame("标签页测试");
    menuBar = new JMenuBar();
    fileMenu = new JMenu("文件(F)");
    openItem = new JMenuItem("打开(O)");
    exitItem = new JMenuItem("退出(E)");
    fileMenu.add(openItem);
    fileMenu.addSeparator();
    fileMenu.add(exitItem);
    menuBar.add(fileMenu);
    jf.setJMenuBar(menuBar);
    jf.setLocation(400, 200);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.add(tabPane);
    //jf.getContentPane().add(tabPane);
    
    
    
    
  }
  
  public JTabbedPaneTest() {
    init();
  }
  
  public static void main(String[] args) {

    EventQueue.invokeLater(new Runnable() {
      
      @Override
      public void run() {
        JTabbedPaneTest window = new JTabbedPaneTest();
        window.jf.pack();
        window.jf.setVisible(true);
      }
    });
  }
  /**
   * 面板类
   * 
   * @author hw076
   *
   */
  class ButtonPane extends JPanel {

    private static final long serialVersionUID = 1L;
    private ButtonGroup group;

    public ButtonPane(String title, String[] options) {

      // 设置边框
      setBorder(BorderFactory.createTitledBorder(BorderFactory.createEtchedBorder(), title));
      // 设置布局
      setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
      group = new ButtonGroup();
      for (int i = 0; options != null && i < options.length; i++) {
        JRadioButton b = new JRadioButton(options[i]);
        b.setActionCommand(options[i]);
        add(b);
        group.add(b);
        b.setSelected(i == 0);
      }
    }
  }

}
