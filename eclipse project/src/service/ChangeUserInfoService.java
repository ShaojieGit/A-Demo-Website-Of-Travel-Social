package service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.servlet.http.*;

import org.apache.struts2.ServletActionContext;
public class ChangeUserInfoService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	java.sql.Statement st_usernameexist=null;
	java.sql.ResultSet sqlRst_usernameexist=null;
	
	public String ChangeSpotInfo(String username,String headimagename,String Email,String Sex,String Usertype,String Spot) throws SQLException
	{
		String change_result;
		String sqlQuery;
		boolean changeresult;
		HttpSession session=ServletActionContext.getRequest().getSession();
		String currentuser=(String)session.getAttribute("user");
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
		System.out.println(username+"  "+currentuser);
		if(!username.equals(currentuser))
		{
			System.out.println("zzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzzz"+headimagename);
			st_usernameexist=conn.createStatement();
			sqlQuery="select * from user where Username ='"+username+"'";
			sqlRst_usernameexist=st_usernameexist.executeQuery(sqlQuery);
			if(sqlRst_usernameexist.next())
				change_result="usernameexist";
		}
		else
		{
			st=conn.createStatement();
			
			if(headimagename==null)
			{
				System.out.println("fffffffffffffffffffffffffffffffffffffffffffffffffffff"+headimagename);
				System.out.println("----------------------------没有传头像");
			     sqlQuery="update user set Username='"+username+"',Email='"+Email+"',Sex='"+Sex+"',Usertype='"+Usertype+"',Spot='"+Spot+"' where Username='"+currentuser+"'";

			}
			else
			{
				System.out.println("rrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrrr"+headimagename);
			     sqlQuery="update user set Username='"+username+"',Email='"+Email+"',Sex='"+Sex+"',Headimage='"+headimagename+"',Usertype='"+Usertype+"',Spot='"+Spot+"' where Username='"+currentuser+"'";

			}
			st.executeUpdate(sqlQuery);
		}
		change_result="success";

		try
		{
			st.close();
			st_usernameexist.close();
			sqlRst.close();
			sqlRst_usernameexist.close();
		conn.close();
		System.out.println("与Mysql数据库关闭成功!");
		}
		catch(Exception e)
		{
		System.out.println("数据库关闭时出错！");
		System.out.println(e.getMessage());
		}
		return change_result;
	}
	
	
	
	
}
