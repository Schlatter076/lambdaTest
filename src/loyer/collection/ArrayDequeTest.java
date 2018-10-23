package loyer.collection;

import java.util.ArrayDeque;

public class ArrayDequeTest {

  public static void main(String[] args) {

    //asStack();
    asQueue();
  }
  public static void asStack() {
    
    ArrayDeque<String> stack = new ArrayDeque<String>();
    //入栈
    stack.push("栈顶");
    stack.push("元素");
    System.out.println(stack); //[元素, 栈顶]
    //访问第一个元素，但并不出栈
    System.out.println(stack.peek());  //元素
    System.out.println(stack);  //[元素, 栈顶]
    //将第一个元素出栈
    System.out.println(stack.pop());  //元素
    System.out.println(stack);  //[栈顶]
    
  }
  public static void asQueue() {
    
    ArrayDeque<String> queue = new ArrayDeque<>();
    queue.offer("队列头");
    queue.offer("元素");
    queue.offer("队列尾");
    System.out.println(queue);  //[队列头, 元素, 队列尾]
    //访问第一个元素，不出队列
    System.out.println(queue.peek());  //队列头
    System.out.println(queue);  //[队列头, 元素, 队列尾]
    //将第一个元素出队列
    System.out.println(queue.poll());  //队列头
    System.out.println(queue);  //[元素, 队列尾]
  }

}
