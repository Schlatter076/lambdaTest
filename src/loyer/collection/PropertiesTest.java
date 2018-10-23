package loyer.collection;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {

  public static void main(String[] args) throws IOException {

    Properties prop1 = new Properties();
    prop1.setProperty("user", "Loyer");
    prop1.setProperty("password", "123456");
    //导出配置文件
    prop1.store(new FileOutputStream("a.ini"), "first prop file");
    
    Properties prop2 = new Properties();
    prop2.setProperty("gender", "MALE");
    //加载配置文件
    prop2.load(new FileInputStream("a.ini"));
    System.out.println(prop2);    
    
  }

}
