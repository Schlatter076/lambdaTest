package loyer.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.border.CompoundBorder;
import javax.swing.border.EtchedBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.MatteBorder;
import javax.swing.border.TitledBorder;

public class BorderTest {
  
  private JFrame jf = new JFrame("Panel边框测试");
  
  public void init() {
    jf.setLayout(new GridLayout(2, 4));
    jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //创建BevelBorder
    Border bevelBorder = BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.RED, Color.GREEN, Color.BLUE, Color.GRAY);
    jf.add(getPanelWithBorder(bevelBorder, "BevelBorder"));
    Border lineBorder = BorderFactory.createLineBorder(Color.ORANGE, 10);
    jf.add(getPanelWithBorder(lineBorder, "LineBorder"));
    Border emptyBorder = BorderFactory.createEmptyBorder(20, 5, 10, 30);
    jf.add(getPanelWithBorder(emptyBorder, "EmptyBorder"));
    Border etchedBorder = BorderFactory.createEtchedBorder(EtchedBorder.RAISED, Color.RED, Color.GREEN);
    jf.add(getPanelWithBorder(etchedBorder, "EtchedBorder"));
    //直接创建TitledBorder，为原有的边框增加标题
    TitledBorder tb = new TitledBorder(lineBorder, "标题边框", TitledBorder.LEFT, 
        TitledBorder.BOTTOM, new Font("宋体", Font.BOLD, 18), Color.BLUE);
    jf.add(getPanelWithBorder(tb, "TitledBorder"));
    //直接创建MatteBorder，是EmptyBorder的子类，可以指定留空区域的颜色或背景(此处为颜色)
    MatteBorder mb = new MatteBorder(20, 5, 10, 30, Color.GREEN);
    jf.add(getPanelWithBorder(mb, "MatteBorder"));
    //直接创建CompoundBorder，将两个边框连接成一个新的边框
    CompoundBorder cb = new CompoundBorder(new LineBorder(Color.RED, 8), tb);
    jf.add(getPanelWithBorder(cb, "CompoundBorder"));
    jf.pack();
    jf.setVisible(true);
  }
  /**
   * 获取添加边框后的面板
   * @param b
   * @param borderName
   * @return
   */
  public JPanel getPanelWithBorder(Border b, String borderName) {
    JPanel p = new JPanel();
    p.add(new JLabel(borderName));
    //设置边框
    p.setBorder(b);
    return p;
  }

  public static void main(String[] args) {

    new BorderTest().init();
  }

}
