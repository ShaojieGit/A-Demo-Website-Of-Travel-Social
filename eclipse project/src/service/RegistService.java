package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RegistService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.Statement st_usernameexist=null;
	java.sql.ResultSet sqlRst_usernameexist=null;

	public String regist_must(String username,String password,String usertype) throws SQLException
	{
		String username_exist;
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
		
		st_usernameexist=conn.createStatement();
		String sqlQuery="select * from user where Username='"+username+"'";
		sqlRst_usernameexist=st_usernameexist.executeQuery(sqlQuery);
		if(sqlRst_usernameexist.next())
			return "exist";
		
		
		st=conn.createStatement();
		sqlQuery="insert into user(Username,Password,Usertype) values('"+username+"','"+password+"','"+usertype+"')";
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
		return "notexist";
	}
	public void regist_email(String email,String username) throws SQLException
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_database","root","770903321");
		System.out.println("与Mysql数据库连接成功!(regist_emai)");
		}
		catch(Exception e)
		{
		System.out.println("连接数据库错误！");
		System.out.println(e.getMessage());
		}
		
		st=conn.createStatement();
		System.out.print("regist_email"+email+username);
		String sqlQuery="update user set Email='"+email+"' where Username='"+username+"'";
		st.executeUpdate(sqlQuery);

		try
		{
		conn.close();
		System.out.println("与Mysql数据库关闭成功!");
		}
		catch(Exception e)
		{
		System.out.println("数据库关闭时出错！");
		System.out.println(e.getMessage());
		}
		
	}
	public void regist_sex(String sex,String username) throws SQLException
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
		String sqlQuery="update user set Sex='"+sex+"' where Username='"+username+"'";
		st.executeUpdate(sqlQuery);

		try
		{
		conn.close();
		System.out.println("与Mysql数据库关闭成功!");
		}
		catch(Exception e)
		{
		System.out.println("数据库关闭时出错！");
		System.out.println(e.getMessage());
		}
	}
	
	public void regist_headimage(String headimage,String username) throws SQLException
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
		String sqlQuery="update user set Headimage='"+headimage+"' where Username='"+username+"'";
		st.executeUpdate(sqlQuery);

		try
		{
		conn.close();
		System.out.println("与Mysql数据库关闭成功!");
		}
		catch(Exception e)
		{
		System.out.println("数据库关闭时出错！");
		System.out.println(e.getMessage());
		}
	}
}
