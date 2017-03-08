package service;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp; 
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class CommentSpotService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	public void comment(String commentuser,String commentcontent,String spotname) throws SQLException
	{
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
		
		st=conn.createStatement();
	//	System.out.println("--------------------------当前评论");
	//	System.out.println(commentcontent);
		String sqlQuery="insert into commentspot(CommentUser,CommentContent,Spotname) values('"+commentuser+"','"+commentcontent+"','"+spotname+"')";

		st.executeUpdate(sqlQuery);
		try
		{
			st.close();

		conn.close();
		System.out.println("与Mysql数据库关闭成功!");
		}
		catch(Exception e)
		{
		System.out.println("数据库关闭时出错！");
		System.out.println(e.getMessage());
		}
	}
	
/*	public int getseconds(String publishtime)   //从2014年1月1号起的秒数
	{2014-08-01 10:12:35.0
		int x;
		x=0;
		x+=((publishtime.charAt(2)-48)*10+publishtime.charAt(3))*365*31536000;
		x+=((publishtime.charAt(5)-48)*10+publishtime.charAt(6))
	}*/
}
