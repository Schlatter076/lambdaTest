package loyer.gui;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.Panel;
import java.awt.TextField;

public class GridLayoutTest {

  public static void main(String[] args) {

    Frame f = new Frame("计算器");
    Panel p1 = new Panel();
    p1.add(new TextField(30));
    f.add(p1, BorderLayout.NORTH);
    Panel p2 = new Panel();
    p2.setLayout(new GridLayout(3, 5, 4, 4));
    String[] names = 
      {"0", "1", "2", "3", "4",
       "5", "6", "7", "8", "9",
       "+", "-", "*", "/", "."};
    for(int i = 0; i < names.length; i++) {
      p2.add(new Button(names[i]));
    }
    f.add(p2);
    f.pack();  //界面自适应大小
    f.setVisible(true);
    
  }

}
