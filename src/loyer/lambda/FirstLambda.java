package loyer.lambda;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FirstLambda {
  
  public static void main(String[] args) throws InterruptedException, IOException {
    //Timer timer = new Timer(1000, event -> {System.out.println("Lambda");}); 
   // timer.start();
    //while(true);
    repeat(10, () -> {System.out.println("lambda~");});
    List<String> list = new ArrayList<>();
    list.add("I am Loyer");
    list.add("你是谁？");
    list.add("What are you 弄啥嘞？");
    list.forEach(System.out::println);
    list.forEach((s) -> {System.out.println(s);});
    int[] arr = new int[] {2, 23, -9, 28, 5};
    Arrays.parallelPrefix(arr, (left, right) -> {return left * right;});
    System.out.println(Arrays.toString(arr));
    int[] arr1 = new int[5];
    Arrays.parallelSetAll(arr1, (operand) -> {return operand * 5;});
    System.out.println(Arrays.toString(arr1));
    new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
    
  }
  public static void repeat(int n, Runnable action) {
    for(int i = 0; i < n; i++) {
      action.run();
    }
  }
}
