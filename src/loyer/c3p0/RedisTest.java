package loyer.c3p0;

import java.util.List;
import java.util.Set;

import redis.clients.jedis.Jedis;

public class RedisTest {

  public static void main(String[] args) {
    //connect();
    //stringTest();
    //listTest();
    keysTest();
    
  }
  public static void connect() {
    Jedis jedis = new Jedis("localhost");
    jedis.auth("123456");
    System.out.println("连接成功");
    System.out.println("服务正在运行:" + jedis.ping());
    jedis.close();
  }
  public static void stringTest() {
    Jedis jedis = new Jedis("localhost");
    jedis.auth("123456");
    System.out.println("连接成功");
    //redis String
    jedis.set("loyer", "无故寻愁觅恨");
    System.out.println("loyer:" + jedis.get("loyer"));
    jedis.close();
  }
  public static void listTest() {
    Jedis jedis = new Jedis("localhost");
    jedis.auth("123456");
    System.out.println("连接成功");
    jedis.lpush("site-list", "泡泡");
    jedis.lpush("site-list", "强");
    jedis.lpush("site-list", "姜");
    List<String> list = jedis.lrange("site-list", 0, 2);
    for(String s : list) {
      System.out.println("列表项为:" + s);
    }
    jedis.close();
  }
  public static void keysTest() {
    Jedis jedis = new Jedis("localhost");
    jedis.auth("123456");
    System.out.println("连接成功");
    Set<String> keys = jedis.keys("*");
    for(String s : keys) {
      System.out.println(s);
    }
    jedis.close();
  }

}
