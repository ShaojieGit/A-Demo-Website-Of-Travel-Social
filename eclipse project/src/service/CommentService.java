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

public class CommentService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	public void comment(String currentuser,String commentcontent,String whoseaction,String publishtime,String repeat) throws SQLException, ParseException
	{
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
		System.out.println(publishtime);
		String cutpublishtime;
		cutpublishtime=publishtime.substring(0, 19);
		System.out.println(cutpublishtime);
		//将日期字符串转化为timestamp类型
		Format f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
		Date d = (Date) f.parseObject(cutpublishtime);
        Timestamp ts = new Timestamp(d.getTime());
        System.out.println(ts);
		//subpublishtime=getseconds(publishtime);
		st=conn.createStatement();
		System.out.println("--------------------------当前评论");
		System.out.println(commentcontent);
		String sqlQuery="insert into comments(CommentUser,CommentContent,WhoseAction,PublishTime,Reply) values('"+currentuser+"','"+commentcontent+"','"+whoseaction+"','"+ts+"','"+repeat+"')";
		System.out.println("<<<<<<<<<<<SQL<<<<<<<<<<<<<"+sqlQuery);
		System.out.println(sqlQuery);
		st.executeUpdate(sqlQuery);
		try
		{
			st.close();

		conn.close();
		System.out.println("与Mysql数据库关闭成功!");
		}
		catch(Exception e)
		{
		System.out.println("数据库关闭时出错！");
		System.out.println(e.getMessage());
		}
	}
	
/*	public int getseconds(String publishtime)   //从2014年1月1号起的秒数
	{2014-08-01 10:12:35.0
		int x;
		x=0;
		x+=((publishtime.charAt(2)-48)*10+publishtime.charAt(3))*365*31536000;
		x+=((publishtime.charAt(5)-48)*10+publishtime.charAt(6))
	}*/
}
