package loyer.collection;

import java.util.Collection;
import java.util.EnumSet;
import java.util.HashSet;

public class EnumSetTest {

  public static void main(String[] args) {

    //创建一个EnumSet，集合元素就是Season枚举类的全部枚举值
    EnumSet<Season> es1 = EnumSet.allOf(Season.class);
    System.out.println(es1);  //[SPRING, SUMMER, FALL, WINTER]
    //创建一个空的EnumSet集合，元素为Season枚举类的枚举值
    EnumSet<Season> es2 = EnumSet.noneOf(Season.class);
    System.out.println(es2);  //[]
    es2.add(Season.SUMMER);
    es2.add(Season.WINTER);
    System.out.println(es2);  //[SUMMER, WINTER]
    //以指定枚举值创建EnumSet集合
    EnumSet<Season> es3 = EnumSet.of(Season.FALL, Season.SPRING, Season.WINTER);
    System.out.println(es3);  //[SPRING, FALL, WINTER]
    EnumSet<Season> es4 = EnumSet.range(Season.SUMMER, Season.WINTER);
    System.out.println(es4);  //[SUMMER, FALL, WINTER]
    //创建一个EnumSet集合和es4具有相同类型的元素,es4 + es5等于Season枚举类的全部元素
    EnumSet<Season> es5 = EnumSet.complementOf(es4);  //[SPRING]
    System.out.println(es5);
    
    Collection<Season> c = new HashSet<>();
    c.clear();
    c.add(Season.FALL);
    c.add(Season.SUMMER);
    //复制集合到EnumSet集合，两个集合的元素必须是同一类型枚举类的枚举值,否则将抛出异常
    EnumSet<Season> es6 = EnumSet.copyOf(c);
    System.out.println(es6);  //[SUMMER, FALL]
    
      
  }

}
enum Season {
  SPRING, SUMMER, FALL, WINTER;
}