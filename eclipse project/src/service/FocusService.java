package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
	
public class FocusService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;

	public void focus(String username,String friendname) throws SQLException
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
		String sqlQuery="insert into relation values ('"+username+"','"+friendname+"')";
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
