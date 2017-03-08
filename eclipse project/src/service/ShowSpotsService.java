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
import java.util.ArrayList;
import java.util.List;

import Bean.SimpleSpot;
public class ShowSpotsService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	java.sql.Statement st_image=null;
	java.sql.ResultSet sqlRst_image=null;
	public List<SimpleSpot> getSpotList() throws SQLException
	{
		List<SimpleSpot> allspots = new ArrayList<SimpleSpot>();
		SimpleSpot CurrentSimpleSpot;
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
		String sqlQuery="select * from spot";
		sqlRst=st.executeQuery(sqlQuery);
		while(sqlRst.next())
		{
			CurrentSimpleSpot=new SimpleSpot(null,null);
			CurrentSimpleSpot.setSpotname(sqlRst.getString("Spotname"));
			st_image=conn.createStatement();
			sqlQuery="select Imagename from spotimage where Spotname='"+sqlRst.getString("Spotname")+"'";
			sqlRst_image=st_image.executeQuery(sqlQuery);
			if(sqlRst_image.next())
				CurrentSimpleSpot.setImage(sqlRst_image.getString("Imagename"));
			allspots.add(CurrentSimpleSpot);
		}
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
		
		return allspots;
	}

}
