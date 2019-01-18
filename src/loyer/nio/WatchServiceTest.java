package loyer.nio;

import java.nio.file.FileSystems;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class WatchServiceTest {

  public static void main(String[] args) throws Exception {

    WatchService watchService = FileSystems.getDefault().newWatchService();  //获取文件系统的WatchService对象
    //为D盘根目录注册监视器
    Paths.get("D:/").register(watchService, StandardWatchEventKinds.ENTRY_CREATE, 
        StandardWatchEventKinds.ENTRY_MODIFY, StandardWatchEventKinds.ENTRY_DELETE);
    while(true) {
      //获取下一个文件变化事件
      WatchKey watchKey = watchService.take();
      for(WatchEvent<?> event : watchKey.pollEvents()) {
        System.out.println(event.context() + " 文件发生了 " + event.kind() + " 事件!");
      }
      //重设WatchKey
      boolean valid = watchKey.reset();
      if(!valid) {
        break;
      }
      
    }
    
  }

}
