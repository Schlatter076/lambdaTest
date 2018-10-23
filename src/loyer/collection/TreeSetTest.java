package loyer.collection;

import java.util.TreeSet;

/**
 * TreeSet是有序的，元素顺序按照元素实际大小排列，与插入的顺序无关
 * @author hw076
 *
 */
public class TreeSetTest {

  public static void main(String[] args) {

    TreeSet<Integer> nums = new TreeSet<>();
    nums.add(5);
    nums.add(10);
    nums.add(9);
    nums.add(-3);
    System.out.println(nums);  //[-3, 5, 9, 10]
    System.out.println(nums.first());  //-3
    System.out.println(nums.last());  //10
    //获取TreeSet的子集，取小于4的元素
    System.out.println(nums.headSet(4));  //[-3]
    //获取nums的子集，取大于等于5的元素
    System.out.println(nums.tailSet(5));  //[5, 9, 10]
    //取nums的子集，大于等于-3小于4
    System.out.println(nums.subSet(-3, 4));  //[-3]
    
    //以下演示自定义排序，元素以降序排列,直接给TreeSet集合传入一个Comparator
    TreeSet<Integer> myInt = new TreeSet<>((o1, o2) -> {
      return o2 - o1;
    });
    myInt.add(-9);
    myInt.add(4);
    myInt.add(10);
    System.out.println(myInt);
  }

}
