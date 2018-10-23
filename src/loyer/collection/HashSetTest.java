package loyer.collection;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;

public class HashSetTest {

  public static void main(String[] args) {

    HashSet<R> hash = new HashSet<R>();
    hash.add(new R(5));
    hash.add(new R(-3));
    hash.add(new R(9));
    hash.add(new R(-2));
    //HashSet集合不允许重复
    System.out.println(hash);
    //取出第一个元素
    Iterator<R> it = hash.iterator();
    R first = it.next();
    //为first的count赋值 
    first.count = -3;
    System.out.println(hash);  //此时出现了重复的元素
    //删除掉count为-3的对象
    hash.remove(new R(-3));
    System.out.println(hash); //可以看到已经删掉一个count为-3的元素
    //查看hash中是否包含count为-3的元素
    System.out.println(hash.contains(new R(-3)));   //false
    //查看hash中是否包含count为-2的元素
    System.out.println(hash.contains(new R(-2)));  //false
    /*
     * 总结：当把可变对象添加至Set后，尽量不要修改参与hashcode()和equals()计算的实例变量值
     * 否则可能引起Set混乱，以及Set无法正确操作这些变量
     */
    
    //===================================================================================
    LinkedHashSet<String> lhs = new LinkedHashSet<>();
    lhs.add("第一");
    lhs.add("第二");
    System.out.println(lhs);  //[第一, 第二]
    lhs.remove("第一");
    lhs.add("第一");
    System.out.println(lhs);  //[第二, 第一]
    //可见LinkedHashSet顺序和添加的顺序一致(其内部是靠链表维持关系)
  }
  
}

class R {
  
  int count;
  public R(int count) {
    this.count = count;
  }
  @Override
  public int hashCode() {
    return this.count;
  }

  @Override
  public boolean equals(Object obj) {
    if(obj != null && obj.getClass() == R.class) {
      R r = (R)obj;
      return this.count == r.count;
    }
    if(this == obj) 
      return true;
    return false;
  }
  @Override
  public String toString() {
    return "R[count: " + count + "]";
  }
  
}