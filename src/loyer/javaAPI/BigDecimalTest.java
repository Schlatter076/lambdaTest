package loyer.javaAPI;

import java.math.BigDecimal;

public class BigDecimalTest {

  public static void main(String[] args) {

    BigDecimal f1 = new BigDecimal("0.05");
    BigDecimal f2 = BigDecimal.valueOf(0.01);
    BigDecimal f3 = new BigDecimal(0.05);
    System.out.println("用String做BigDecimal的构造函数~");
    System.out.println(f1.add(f2)); // 加
    System.out.println(f1.subtract(f2)); // 减
    System.out.println(f1.multiply(f2)); // 乘
    System.out.println(f1.divide(f2)); // 除
    System.out.println("用double做BigDecimal的构造函数~");
    System.out.println(f3.add(f2)); // 加
    System.out.println(f3.subtract(f2)); // 减
    System.out.println(f3.multiply(f2)); // 乘
    System.out.println(f3.divide(f2)); // 除

    /*
     * 以下为打印结果===================================================
     * 用String做BigDecimal的构造函数~ 
     * 0.06 
     * 0.04 
     * 0.0005 
     * 5 
     * 用double做BigDecimal的构造函数~
     * 0.06000000000000000277555756156289135105907917022705078125
     * 0.04000000000000000277555756156289135105907917022705078125
     * 0.0005000000000000000277555756156289135105907917022705078125
     * 5.000000000000000277555756156289135105907917022705078125
     * 所以选用String做BigDecimal的构造函数的参数明显靠谱===============================
     */
  }

}
