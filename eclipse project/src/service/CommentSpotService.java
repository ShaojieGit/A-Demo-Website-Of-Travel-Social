package service;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp; 
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class CommentSpotService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	public void comment(String commentuser,String commentcontent,String spotname) throws SQLException
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
	//	System.out.println("--------------------------��ǰ����");
	//	System.out.println(commentcontent);
		String sqlQuery="insert into commentspot(CommentUser,CommentContent,Spotname) values('"+commentuser+"','"+commentcontent+"','"+spotname+"')";

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
	
/*	public int getseconds(String publishtime)   //��2014��1��1���������
	{2014-08-01 10:12:35.0
		int x;
		x=0;
		x+=((publishtime.charAt(2)-48)*10+publishtime.charAt(3))*365*31536000;
		x+=((publishtime.charAt(5)-48)*10+publishtime.charAt(6))
	}*/
}
