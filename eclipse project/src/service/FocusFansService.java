package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import Bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class FocusFansService {
     public List<User> getUserinfo(String username,String FocusOrFans) throws SQLException
     {
    	 
    	 List<User> userlist = new ArrayList<User>();
    	 User currentuser;
    	 List<String> namelist = new ArrayList<String>();
    	 String currentusername=null;
    	 String loginusername=null;
    	 String currentuserheadimage=null;
    	 HttpSession session=ServletActionContext.getRequest().getSession();
    	 loginusername=(String)session.getAttribute("user");
    	 
    	 java.sql.Connection conn=null;
    	 java.sql.Statement st=null;
    	 java.sql.ResultSet sqlRst=null;
    	 java.sql.Statement st_headimage=null;
    	 java.sql.ResultSet sqlRst_headimage=null;
    	 java.sql.Statement st_focus=null;
    	 java.sql.ResultSet sqlRst_focus=null;
    	 java.sql.Statement st_focusme=null;
    	 java.sql.ResultSet sqlRst_focusme=null;
    	 
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
    	 String focused;
    	 String focusme;
    	 if(FocusOrFans=="focus")   //查询关注好友列表
    	 {
    		 //获取关注的用户名列表
    		 st=conn.createStatement();
    	 	 sqlQuery="select Friendname from relation where Username='"+username+"'";
    	 	 sqlRst=st.executeQuery(sqlQuery);
    	 	//获取每个用户的头像，判断 当前操作者有没有关注该用户
    	 	 while(sqlRst.next())   
    	 	 {
    	 		currentusername=sqlRst.getString("Friendname");
    	 		//获得头像
    	 		st_headimage=conn.createStatement();
       	 	 	sqlQuery="select Headimage from user where Username='"+currentusername+"'";
       	 	 	sqlRst_headimage=st_headimage.executeQuery(sqlQuery);
       	 	 	if(sqlRst_headimage.next())
       	 	 		currentuserheadimage=sqlRst_headimage.getString("Headimage");
       	 	 	//是否已经被当前用户登录
		   	 	st_focus=conn.createStatement();
		 	 	sqlQuery="select * from relation where Username='"+loginusername+"' and Friendname='"+currentusername+"'";
		 	 	sqlRst_focus=st_focus.executeQuery(sqlQuery);
		 	 	if(sqlRst_focus.next())
		 	 		focused="yes";
		 	 	else
		 	 		focused="no";
		 	 	
		 	 	//下面查找该用户是不是自己的粉丝
		 	 	st_focusme=conn.createStatement();
		 	 	sqlQuery="select * from relation where Username='"+currentusername+"' and Friendname='"+loginusername+"'";
		 	 	sqlRst_focusme=st_focusme.executeQuery(sqlQuery);
		 	 	if(sqlRst_focusme.next())
		 	 		focusme="yes";
		 	 	else
		 	 		focusme="no";
		 	 	currentuser=new User(currentusername,currentuserheadimage,focused,focusme);
		 	 	userlist.add(currentuser);
    	 	 }
    	 }
    	 else    //查询粉丝列表
    	 {
    		 st=conn.createStatement();
    	 	 sqlQuery="select Username from relation where Friendname='"+username+"'";
    	 	 sqlRst=st.executeQuery(sqlQuery);
    	 	//获取每个用户的头像，判断 当前操作者有没有关注该用户
    	 	 while(sqlRst.next())   
    	 	 {
    	 		currentusername=sqlRst.getString("Username");
    	 		//获得头像
    	 		st_headimage=conn.createStatement();
       	 	 	sqlQuery="select Headimage from user where Username='"+currentusername+"'";
       	 	 	sqlRst_headimage=st_headimage.executeQuery(sqlQuery);
       	 	 	if(sqlRst_headimage.next())
       	 	 		currentuserheadimage=sqlRst_headimage.getString("Headimage");
       	 	 	//是否已经被当前用户登录
		   	 	st_focus=conn.createStatement();
		 	 	sqlQuery="select * from relation where Username='"+loginusername+"' and Friendname='"+currentusername+"'";
		 	 	sqlRst_focus=st_focus.executeQuery(sqlQuery);
		 	 	if(sqlRst_focus.next())
		 	 		focused="yes";
		 	 	else
		 	 		focused="no";
		 	 	
		 	 //下面查找该用户是不是自己的粉丝
		 	 	st_focusme=conn.createStatement();
		 	 	sqlQuery="select * from relation where Username='"+currentusername+"' and Friendname='"+loginusername+"'";
		 	 	sqlRst_focusme=st_focusme.executeQuery(sqlQuery);
		 	 	if(sqlRst_focusme.next())
		 	 		focusme="yes";
		 	 	else
		 	 		focusme="no";
		 	 	currentuser=new User(currentusername,currentuserheadimage,focused,focusme);
		 	 	userlist.add(currentuser);
    	 	 }
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
     public User getThisUserinfo(String username) throws SQLException
     {
    	 java.sql.Connection conn=null;
    	 java.sql.Statement st_focus=null;
    	 java.sql.ResultSet sqlRst_focus=null;  	 
    	 
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
    	 
    	 String loginusername=null;
    	 HttpSession session=ServletActionContext.getRequest().getSession();
    	 loginusername=(String)session.getAttribute("user");
    	 String focus;
    	 User thisUser=new User(null, null, null, null);
    	 
    	//获取关注的用户名列表
    	 st_focus=conn.createStatement();
    	  String sqlQuery="select * from relation where Username='"+loginusername+"' and Friendname = '"+username+"'";
    	  sqlRst_focus=st_focus.executeQuery(sqlQuery);
	 	if(sqlRst_focus.next())
	 		focus="yes";
	 	else
	 		focus="no";
	 	thisUser.setFocused(focus);
	 	thisUser.setUsername(username);
    	 return thisUser;
     }
}
