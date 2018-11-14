package loyer.gui;

import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileSystemView;
import javax.swing.filechooser.FileView;
import java.awt.Color;
import java.awt.Font;

public class JFileChooserTest {

  private final int PREVIEW_SIZE = 100;
  private JFrame frame = new JFrame("图片查看器");
  private JLabel label = new JLabel();  //用来显示图片
  //以当前路径创建文件选择器
  JFileChooser chooser = new JFileChooser(".");
  JLabel accessory = new JLabel();
  //定义文件过滤器
  ExtensionFileFilter filter = new ExtensionFileFilter();
  JMenuBar menuBar = new JMenuBar();
  
  public void init() {
    
    /*
    chooser.setBackground(new Color(245, 245, 245));
    chooser.setForeground(new Color(245, 245, 245));
    chooser.setFont(new Font("宋体", Font.PLAIN, 14));
    //*/
    
    try {
      //将界面风格设置成和系统一置
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
    }//*/
    
    
    filter.addExtensions("jpg");
    filter.addExtensions("jpeg");
    filter.addExtensions("gif");
    filter.addExtensions("png");
    filter.setDescription("图片文件(*.jpg, *.jpeg, *.gif, *.png)");
    
    chooser.addChoosableFileFilter(filter);
    //禁止"文件类型"下拉列表显示所有文件选项
    chooser.setAcceptAllFileFilterUsed(false);
    //为文件选择器指定一个预览图片的附件
    chooser.setAccessory(accessory);
    //设置预览组件的大小和边框
    accessory.setPreferredSize(new Dimension(PREVIEW_SIZE, PREVIEW_SIZE));
    accessory.setBorder(BorderFactory.createEtchedBorder());
    
    chooser.setFileView(new FileIconView(filter));
    //被选择文件改变事件
    chooser.addPropertyChangeListener(e -> {
      //被选择对象已经发生改变
      if(e.getPropertyName() == JFileChooser.SELECTED_FILE_CHANGED_PROPERTY) {
        //获取用户选择的新文件
        File file = (File) e.getNewValue();
        if(file == null) {
          accessory.setIcon(null);
          return;
        }
        //将所选文件读取进来
        ImageIcon icon = new ImageIcon(file.getPath());
        //如果图像太大，则缩小它
        if(icon.getIconWidth() > PREVIEW_SIZE) {
          icon = new ImageIcon(icon.getImage().getScaledInstance(PREVIEW_SIZE, -1, Image.SCALE_DEFAULT));
          
        }
        //改变accesory图标
        accessory.setIcon(icon);
      }
    });
    //为窗口安装菜单
    JMenu menu = new JMenu("文件(F)");
    menu.setFont(new Font("宋体", Font.PLAIN, 14));
    menu.setBackground(new Color(245, 245, 245));
    menuBar.setBackground(new Color(255, 255, 255));
    menuBar.add(menu);
    JMenuItem openItem = new JMenuItem("打开");
    openItem.setFont(new Font("宋体", Font.PLAIN, 14));
    openItem.setBackground(new Color(245, 245, 245));
    menu.add(openItem);
    //添加打开事件
    openItem.addActionListener(e -> {
      int result = chooser.showDialog(frame, "打开图片文件");
      //如果用户点击了同意按钮
      if(result == JFileChooser.APPROVE_OPTION) {
        String name = chooser.getSelectedFile().getPath();
        //显示图片
        label.setIcon(new ImageIcon(name));
      }
    });
    JMenuItem exitItem = new JMenuItem("退出");
    exitItem.setFont(new Font("宋体", Font.PLAIN, 14));
    exitItem.setBackground(new Color(245, 245, 245));
    //menu.addSeparator();  //添加分割线
    menu.add(exitItem);
    exitItem.addActionListener(e -> System.exit(0));
    frame.setBackground(new Color(245, 245, 245));
    
    frame.setJMenuBar(menuBar);  //设置菜单栏
    label.setFont(new Font("宋体", Font.PLAIN, 14));
    //添加用于显示图片的组件
    frame.getContentPane().add(new JScrollPane(label));
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setBounds(200, 100, 500, 400);
    frame.setIconImage(Toolkit.getDefaultToolkit().getImage(frame.getClass().getResource("/Kyokuto.png")));
    //frame.pack();
    frame.setVisible(true); 
  }
  
  public static void main(String[] args) {

    new JFileChooserTest().init();
  }
  /**
   * 文件过滤类
   * @author hw076
   *
   */
  class ExtensionFileFilter extends FileFilter {

    private String description;
    private ArrayList<String> extensions = new ArrayList<>();
    
    /**
     * 添加文件扩展名
     */
    public void addExtensions(String extension) {
      if(!extension.startsWith(".")) {
        extension = "." + extension;
        extensions.add(extension.toLowerCase());
      }
    }
    /**
     * 设置文件描述
     * @param description
     */
    public void setDescription(String description) {
      this.description = description;
    }
    
    @Override
    public boolean accept(File f) {
      if(f.isDirectory()) 
        return true;
      String name = f.getName().toLowerCase(); //忽略文件名大小写
      //遍历文件扩展名
      for(String s : extensions) {
        if(name.endsWith(s))
          return true;
      }
      return false;
    }

    @Override
    public String getDescription() {
      return description;
    }
    
  }
  class FileIconView extends FileView {

    private FileFilter filter;
    public FileIconView(FileFilter filter) {
      super();
      this.filter = filter;
    }
    
    @Override
    public Icon getIcon(File f) {
      if(!f.isDirectory() && filter.accept(f)) {
        return new ImageIcon("/Kyokuto.png");
      }
      else if(f.isDirectory()) {
        File[] fList = File.listRoots();
        for(File tem : fList) {
          if(tem.equals(f)) {
            return new ImageIcon("/img7.jpg");
          }
        }
        return new ImageIcon("/img8.jpg");
      }
      else
        return null;
    }
    
  }

}
