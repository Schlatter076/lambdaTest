package loyer.enuma;

public class GenderEnumTest {

  public static void main(String[] args) {
    
    GenderDemo girl = GenderDemo.FAMALE;
    GenderDemo boy = GenderDemo.MALE;
    
    girl.info();
    System.out.println(girl.getName());
    boy.info();
    System.out.println(boy.getName());
  }

}
