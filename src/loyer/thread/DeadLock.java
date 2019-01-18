package loyer.thread;

public class DeadLock implements Runnable {

  B b = new B();
  A a = new A();
  @Override
  public void run() {
    Thread.currentThread().setName("副线程");
    a.bar(b);
    System.out.println("进入副线程之后");
  }

  public void init() {
    Thread.currentThread().setName("主线程");
    b.foo(a);
  }
  public static void main(String[] args) {

    DeadLock d = new DeadLock();
    new Thread(d).start();
    d.init();
  }
  
  public class A {
    public synchronized void bar(B b) {
      System.out.println(Thread.currentThread().getName() + "试图调用A的bar方法");
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "试图调用B类的last方法");
      b.last();
    }
    public synchronized void last() {
      System.out.println("进入A的last方法");
    }
  }
  public class B {
    public synchronized void foo(A a) {
      System.out.println(Thread.currentThread().getName() + "试图调用B的foo方法");
      try {
        Thread.sleep(200);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + "试图调用A类的last方法");
      a.last();
    }
    public synchronized void last() {
      System.out.println("进入B的last方法");
    }
  }
  //==两个线程进行等待，达到死锁==//

}
