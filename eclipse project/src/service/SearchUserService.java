
package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import Bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class SearchUserService {
     public List<User> searchUser(String searchkey)throws SQLException
     {
    	 List<User> userlist = new ArrayList<User>();
    	 java.sql.Connection conn=null;
    	 java.sql.Statement st=null;
    	 java.sql.ResultSet sqlRst=null;
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
	 	 sqlQuery="select * from user where Username like '%"+searchkey+"%'";
	 	 sqlRst=st.executeQuery(sqlQuery);
    	 while(sqlRst.next())
    	 {
    		 User thisUser = new User(null, null, null, null);
    		 thisUser.setUsername(sqlRst.getString("Username"));
    		 thisUser.setHeadimagename(sqlRst.getString("Headimage"));
    		 userlist.add(thisUser);
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
     	return userlist;
     }
}