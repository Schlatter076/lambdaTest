package loyer.collection;

import java.util.HashMap;
import java.util.Map;

public class MapTest {

  public static void main(String[] args) {

    //basicFunction();
    java8Function();
   
  }
  public static void basicFunction() {
    
    Map<String, Integer> map = new HashMap<>();
    // 存入键值对
    map.put("Neo", 8217);
    map.put("Goudan", 8218);
    map.put("Loyer", 8219);
    // 值可以重复
    map.put("Schlatter", 8219);
    System.out.println(map); // {Loyer=8219, Neo=8217, Goudan=8218, Schlatter=8219}
    // 如果键重复，新的值将会覆盖旧值，并将旧值返回
    System.out.println(map.put("Loyer", 4076)); // 8219
    System.out.println(map); // {Loyer=4076, Neo=8217, Goudan=8218, Schlatter=8219}
    // 判定是否包含指定键
    System.out.println(map.containsKey("Loyer")); // true
    // 判定是否包含制定值
    System.out.println(map.containsValue(4076)); // true
    // 根据键取出值
    for (String key : map.keySet()) {
      System.out.println(key + "-->" + map.get(key));
      // Loyer-->4076
      // Neo-->8217
      // Goudan-->8218
      // Schlatter-->8219
    }
    //根据key删除键值对
    map.remove("Loyer");
    System.out.println(map);  //{Neo=8217, Goudan=8218, Schlatter=8219}

  }
  public static void java8Function() {
    
    Map<String, Integer> map = new HashMap<>();
    // 存入键值对
    map.put("Neo", 8217);
    map.put("Goudan", 8218);
    map.put("Loyer", 8219);
    System.out.println(map);  //{Loyer=8219, Neo=8217, Goudan=8218}
    //尝试替换,如果不包含键，则不做任何操作，并返回null
    System.out.println(map.replace("Schlatter", 4076));  //null
    //使用原value和新传入的值计算的结果，替换原value,返回计算后的新值
    System.out.println(map.merge("Loyer", 10, (oldVal, newVal) -> oldVal + newVal));  //8229
    System.out.println(map);  //{Loyer=8229, Neo=8217, Goudan=8218}
    //通过指定的key来计算新值，如果原值为null，则将key和计算后的新值添加到集合中
    map.computeIfAbsent("Sch", key -> key.length() * 3);
    System.out.println(map);  //{Loyer=8229, Sch=9, Neo=8217, Goudan=8218}
    //通过指定的key来计算新值，如果原值存在，则将key和计算后的新值添加到集合中
    map.computeIfPresent("Sch", (key, value) -> value * value);
    System.out.println(map);  //{Loyer=8229, Sch=81, Neo=8217, Goudan=8218}
  }

}
