package com.tools.jackson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import com.tools.jackson.po.Student;

/**
 * JSON转换工具
 * @author zhaohuaan
 *
 */
public class JackSonUtil {
	public  static JsonGenerator jsonGenerator = null;
	private static ObjectMapper mapper = new ObjectMapper();
	
	public static void main(String[] args) {
		Student student = new Student();
		student.setIsstudent(true);
		student.setUid(1000);
		student.setUname("xiao liao");
		student.setNumber(12);
		
		Map<String, Student> stuMap = new HashMap<String, Student>();
		stuMap.put("1", student);
		stuMap.put("2", student);
		
		List<Object> stuList = new ArrayList<Object>();
		List<Student> stuList1 = new ArrayList<Student>();
		stuList1.add(student);
		
		student =  new  Student();
		student.setIsstudent(false);
		student.setUid(200);
		student.setUname("xiao mi");
		stuList1.add(student);
		
		stuList.add(student);
		stuList.add("xiao xin");
		stuList.add("xiao er");
		stuList.add(stuMap);
		
		//String[] array = {"1","2","3"};
		try {
			//writeEntity2Json(student);//实体转JSON
			//writeArray2Json(array); //数组转换成json数组
			//writeMap2Json(stuMap);//map转json
			//writeList2Json(stuList1);//List转json
			//writeJson2Entity();//json转实体
			writeJson2List();//json转list
			
			//readJson2Array();
			//readJson2List();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 实体类转换成json
	 * @param object
	 * @throws IOException
	 */
	 public static void writeEntity2Json(Object object) throws IOException {
		 mapper.writeValue(new File("D:/test/1.txt"),object);
		 mapper.writeValue(System.out,object);
		 String json = mapper.writeValueAsString(object);
		 System.out.println(json);
	 }
	/**
	 * 数组转换成json数组
	 * @param object
	 * @throws IOException
	 */
	 public static void writeArray2Json(Object object) throws IOException {	 
		 // writeValue具有和writeObject相同的功能
		 mapper.writeValue( new File("D:/test/2.txt"),object );
		 mapper.writeValue(System.out,object );
		 
	 }
	 /**
	  * map对象转换成json对象
	  * @param object
	  * @throws IOException
	  */
	 public static void writeMap2Json(Object object) throws IOException {
		 // writeValue具有和writeObject相同的功能
		 System.out.println("==>"+mapper.writeValueAsString(object));
		 mapper.writeValue( new File("D:/test/3.txt"),object );
		 mapper.writeValue( System.out , object );
	 }
	 /**
	  * list转换成json
	  * @param object
	  * @throws IOException
	  */
	 public static void writeList2Json(Object object) throws IOException {
		 System.out.println("==>"+mapper.writeValueAsString(object));
		 mapper.writeValue( new File("D:/test/4.txt"),object );
		 mapper.writeValue( System.out , object );
	 }
	 /**
	  * json转换成实体
	  * @throws IOException
	  */
	 public static void writeJson2Entity() throws IOException {
		 System.out.println("json串转换成entity-------------");
		 File file = new File("D:/test/1.txt");
		 FileInputStream inputStream = new FileInputStream(file);
		 Student student = mapper.readValue(inputStream,Student.class);
		 System.out.println(student.toString());
		 //漂亮输出
	
		 //名称、个数保持一致
		 /*String json = "{\"uid\":1000,\"uname\":\"xiao liao\",\"number\":12.0,\"isstudent\":true}";
		 Student student1 = mapper.readValue(json,Student.class);
		 System.out.println("json2:"+student1.toString());*/
	 }
	 /**
	  * json转成list对象
	  * @throws IOException
	  */
	 @SuppressWarnings({"unchecked","rawtypes"})
	 public static void writeJson2List() throws IOException {	 
		 String json = "[{\"uid\":1000,\"uname\":\"xiao liao\",\"upwd\":\"123\",\"number\":12.0,\"isstudent\":true},{\"uid\":200,\"uname\":\"xiao mi\",\"upwd\":null,\"number\":0.0,\"isstudent\":false}]";
		 List<LinkedHashMap<String, Object>> s= mapper.readValue(json,List.class);
		 for (int i = 0; i < s.size(); i++) {
			 LinkedHashMap<String, Object> link = s.get(i);
			 Set<String>  key = link.keySet();
			 for (Iterator iterator = key.iterator(); iterator.hasNext();) {
				String string = (String) iterator.next();
				System.out.println(string+"==>"+link.get(string));
				
			}
			 System.out.println("json:"+i+""+s.get(i).toString());
			
		}
	 }
	 /**
	   * JSON转换为List对象
	   */
	 @SuppressWarnings("unchecked")
	  public static void readJson2List() {
	   String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"},"
	     + "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}]";
	   try {
	    List<LinkedHashMap<String, Object>> list = mapper.readValue(json, List.class);
	    System.out.println(list.size());
	    for (int i = 0; i < list.size(); i++) {
	     Map<String, Object> map = list.get(i);
	     Set<String> set = map.keySet();
	     for (Iterator<String> it = set.iterator(); it.hasNext();) {
	      String key = it.next();
	      System.out.println(key + ":" + map.get(key));
	     }
	    }
	   } catch (JsonParseException e) {
	    e.printStackTrace();
	   } catch (JsonMappingException e) {
	    e.printStackTrace();
	   } catch (IOException e) {
	    e.printStackTrace();
	   }
	  }
	  /**
	   * JSON转换为List对象
	   */
	  public static void readJson2Array() {
		  String json = "[{\"uid\":1,\"uname\":\"www\",\"number\":234,\"upwd\":\"456\"},"
			  + "{\"uid\":5,\"uname\":\"tom\",\"number\":3.44,\"upwd\":\"123\"}]";
		  try {
			  Student[] students = mapper.readValue(json, Student[].class);
			  for (Student student : students) {
				System.out.println(">"+student.toString());
			}
		  } catch (JsonParseException e) {
			  e.printStackTrace();
		  } catch (JsonMappingException e) {
			  e.printStackTrace();
		  } catch (IOException e) {
			  e.printStackTrace();
		  }
	  }
}

