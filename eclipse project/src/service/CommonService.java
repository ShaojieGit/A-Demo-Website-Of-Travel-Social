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
		System.out.println("��Mysql���ݿ����ӳɹ�!");
		}
		catch(Exception e)
		{
		System.out.println("�������ݿ����");
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
		System.out.println("��Mysql���ݿ�رճɹ�!");
		}
		catch(Exception e)
		{
		System.out.println("���ݿ�ر�ʱ����");
		System.out.println(e.getMessage());
		}
		return Usertype;
	}
	
	
	
	
}
