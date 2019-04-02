package loyer.javaAPI;

import java.io.IOException;
import java.util.Scanner;

public class SimpleAI {

  public static void main(String[] args) throws IOException {

    for(Scanner sc = new Scanner(System.in);;) {
      System.out.println(sc.nextLine().replaceAll("吗?([？?])", "！"));
    }
    /*
    while(true) {
      
      BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
      System.out.println(br.readLine().replaceAll("吗?([？?])", "！") + System.getProperty("line.separator"));
    }//*/
  }

}
