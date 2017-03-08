package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class PublishService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;

	public void publish(String actiontext,String actionimage,String username) throws SQLException
	{
		System.out.println("发布状态数据库操作开始");
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
		String sqlQuery;
		if(actionimage!=null)
			sqlQuery="insert into action(ActionText,ActionImage,Username) values('"+actiontext+"','"+actionimage+"','"+username+"')";
		else
			sqlQuery="insert into action(ActionText,Username) values('"+actiontext+"','"+username+"')";
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
		System.out.println("发布状态数据库操作结束");
	}
}
