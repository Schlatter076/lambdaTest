package loyer.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class CallableTest {

  public static void main(String[] args) {

    CallableTest cb = new CallableTest();
    //使用lambda表达式封装Callable对象
    FutureTask<Integer> task = new FutureTask<>((Callable<Integer>)()-> {
      int i = 0;
      for(; i < 100; i++) {
        System.out.println(Thread.currentThread().getName() + "::::" + i);
      }
      //call()方法可以有返回值
      return i;
    });
    for(int i = 0; i < 100; i++) {
      System.out.println(Thread.currentThread().getName() + "::::" + i);
      if(i == 20) {
        //以Callable对象来创建并启动线程
        new Thread(task, "有返回值的线程").start();
      } 
    }
    //获取线程返回值
    try {
      System.out.println("线程返回值为：" + task.get());
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    }
    
  }

}
