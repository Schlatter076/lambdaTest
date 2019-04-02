package loyer.xmlRead;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class TableTools {

  private static DocumentBuilderFactory factory = null;
  private static DocumentBuilder builder = null;
  private static Document document = null;
  
  static {
    factory = DocumentBuilderFactory.newInstance();
    try {
      builder = factory.newDocumentBuilder();
    } catch (ParserConfigurationException e) {
      e.printStackTrace();
    }
  }
  
  /**
   * 获取配置文件表名
   * @param tableName
   * @return
   * @throws ParserConfigurationException 
   * @throws IOException 
   * @throws SAXException 
   */
  public static List<Table> getTable(String tableName) throws SAXException, IOException {
    List<Table> list = new ArrayList<>();
    document = builder.parse(new File(tableName));
    NodeList tableList = document.getElementsByTagName("table");
    for(int i = 0; i < tableList.getLength(); i++) {
      Node node = tableList.item(i); //获取结点
      NamedNodeMap namedNodeMap = node.getAttributes();
      //获取id属性值
      String id = namedNodeMap.getNamedItem("id").getTextContent();
      //获取结点的子结点
      NodeList childNodes = node.getChildNodes();
      //将子结点的属性添加到list
      ArrayList<String> childList = new ArrayList<>();
      for(int j = 1; j < childNodes.getLength(); j += 2) {
        Node chilNode = childNodes.item(j);
        childList.add(chilNode.getFirstChild().getTextContent());
      }
      list.add(new Table(Integer.parseInt(id), childList.get(0), childList.get(1)));
    }
    return list;
  }
  
  public static class Table {
    private int id;
    private String name;
    private String value;
    
    
    public int getId() {
      return id;
    }


    public void setId(int id) {
      this.id = id;
    }


    public String getName() {
      return name;
    }


    public void setName(String name) {
      this.name = name;
    }


    public String getValue() {
      return value;
    }


    public void setValue(String value) {
      this.value = value;
    }


    public Table() {
      super();
    }

    public Table(int id, String name, String value) {
      super();
      this.id = id;
      this.name = name;
      this.value = value;
    }

    @Override
    public String toString() {
      return "Table [id=" + id + ", name=" + name + ", value=" + value + "]";
    }
    
  }
  
  public static void main(String[] args) throws SAXException, IOException {
    List<Table> list = getTable("tables.xml");
    System.out.println(list.get(0));
    for(Table table: list) {
      System.out.println(table.getId());
      System.out.println(table.getName());
      System.out.println(table.getValue());
    }
  }
}
