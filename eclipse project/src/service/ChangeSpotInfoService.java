package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ChangeSpotInfoService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	
	public void addspotimage(String imagename,String spotname) throws SQLException
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
		String sqlQuery="insert into spotimage values('"+ imagename+"','"+spotname+"')";
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
	
	public void ChangeSpotInfo(String spottext,String phonenumber,String level,String newspotimage,String spotname)throws SQLException
	{
		//ͼƬ�������ݿ�
		if(newspotimage!=null&&newspotimage!="")
			addspotimage(newspotimage,spotname);
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
		String sqlQuery="update spot set Level='"+level+"',Phonenumber='"+phonenumber+"',spottext='"+spottext+"' where Spotname='"+spotname+"'";
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
