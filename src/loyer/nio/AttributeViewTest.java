package loyer.nio;

import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.attribute.BasicFileAttributeView;
import java.nio.file.attribute.BasicFileAttributes;
import java.nio.file.attribute.DosFileAttributeView;
import java.nio.file.attribute.FileOwnerAttributeView;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.util.List;

public class AttributeViewTest {

  public static void main(String[] args) throws Exception {

    //希望获取的文件
    Path testPath = Paths.get("D:\\Eclipse\\lambdaTest\\src\\loyer\\nio\\AttributeViewTest.java");
    //访问文件基本属性的视图
    BasicFileAttributeView basicView = Files.getFileAttributeView(testPath, BasicFileAttributeView.class);
    //获取文件基本属性
    BasicFileAttributes basicAttr = basicView.readAttributes();
    //访问文件的基本属性
    System.out.println("创建时间：" + basicAttr.creationTime());
    System.out.println("最后访问时间：" + basicAttr.lastAccessTime());
    System.out.println("最后修改时间：" + basicAttr.lastAccessTime());
    System.out.println("文件大小：" + basicAttr.size());
    //获取访问文件属主信息的视图
    FileOwnerAttributeView ownerView = Files.getFileAttributeView(testPath, FileOwnerAttributeView.class);
    //获取文件所属的用户
    System.out.println("文件所属用户：" + ownerView.getOwner());
    //获取系统中guest用户
    //UserPrincipal user = FileSystems.getDefault().getUserPrincipalLookupService().lookupPrincipalByName("guest");
    //修改用户
    //ownerView.setOwner(user);
    //获取访问自定义属性的视图
    UserDefinedFileAttributeView userView = Files.getFileAttributeView(testPath, UserDefinedFileAttributeView.class);
    List<String> userList = userView.list();
    for(String str : userList) {
      ByteBuffer buffer = ByteBuffer.allocate(userView.size(str));
      userView.read(str, buffer);
      buffer.flip();
      String value = Charset.defaultCharset().decode(buffer).toString();
      System.out.println(str + "-->" + value);
    }
    //添加一个自定义属性
    userView.write("发行", Charset.defaultCharset().encode("全能大法好~"));
    DosFileAttributeView dosView = Files.getFileAttributeView(testPath, DosFileAttributeView.class);
    //将文件设置为隐藏、只读
    dosView.setHidden(false);
    dosView.setReadOnly(false);
    
  }

}
