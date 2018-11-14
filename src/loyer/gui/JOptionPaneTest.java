package loyer.gui;

import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.border.EtchedBorder;
import javax.swing.border.TitledBorder;

public class JOptionPaneTest {

  private JFrame jf = new JFrame("JOptionPane测试");
  // 定义六个面板
  private ButtonPane messagePanel;
  private ButtonPane messageTypePane;
  private ButtonPane msgPanel;
  private ButtonPane confirmPanel;
  private ButtonPane optionPanel;
  private ButtonPane inputPanel;
  private String messageString = "消息区内容";
  private Icon messageIcon = new ImageIcon("/Kyokuto.png");
  private Object messageObj = new Date();
  private Component messageComp = new JButton("组件消息");
  private JButton msgButt = new JButton("消息对话框");
  private JButton confirmButt = new JButton("确认对话框");
  private JButton inputButt = new JButton("输入对话框");
  private JButton optionButt = new JButton("选项对话框");

  public void init() {
    
    try {
      //将界面风格设置成和系统一置
      UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
    } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException e) {
      JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
    }//*/

    JPanel top = new JPanel();
    top.setBorder(new TitledBorder(new EtchedBorder(), "对话框的通用选项", TitledBorder.CENTER, TitledBorder.TOP));
    top.setLayout(new GridLayout(1, 2));
    // 消息类型面板，该面板中选项决定对话框的图标
    messageTypePane = new ButtonPane("选择消息的类型",
        new String[] { "ERROR_MESSAGE", "INFOMATION_MESSAGE", "WARNING_MESSAGE", "QUESTION_MESSAGE", "PLAIN_MESSAGE" });
    // 消息内容类型面板，该面板中的选项决定对话框消息区的内容
    messagePanel = new ButtonPane("选择消息内容的类型", new String[] { "字符串消息", "图标消息", "组件消息", "普通对象消息", "Object[] 消息" });
    top.add(messageTypePane);
    top.add(messagePanel);

    JPanel bottom = new JPanel();
    bottom.setLayout(new GridLayout(1, 4));
    bottom.setBorder(new TitledBorder(new EtchedBorder(), "弹出不同的对话框", TitledBorder.CENTER, TitledBorder.TOP));
    // 用于弹出消息对话框的面板
    msgPanel = new ButtonPane("消息对话框", null);
    msgButt.addActionListener(new ShowAction());
    msgButt.setFont(new Font("宋体", Font.PLAIN, 12));
    msgPanel.add(msgButt);
    // 用于弹出确认对话框的面板
    confirmPanel = new ButtonPane("确认对话框",
        new String[] { "DEFAULT_OPTION", "YES_NO_OPTION", "YES_NO_CANCEL_OPTION", "OK_CANCEL_OPTION" });
    confirmButt.addActionListener(new ShowAction());
    confirmButt.setFont(new Font("宋体", Font.PLAIN, 12));
    confirmPanel.add(confirmButt);
    // 用于弹出输入对话框的面板
    inputPanel = new ButtonPane("输入对话框", new String[] { "单行文本框", "下拉列表选择框" });
    inputButt.addActionListener(new ShowAction());
    inputButt.setFont(new Font("宋体", Font.PLAIN, 12));
    inputPanel.add(inputButt);
    // 创建弹出选项对话框
    optionPanel = new ButtonPane("选项对话框", new String[] { "字符串选项", "图表选项", "对象选项" });
    optionButt.addActionListener(new ShowAction());
    optionButt.setFont(new Font("宋体", Font.PLAIN, 12));
    optionPanel.add(optionButt);

    bottom.add(msgPanel);
    bottom.add(confirmPanel);
    bottom.add(inputPanel);
    bottom.add(optionPanel);

    Box box = new Box(BoxLayout.Y_AXIS);
    box.add(top);
    box.add(bottom);
    jf.add(box);
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    jf.pack();
    jf.setVisible(true);

  }

  /**
   * 根据用户选择返回选项类型
   * 
   * @return
   */
  private int getOptionType() {
    switch (confirmPanel.getSelection()) {

    case "DEFAULT_OPTION":
      return JOptionPane.DEFAULT_OPTION;
    case "YES_NO_OPTION":
      return JOptionPane.YES_NO_OPTION;
    case "YES_NO_CANCEL_OPTION":
      return JOptionPane.YES_NO_CANCEL_OPTION;
    default:
      return JOptionPane.OK_CANCEL_OPTION;
    }
  }

  /**
   * 根据用户选择返回信息
   * 
   * @return
   */
  private Object getMessage() {
    switch (messagePanel.getSelection()) {

    case "字符串消息":
      return messageString;
    case "图标消息":
      return messageIcon;
    case "组件消息":
      return messageComp;
    case "普通对象消息":
      return messageObj;
    default:
      return new Object[] {messageString, messageIcon, messageObj, messageComp};
    }
  }
  /**
   * 返回消息类型(决定图标区的图标)
   * @return
   */
  private int getDialogType() {

    switch (messageTypePane.getSelection()) {

    case "ERROR_MESSAGE":
      return JOptionPane.ERROR_MESSAGE;
    case "INFOMATION_MESSAGE":
      return JOptionPane.INFORMATION_MESSAGE;
    case "WARNING_MESSAGE":
      return JOptionPane.WARNING_MESSAGE;
    case "QUESTION_MESSAGE":
      return JOptionPane.QUESTION_MESSAGE;
    default:
      return JOptionPane.PLAIN_MESSAGE;
    }
  }
  
  private Object[] getOption() {
    
    switch(optionPanel.getSelection()) {
    
    case "字符串选项" :
      return new String[] {"a", "b", "c", "d"};
    case "图标选项" :
      return new Icon[] {};
      
    default :
      return new Object[] {new Date(), new Date(), new Date()};
    }
    
  }

  public static void main(String[] args) {

    new JOptionPaneTest().init();
  }

  /**
   * 按钮事件监听器
   * 
   * @author hw076
   *
   */
  private class ShowAction implements ActionListener {

    @Override
    public void actionPerformed(ActionEvent e) {
      
      switch (e.getActionCommand()) {
      
      case "确认对话框":
        JOptionPane.showConfirmDialog(jf, getMessage(), "确认对话框", getOptionType(), getDialogType());
        break;
      case "输入对话框" :
        if(inputPanel.getSelection().equals("单行文本框")) {
          JOptionPane.showInputDialog(jf, getMessage(), "输入对话框", getDialogType());
        } else {
          JOptionPane.showInputDialog(jf, getMessage(), "输入对话框", getDialogType(), null, new String[] {"Loyer", "Schlatter"}, "Neo");
        }
        break;
      case "消息对话框" :
        JOptionPane.showMessageDialog(jf, getMessage(), "消息对话框", getDialogType());
        break;
      case "选项对话框" :
        JOptionPane.showOptionDialog(jf, getMessage(), "选项对话框", getOptionType(), getDialogType(), null, getOption(), "a");
        break;

      }
    }

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

    /**
     * 返回用户选择的选项
     * 
     * @return
     */
    public String getSelection() {
      return group.getSelection().getActionCommand();
    }

  }

}
