package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class LoginService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	public boolean isCorrect(String username,String password) throws SQLException
	{
		try
		{
		Class.forName("com.mysql.jdbc.Driver").newInstance();
		conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/web_database","root","770903321");
		System.out.println("��Mysql���ݿ����ӳɹ�!");
		}
		catch(Exception e)
		{
		System.out.println("�������ݿ����");
		System.out.println(e.getMessage());
		}
		
		st=conn.createStatement();
		String sqlQuery="select * from user where Username='"+username+"'and Password='"+password+"'";
		sqlRst=st.executeQuery(sqlQuery);
		int flag=0;
		if(sqlRst.next())
			flag=1;
		else
			flag=0;
		try
		{
		st.close();
		sqlRst.close();
		conn.close();
		System.out.println("��Mysql���ݿ�رճɹ�!");
		}
		catch(Exception e)
		{
		System.out.println("���ݿ�ر�ʱ����");
		System.out.println(e.getMessage());
		}
		if(flag==1)
			return true;
		else
			return false;
	}
	
}
