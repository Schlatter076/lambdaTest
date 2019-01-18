package loyer.ip;

import java.net.InetAddress;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class InetAddressTest {

  public static void main(String[] args) throws Exception {

    InetAddress ip = InetAddress.getByName("localhost");  //根据主机名来获取IP地址
    System.out.println(ip.isReachable(2000)); //判断是否可达
    System.out.println(ip.getHostAddress());  //打印ip地址字符串
    //根据原始IP地址获取Ip地址
    InetAddress net = InetAddress.getByAddress(new byte[] {127, 0, 0, 1});
    System.out.println(net.isReachable(9600));
    System.out.println(net.getCanonicalHostName());  //获取全限定域名
    
    //将 application/x-www-form-urlencoded字符串转换成普通字符串
    System.out.println(URLDecoder.decode("%E7%96%AF%E7%8B%82java", "UTF-8"));//疯狂java
    //将普通字符串转换成application/x-www-form-urlencoded字符串
    System.out.println(URLEncoder.encode("我们都是好孩子", "UTF-8"));//%E6%88%91%E4%BB%AC%E9%83%BD%E6%98%AF%E5%A5%BD%E5%AD%A9%E5%AD%90
//    System.out.println(URLEncoder.encode("我们都是好孩子", "GBK"));/%CE%D2%C3%C7%B6%BC%CA%C7%BA%C3%BA%A2%D7%D3
    
  }

}
