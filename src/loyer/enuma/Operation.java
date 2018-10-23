package loyer.enuma;

public enum Operation {
  
  PLUS {

    @Override
    public double eval(double x, double y) {
      return x + y;
    }
    
  },
  MINUS {

    @Override
    public double eval(double x, double y) {
      return x - y;
    }
    
  },
  TIMES {

    @Override
    public double eval(double x, double y) {
      return x * y;
    }
    
  },
  DIVIDE {

    @Override
    public double eval(double x, double y) {
      return x / y;
    }
    
  };
  //这个抽象方法由不同的枚举值实现
  public abstract double eval(double x, double y);
  public static void main(String[] args) {
    
    Operation plus = Operation.PLUS;
    Operation minus = Operation.MINUS;
    Operation times = Operation.TIMES;
    Operation divide = Operation.DIVIDE;
    
    System.out.println(plus.eval(3, 4));
    System.out.println(minus.eval(5, 4));
    System.out.println(times.eval(3, 4));
    System.out.println(divide.eval(3, 4));
    
  }
  
}
