package loyer.gc;

public class FinalizeTest {
  
  private static FinalizeTest ft = null;
  
  public void info() {
    System.out.println("测试对象的finalize方法！");
  }
  
  public void finalize() {
    //让垃圾回收时，试图将可回复对象变成可达对象
    ft = this;
  }
  public static void main(String[] args) {
    
    //创建FinalizeTest对象，立即进入可恢复状态
    new FinalizeTest();
    //通知系统进行垃圾回收
    System.gc();
    //Runtime.getRuntime().gc();
    //强制垃圾回收调用可恢复对象的finalize方法
    System.runFinalization();
    ft.info();
    
  }
}
