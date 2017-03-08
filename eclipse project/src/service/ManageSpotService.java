package service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import Bean.*;
public class ManageSpotService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	java.sql.Statement st_image=null;
	java.sql.ResultSet sqlRst_image=null;
	
	public Spot findspot(String username) throws SQLException
	{
		List<String> spotimages = new ArrayList<String>();
		Spot myspot=new Spot(null,null,null,null,null,null,null);
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
		String sqlQuery="select * from spot where Username='"+username+"'";
		sqlRst=st.executeQuery(sqlQuery);
		if(sqlRst.next())
		{	
			myspot.setLevel(sqlRst.getString("Level"));
			myspot.setPhonenumber(sqlRst.getString("Phonenumber"));
			myspot.setPosition(sqlRst.getString("Position"));
			myspot.setSpottext(sqlRst.getString("spottext"));
			myspot.setSpotname(sqlRst.getString("Spotname"));
			myspot.setUsername(sqlRst.getString("Username"));
			//下面查询该景点的图片
			
			st_image=conn.createStatement();
			sqlQuery="select * from spotimage where Spotname='"+sqlRst.getString("Spotname")+"'";
			sqlRst_image=st_image.executeQuery(sqlQuery);
			while(sqlRst_image.next())
			{
				String currentimage=new String();
				currentimage=sqlRst_image.getString("Imagename");
				spotimages.add(currentimage);
			}
			
			myspot.setImages(spotimages);
		}
			
		else
			myspot=null; 

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
		return myspot;
	}
	
	
	
	
}
