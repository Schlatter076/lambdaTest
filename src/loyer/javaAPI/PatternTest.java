package loyer.javaAPI;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PatternTest {

  public static void main(String[] args) {
    
    //mailJudge();
    replaceAllTest();
  }

  public static void mailJudge() {
    
    String[] mails = { 
        "Futing0805@163.com", 
        "Jinyujuan0908@sina.cn", 
        "Guohuijuan1126@gmail.com",
        "Wangrun1121@qq.com" 
    };
    String mailRegex = "\\w{3,20}@\\w{2,6}\\.(com|cn|org)";
    Pattern mailPattern = Pattern.compile(mailRegex);
    Matcher mailMatcher = null;
    for (String mail : mails) {
      if (mailMatcher == null) {
        mailMatcher = mailPattern.matcher(mail);
      } else
        mailMatcher.reset(mail);
      String result = mail + (mailMatcher.matches() ? "是" : "否") + "一个有效的邮箱地址！";
      System.out.println(result);
    }
  }
  public static void replaceAllTest() {
    
    String[] args = {
        "This is Java regex test",
        "I want replace he to she",
        "The function replaceAll() is very intersting"
    };
    Pattern pattern = Pattern.compile("re\\w*");
    Matcher matcher = null;
    for(String str : args) {
      if(matcher == null) 
        matcher = pattern.matcher(str);
      else
        matcher.reset(str);
      
      System.out.println(matcher.replaceAll("哈哈:)"));
    }
    
  }

}
