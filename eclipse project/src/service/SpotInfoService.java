package service;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


import Bean.SpotDetail;
import javax.servlet.http.*;
import java.util.List;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;
import Bean.CommentSpot;

public class SpotInfoService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.ResultSet sqlRst=null;
	java.sql.Statement st_image=null;
	java.sql.ResultSet sqlRst_image=null;
	java.sql.Statement st_comment=null;
	java.sql.ResultSet sqlRst_comment=null;
	java.sql.Statement st_headimage=null;
	java.sql.ResultSet sqlRst_headimage=null;
	java.sql.Statement st_collected=null;
	java.sql.ResultSet sqlRst_collected=null;
	
	
	public SpotDetail getSpotinfo(String spotname) throws SQLException
	{
		List<String> images=new ArrayList<String>();
		String currentuser=null;  //当前操作用户
		List<CommentSpot> comments=new ArrayList<CommentSpot>();
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(session.getAttribute("user")!=null)
			currentuser=(String)session.getAttribute("user");

		SpotDetail thisSpotDetail=new SpotDetail(null,null,null,null,null,null,null,null,null);
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
		String sqlQuery="select * from spot where Spotname='"+spotname+"'";
		sqlRst=st.executeQuery(sqlQuery);
		if(sqlRst.next())
		{
			thisSpotDetail.setLevel(sqlRst.getString("Level"));
			thisSpotDetail.setPhonenumber(sqlRst.getString("Phonenumber"));
			thisSpotDetail.setPosition(sqlRst.getString("Position"));
			thisSpotDetail.setSpotname(spotname);
			thisSpotDetail.setSpottext(sqlRst.getString("spottext"));
			thisSpotDetail.setUsername(sqlRst.getString("Username"));
			//下面查找图片
			st_image=conn.createStatement();
			sqlQuery="select * from spotimage where Spotname='"+spotname+"'";
			sqlRst_image=st_image.executeQuery(sqlQuery);
			while(sqlRst_image.next())
			{
				String thisImage=sqlRst_image.getString("Imagename");
				images.add(thisImage);
			}
			thisSpotDetail.setImages(images);
			//下面找是否收藏
			st_collected=conn.createStatement();
			sqlQuery="select * from collectspot where Username='"+currentuser +"'and Spotname='"+spotname+"'";
			sqlRst_collected=st_collected.executeQuery(sqlQuery);
			if(sqlRst_collected.next())
				thisSpotDetail.setCollected("yes");
			else
				thisSpotDetail.setCollected(null);
			//下面查找评论
			st_comment=conn.createStatement();
			sqlQuery="select * from commentspot where Spotname='"+spotname+"'";
			sqlRst_comment=st_comment.executeQuery(sqlQuery);
			while(sqlRst_comment.next())
			{
				CommentSpot nextCommentSpot=new CommentSpot(null,null,null,null,null);
				nextCommentSpot.setCommentuser(sqlRst_comment.getString("CommentUser"));
				nextCommentSpot.setSpotname(spotname);
				nextCommentSpot.setCommenttime(sqlRst_comment.getString("CommentTime"));
				nextCommentSpot.setCommentcontent(sqlRst_comment.getString("CommentContent"));
				//查找评论人头像
				st_headimage=conn.createStatement();
				sqlQuery="select Headimage from user where Username='"+sqlRst_comment.getString("CommentUser")+"'";
				sqlRst_headimage=st_headimage.executeQuery(sqlQuery);
				if(sqlRst_headimage.next())
					nextCommentSpot.setHeadimagename(sqlRst_headimage.getString("Headimage"));
				comments.add(nextCommentSpot);
			}
			//下面对评论按时间排序
			CommentSpot tempcomment;
			
			int p,q,commentnum;
			commentnum=comments.size();
			for(p=0;p<commentnum-1;p++)   //冒泡排序
			{
				for(q=commentnum-2;q>=p;q--)
				{				
					if(TimeToLong(comments.get(q).getCommenttime())<
					   TimeToLong(comments.get(q+1).getCommenttime()))
					{							
						tempcomment=new CommentSpot(null,null,null,null,null);
						tempcomment=comments.get(q);
						
						comments.set(q, comments.get(q+1));
						comments.set(q+1, tempcomment);
					}
				}
			}
			
			thisSpotDetail.setComments(comments);
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
		return thisSpotDetail;
	}
	
	public long TimeToLong(String time)    //把从CreateTime 字段取到的时间转换为一个数字便于排序
	{
	//	System.out.println(time+"---");
		long LongOfTime=0;
		LongOfTime+=(time.charAt(20)-48);
		
		LongOfTime+=(time.charAt(18)-48)*10;
		LongOfTime+=(time.charAt(17)-48)*100;
		
		LongOfTime+=(time.charAt(15)-48)*1000;
		LongOfTime+=(time.charAt(14)-48)*10000;
		
		LongOfTime+=(time.charAt(12)-48)*100000;
		LongOfTime+=(time.charAt(11)-48)*1000000;
		
		LongOfTime+=(time.charAt(9)-48)*10000000;
		LongOfTime+=(time.charAt(8)-48)*100000000;
		
		LongOfTime+=(time.charAt(6)-48)*1000000000;
		LongOfTime+=(time.charAt(5)-48)*10000000000L;
		
		LongOfTime+=(time.charAt(3)-48)*100000000000L;
		LongOfTime+=(time.charAt(2)-48)*1000000000000L;
		LongOfTime+=(time.charAt(1)-48)*10000000000000L;
		LongOfTime+=(time.charAt(0)-48)*100000000000000L;
		return LongOfTime;
		
	}
	
	
}
