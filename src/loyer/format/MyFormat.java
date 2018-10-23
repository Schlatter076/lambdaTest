package loyer.format;

import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class MyFormat {

  public static void main(String[] args) throws ParseException {

    // numberFormatTest();
    //dateFormatTest();
    //simpleDateFormat();
    dateTimeFormatterTest();
  }

  public static void numberFormatTest() {

    double db = 1234000.567; // 需要格式化的数字
    Locale[] locales = { Locale.CHINA, Locale.JAPAN, Locale.GERMAN, Locale.US }; // 创建Locale
    NumberFormat[] format = new NumberFormat[12]; // 创建格式化对象数组
    for (int i = 0; i < locales.length; i++) {
      format[i * 3] = NumberFormat.getNumberInstance(locales[i]); // 通用数值格式器
      format[i * 3 + 1] = NumberFormat.getPercentInstance(locales[i]); // 百分数格式器
      format[i * 3 + 2] = NumberFormat.getCurrencyInstance(locales[i]); // 货币格式器
    }
    for (int i = 0; i < locales.length; i++) {
      String tip = i == 0 ? "----中国的格式----" : i == 1 ? "----日本的格式----" : i == 2 ? "----德国的格式----" : "美国的格式";

      System.out.println(tip);
      System.out.println("通用数值格式：" + format[i * 3].format(db));
      System.out.println("百分数格式：" + format[i * 3 + 1].format(db));
      System.out.println("货币格式：" + format[i * 3 + 2].format(db));
    }
    // ----中国的格式----
    // 通用数值格式：1,234,000.567
    // 百分数格式：123,400,057%
    // 货币格式：￥1,234,000.57
    // ----日本的格式----
    // 通用数值格式：1,234,000.567
    // 百分数格式：123,400,057%
    // 货币格式：￥1,234,001
    // ----德国的格式----
    // 通用数值格式：1.234.000,567
    // 百分数格式：123.400.057%
    // 货币格式：¤ 1.234.000,57
    // 美国的格式
    // 通用数值格式：1,234,000.567
    // 百分数格式：123,400,057%
    // 货币格式：$1,234,000.57
  }

  public static void dateFormatTest() {

    Date date = new Date(); // 创建需要格式化的日期
    Locale[] locales = { Locale.CHINA, Locale.US };
    DateFormat[] df = new DateFormat[16];
    for (int i = 0; i < locales.length; i++) {
      df[i * 8] = DateFormat.getDateInstance(DateFormat.SHORT, locales[i]);
      df[i * 8 + 1] = DateFormat.getDateInstance(DateFormat.MEDIUM, locales[i]);
      df[i * 8 + 2] = DateFormat.getDateInstance(DateFormat.LONG, locales[i]);
      df[i * 8 + 3] = DateFormat.getDateInstance(DateFormat.FULL, locales[i]);
      df[i * 8 + 4] = DateFormat.getTimeInstance(DateFormat.SHORT, locales[i]);
      df[i * 8 + 5] = DateFormat.getTimeInstance(DateFormat.MEDIUM, locales[i]);
      df[i * 8 + 6] = DateFormat.getTimeInstance(DateFormat.LONG, locales[i]);
      df[i * 8 + 7] = DateFormat.getTimeInstance(DateFormat.FULL, locales[i]);
    }
    for (int i = 0; i < locales.length; i++) {
      String tip = i == 0 ? "----中国的日期格式----" : "----美国的日期格式----";
      System.out.println(tip);
      System.out.println("SHORT格式的日期格式：" + df[i * 8].format(date));
      System.out.println("MEDIUM格式的日期格式：" + df[i * 8 + 1].format(date));
      System.out.println("LONG格式的日期格式：" + df[i * 8 + 2].format(date));
      System.out.println("FULL格式的日期格式：" + df[i * 8 + 3].format(date));
      System.out.println("SHORT格式的时间格式：" + df[i * 8 + 4].format(date));
      System.out.println("MEDIUM格式的时间格式：" + df[i * 8 + 5].format(date));
      System.out.println("LONG格式的时间格式：" + df[i * 8 + 6].format(date));
      System.out.println("FULL格式的时间格式：" + df[i * 8 + 7].format(date));

    }
    // ----中国的日期格式----
    // SHORT格式的日期格式：18-10-17
    // MEDIUM格式的日期格式：2018-10-17
    // LONG格式的日期格式：2018年10月17日
    // FULL格式的日期格式：2018年10月17日 星期三
    // SHORT格式的时间格式：上午10:22
    // MEDIUM格式的时间格式：10:22:59
    // LONG格式的时间格式：上午10时22分59秒
    // FULL格式的时间格式：上午10时22分59秒 CST
    // ----美国的日期格式----
    // SHORT格式的日期格式：10/17/18
    // MEDIUM格式的日期格式：Oct 17, 2018
    // LONG格式的日期格式：October 17, 2018
    // FULL格式的日期格式：Wednesday, October 17, 2018
    // SHORT格式的时间格式：10:22 AM
    // MEDIUM格式的时间格式：10:22:59 AM
    // LONG格式的时间格式：10:22:59 AM CST
    // FULL格式的时间格式：10:22:59 AM CST
  }
  public static void simpleDateFormat() throws ParseException {
    Date date = new Date();
    SimpleDateFormat format = new SimpleDateFormat("Gyyyy中的第D天");
    String dateStr = format.format(date);
    System.out.println(dateStr);
    String str = "18###10##17";
    SimpleDateFormat sf = new SimpleDateFormat("y###MM##d");
    System.out.println(sf.parse(str));
    // 公元2018中的第290天
    // Wed Oct 17 00:00:00 CST 2018
  }
  public static void dateTimeFormatterTest() {
    String time = "2014==04==12 01时09分09秒";  //随意创建一个日期时间字符串
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy==MM==dd HH时mm分ss秒");
    //解析字符串
    LocalDateTime ldt1 = LocalDateTime.parse(time, formatter);
    System.out.println(ldt1);
    String str = "2014$$$四月$$$13 20小时";
    DateTimeFormatter f2 = DateTimeFormatter.ofPattern("yyyy$$$MMM$$$dd HH小时");
    LocalDateTime ldt2 = LocalDateTime.parse(str, f2);
    System.out.println(ldt2);
    
    // 2014-04-12T01:09:09
    // 2014-04-13T20:00
  }
  

}
