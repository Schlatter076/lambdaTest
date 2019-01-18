package loyer.nio;

import java.io.IOException;
import java.nio.file.FileVisitResult;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.SimpleFileVisitor;
import java.nio.file.attribute.BasicFileAttributes;

public class FileVisitorTest {

  public static void main(String[] args) throws Exception {
    
    Files.walkFileTree(Paths.get("D:", "Eclipse", "lambdaTest"), new SimpleFileVisitor<Path> (){

      @Override
      public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
        System.out.println("正在访问" + dir + "路径");
        return FileVisitResult.CONTINUE;
      }

      @Override
      public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
        System.out.println("正在访问" + file + "文件");
        if(file.endsWith("FileVisitorTest.java")) {
          System.out.println("找到" + file + "文件");
          return FileVisitResult.TERMINATE;
        }
        return FileVisitResult.CONTINUE;
        
      }
      
    });

  }

}
