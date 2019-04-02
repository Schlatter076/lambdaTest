package loyer.xmlRead;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesTest {

  public static void main(String[] args) throws FileNotFoundException, IOException {
    Properties prop = new Properties();
    prop.load(new FileInputStream(new File("SerialPorts.properties")));
    String com1 = prop.getProperty("com1Name");
    String com1Baudrate = prop.getProperty("com1Baudrate");
    String com1StopBits = prop.getProperty("com1StopBits");
    String com1Parity = prop.getProperty("com1Parity");
    System.out.println(com1);
    System.out.println(com1Baudrate);
    System.out.println(com1StopBits);
    System.out.println(com1Parity);
    //prop.storeToXML(new FileOutputStream(new File("s.xml")), "配置文件转换", "UTF-8");
    //prop.store(new FileOutputStream(new File("ss.properties")), "串口参数配置");
  }

}
