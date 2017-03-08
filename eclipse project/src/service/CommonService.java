package service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CommonService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	
	public String getUsertype(String username) throws SQLException
	{
		String Usertype;
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
		String sqlQuery="select Usertype from user where Username='"+username+"'";
		sqlRst=st.executeQuery(sqlQuery);
		if(sqlRst.next())
			Usertype=sqlRst.getString(1);
		else
			Usertype=null; 

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
		return Usertype;
	}
	
	
	
	
}
