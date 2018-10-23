package loyer.gc;

public class GCtest {
  
  public static void main(String[] args) {
    
    for(int i = 0; i < 4; i++) {
      
      new GCtest();
      //System.gc();  //强制垃圾回收
      Runtime.getRuntime().gc(); //强制垃圾回收
    }
  }
  
  public void finalize() {
    System.out.println("垃圾回收启动！");
  }
  
}
