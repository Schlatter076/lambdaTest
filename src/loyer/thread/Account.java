package loyer.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Account {
  
  /**显式锁对象*/
  private final Lock lock = new ReentrantLock();
  /**获得锁对象的Condition*/
  private final Condition condition = lock.newCondition();
  
  private String accountNo;
  private double balance;
  /**是否存了钱*/
  private boolean hasDeposit = false;
  
  public String getAccountNo() {
    return accountNo;
  }
  public void setAccountNo(String accountNo) {
    this.accountNo = accountNo;
  }
  public double getBalance() {
    return balance;
  }
  public Account() {
    super();
  }
  public Account(String accountNo, double balance) {
    super();
    this.accountNo = accountNo;
    this.balance = balance;
  }
  /**
   * 取钱的方法
   * @param drawAmount
   */
  public void draw(double drawAmount) {
    lock.lock(); //加锁
    try {
      if(!hasDeposit) {
        condition.await();  //如没有存钱，则线程等待
      } else {
        System.out.println(Thread.currentThread().getName() + "取了" + drawAmount + "元钱");
        balance -= drawAmount;
        System.out.println("账户余额为：" + balance);
        hasDeposit = false;
        condition.signalAll();  //唤醒其他所有线程
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      lock.unlock();  //释放锁
    }
    
  }
  /**
   * 存钱的方法
   * @param depositAmount
   */
  public void deposit(double depositAmount) {
    lock.lock();
    try {
      if(hasDeposit) {
        condition.await(); //如果已经存了钱，则线程等待
      } else {
        System.out.println(Thread.currentThread().getName() + "存了" + depositAmount + "元钱");
        balance += depositAmount;
        System.out.println("账户余额为：" + balance);
        hasDeposit = true;
        condition.signalAll(); //唤醒其他线程去取钱
      }
    } catch (InterruptedException e) {
      // TODO: handle exception
      e.printStackTrace();
    } finally {
      lock.unlock();
    }
  }
  

}
