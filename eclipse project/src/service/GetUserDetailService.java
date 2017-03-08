package service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import Bean.UserDetail;
public class GetUserDetailService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	
	public UserDetail getUserService(String username) throws SQLException
	{
		UserDetail ThisUserDetail = new UserDetail(null, null, null, null, null, null);
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
		String sqlQuery="select * from user where Username='"+username+"'";
		sqlRst=st.executeQuery(sqlQuery);
		if(sqlRst.next())
		{
			ThisUserDetail.setEmail(sqlRst.getString("Email"));
			ThisUserDetail.setHeadimagename(sqlRst.getString("Headimage"));
			ThisUserDetail.setSex(sqlRst.getString("Sex"));
			ThisUserDetail.setSpot(sqlRst.getString("Spot"));
			ThisUserDetail.setUsername(username);
			ThisUserDetail.setUsertype(sqlRst.getString("Usertype"));
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
		return ThisUserDetail;
	}
	
	
	
	
}
