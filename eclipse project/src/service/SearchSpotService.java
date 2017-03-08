package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import Bean.SimpleSpot;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SearchSpotService {
     public List<SimpleSpot> searchSpot(String searchkey,String keyname)throws SQLException
     {
    	 List<SimpleSpot> spotlist = new ArrayList<SimpleSpot>();
    	 java.sql.Connection conn=null;
    	 java.sql.Statement st=null;
    	 java.sql.ResultSet sqlRst=null;
    	 java.sql.Statement st_image=null;
    	 java.sql.ResultSet sqlRst_image=null;
    	 try
 		 {
 		 Class.forName("com.mysql.jdbc.Driver").newInstance();
 		 conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_database","root","770903321");
 		 System.out.println("与Mysql数据库连接成功!");
 		 }
 		 catch(Exception e)
 		 {
 		 System.out.println("连接数据库错误！");
 		 System.out.println(e.getMessage());
 		 }
    	 String sqlQuery;
    	 st=conn.createStatement();
    	 
    	 if(keyname.equals("景点名称"))
    	 {
    		 System.out.println("搜索关键字 "+searchkey);
    		 sqlQuery="select * from spot where Spotname like '%"+searchkey+"%'";
    	 	 sqlRst=st.executeQuery(sqlQuery);
    	 }
    	 else if(keyname.equals("景点地区"))
    	 {System.out.println("kkkkkkkkkkkkkkkkkkkkkkk");
    		 sqlQuery="select * from spot where Position like '%"+searchkey+"%'";
    	 	 sqlRst=st.executeQuery(sqlQuery);
    	 }
    	 else
    	 {
    		 System.out.println("bbbbbbbbbbbb");
    		 sqlQuery="select * from spot where Level like '%"+searchkey+"%'";
    	 	 sqlRst=st.executeQuery(sqlQuery);
    	 }
	 	 
    	 while(sqlRst.next())
    	 {
    		 SimpleSpot thisSpot = new SimpleSpot(null, null);
    		 thisSpot.setSpotname(sqlRst.getString("Spotname"));
    		 
    		 st_image=conn.createStatement();
    		 sqlQuery="select Imagename from spotimage where Spotname='"+sqlRst.getString("Spotname")+"'";
    		 sqlRst_image = st_image.executeQuery(sqlQuery);
    		 if(sqlRst_image.next())
    			 thisSpot.setImage(sqlRst_image.getString("Imagename"));
    		 spotlist.add(thisSpot);
    	 }
    	 
    	 try
 		{
 			st.close();
 			sqlRst.close();
 		conn.close();
 		System.out.println("与Mysql数据库关闭成功!");
 		}
 		catch(Exception e)
 		{
 		System.out.println("数据库关闭时出错！");
 		System.out.println(e.getMessage());
 		}
     	return spotlist;
     }
}