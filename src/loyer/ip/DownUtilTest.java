package loyer.ip;

public class DownUtilTest {

  public static void main(String[] args) throws Exception {

    //初始化DownUtil对象
    final DownUtil down = new DownUtil("http://qnypy.doubanio.com/201612082352224321__l", "ios.jpg", 4);
    /*
    final DownUtil down = new DownUtil("http://www.crazyit.org/"
        + "attachments/month_1403/1403202355ff6cc9a4fbf6f14a.png"
        , "ios.png", 4);//*/
    
    //开始下载"https://image.baidu.com/search/detail?ct=503316480&z=0&ipn=d&word=%E6%B1%BD%E8%BD%A6%E5%9B%BE%E7%89%87%E4%B8%8B%E8%BD%BD&step_word=&hs=0&pn=2&spn=0&di=917207830&pi=0&rn=1&tn=baiduimagedetail&is=0%2C0&istype=2&ie=utf-8&oe=utf-8&in=&cl=2&lm=-1&st=-1&cs=1049283687%2C1212479962&os=641548647%2C3090580232&simid=3546733275%2C337380460&adpicid=0&lpn=0&ln=1861&fr=&fmq=1544172354149_R&fm=detail&ic=undefined&s=undefined&hd=undefined&latest=undefined&copyright=undefined&se=&sme=&tab=0&width=undefined&height=undefined&face=undefined&ist=&jit=&cg=&bdtype=0&oriquery=&objurl=http%3A%2F%2Fpic.58pic.com%2F58pic%2F15%2F46%2F97%2F88958PICIWu_1024.jpg&fromurl=ippr_z2C%24qAzdH3FAzdH3Fooo_z%26e3Bcbrtv_z%26e3Bv54AzdH3Fzi7wg2fitAzdH3F8c9ml0bb_z%26e3Bip4s&gsm=0&rpstart=0&rpnum=0&islist=&querylist=&selected_tags=0"
    down.downlaod();
    new Thread(() -> {
      while(down.getCompleteRate() < 1) {
        //每隔0.1s查询一次任务的完成进度
        System.out.println("已完成：" + down.getCompleteRate());
        try {
          Thread.sleep(100);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      }
    }).start();
  }

}
