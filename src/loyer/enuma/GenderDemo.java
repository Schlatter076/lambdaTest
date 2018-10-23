package loyer.enuma;

public enum GenderDemo implements GenderDec {
  
  MALE("男") {
    @Override
    public void info() {
      System.out.println("让每个枚举值实现接口，呈现不同的形式之男性~");
    }
  }, 
  FAMALE("女") {
    @Override
    public void info() {
      System.out.println("让每个枚举值实现接口，呈现不同的形式之女性~");
    }
  };
  
  private final String name;
  //枚举类的构造器只能用private修饰
  private GenderDemo(String name) {
    this.name = name;
  }
  
  public String getName() {
    return this.name;
  }
  
  /*
  @Override
  public void info() {
  }
  //*/
  
}
