package service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.servlet.http.*;
import org.apache.struts2.ServletActionContext;
public class ChangePasswordService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	java.sql.Statement st_change=null;
	
	public boolean changepassword(String oldpassword,String newpassword) throws SQLException
	{
		
		HttpSession session=ServletActionContext.getRequest().getSession();
		String currentuser=(String)session.getAttribute("user");
		boolean changeresult=false ;
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
		String sqlQuery="select Password from user where Username='"+currentuser+"'";
		sqlRst=st.executeQuery(sqlQuery);
		if(sqlRst.next())
			
		{
			String lastpassword;
			lastpassword= sqlRst.getString("Password");
			if(lastpassword.equals(oldpassword))
			{
				st_change=conn.createStatement();
				sqlQuery="update user set Password = '"+newpassword+"' where Username = '"+currentuser+"'";
				st_change.executeUpdate(sqlQuery);
				changeresult= true;
			}
				
			else
			{
				
				changeresult = false ;
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
		return changeresult;
	}
	
	
	
	
}
