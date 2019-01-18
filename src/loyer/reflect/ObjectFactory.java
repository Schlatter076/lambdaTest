package loyer.reflect;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class ObjectFactory {

  /**对象池*/
  private Map<String, Object> objPool = new HashMap<>();
  
  private Object createObj(String clazzName) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
    Class<?> clazz = Class.forName(clazzName);
    return clazz.newInstance();
  }
  
  public void initPool(String fileName) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
    try(FileInputStream fis = new FileInputStream(fileName)) {
      Properties prop = new Properties();
      prop.load(fis);
      for(String name : prop.stringPropertyNames()) {
        objPool.put(name, createObj(prop.getProperty(name)));
        
      }
    } catch(IOException e) {
      System.out.println("加载" + fileName + "文件失败");
    }
  }
  
  public Object getObj(String name) {
    return objPool.get(name);
  }
  
  public static void main(String[] args) throws Exception {

    ObjectFactory factory = new ObjectFactory();
    factory.initPool("prop.txt");
    System.out.println(factory.getObj("a"));
    System.out.println(factory.getObj("b"));
  }

}
