package service;

import java.sql.DriverManager;
import java.sql.SQLException;

public class DeleteActionService {
	public void deleteaction(String actionuser,String publishtime) throws SQLException
	{
		java.sql.Connection conn=null;
		java.sql.Statement st=null;
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
		String cutpublishtime;
		cutpublishtime=publishtime.substring(0, 19);
		String sqlQuery="delete from action where Username='"+actionuser+"' and CreateTime='"+cutpublishtime+"'";
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
