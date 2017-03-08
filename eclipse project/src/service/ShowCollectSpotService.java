package service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.SimpleSpot;

public class ShowCollectSpotService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	java.sql.Statement st_image=null;
	java.sql.ResultSet sqlRst_image=null;
	
	public List<SimpleSpot> getCollectSpot(String username) throws SQLException
	{
		List<SimpleSpot> CollectSpot = new ArrayList<SimpleSpot>();  //收藏的景点
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
		
		st=conn.createStatement();
		String sqlQuery="select * from collectspot where Username='"+username+"'";
		sqlRst=st.executeQuery(sqlQuery);
		while(sqlRst.next())
		{
			SimpleSpot CurrentSimpleSpot=new SimpleSpot(null,null);
			CurrentSimpleSpot.setSpotname(sqlRst.getString("Spotname"));
			//下面取出该景点的一个图片
			st_image=conn.createStatement();
			sqlQuery="select * from spotimage where Spotname='"+sqlRst.getString("Spotname")+"'";
			sqlRst_image=st_image.executeQuery(sqlQuery);
			if(sqlRst_image.next())
				CurrentSimpleSpot.setImage(sqlRst_image.getString("Imagename"));
			CollectSpot.add(CurrentSimpleSpot);
		}

		try
		{
			st.close();
			sqlRst.close();
		conn.close();
		System.out.println("与Mysql数据库关闭成功!");
		}
		catch(Exception e)
		{
		System.out.println("数据库关闭时出错！");
		System.out.println(e.getMessage());
		}
		return CollectSpot;
	}
	
	
	
	
}
