package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
	
public class CancelFansService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;

	public void cancelfans(String username,String fansname) throws SQLException
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
		String sqlQuery="delete from relation where Username = '"+fansname+"' and Friendname = '"+username+"'";
		st.executeUpdate(sqlQuery);

		try
		{
			st.close();
		conn.close();
		System.out.println("��Mysql���ݿ�رճɹ�!");
		}
		catch(Exception e)
		{
		System.out.println("���ݿ�ر�ʱ����");
		System.out.println(e.getMessage());
		}
	}
}
