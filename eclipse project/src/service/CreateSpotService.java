package service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class CreateSpotService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.Statement st_exist=null;
	java.sql.ResultSet sqlRst_exist=null;
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
	
	public boolean createspot(String spotname,String username,String position,String spottext,String spotimage,String phonenumber,String level) throws SQLException
	{
		String Usertype;
		addspotimage(spotimage,spotname);
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
		String sqlQuery;
		st_exist=conn.createStatement();
		sqlQuery="select * from spot where Spotname = '"+spotname+"'";
		sqlRst_exist=st_exist.executeQuery(sqlQuery);
		if(sqlRst_exist.next())
		{
			System.out.println("���������Ѿ�����");
			return false;
		}
		
		
		st=conn.createStatement();
		sqlQuery="insert into spot values('"+ spotname+"','"+username+"','"+position+"','"+level+"','"+phonenumber+"','"+spottext+"')";
		st.executeUpdate(sqlQuery);
		sqlQuery="update user set Spot='"+spotname+"' where Username='"+username+"'";
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
		return true;
	}
	
	
}
