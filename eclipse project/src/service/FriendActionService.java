package service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import Bean.Action;
import Bean.Comment;
import javax.servlet.http.*;

import org.apache.struts2.ServletActionContext;

import java.sql.Timestamp; 
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date; 

public class FriendActionService {
	java.sql.Connection conn=null;
	java.sql.Statement st=null;
	java.sql.Statement st_user=null;
	java.sql.Statement st_comment=null;
	java.sql.Statement st_commentuser=null;
	java.sql.ResultSet sqlRst=null;
	java.sql.ResultSet sqlRst_user=null;
	java.sql.ResultSet sqlRst_comment=null;
	java.sql.ResultSet sqlRst_commentuser=null;
	
	
	public List<Action> getFriendAction(String username,boolean IsSingle) throws SQLException, ParseException
	{    //此方法既可以获得好友动态列表，也可以获得某一个特定用户的动态列表
		System.out.println("FriendActionService");
		int i,n;
		List<String> friends = new ArrayList<String>();
		List<Action> friendactions = new ArrayList<Action>();
		
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
		String sqlQuery;
		st=conn.createStatement();
		st_user=conn.createStatement();
		//下面获得当前登录好友的关注列表
		if(!IsSingle)
		{
			sqlQuery="select Friendname from relation where Username='"+username+"'";
			sqlRst=st.executeQuery(sqlQuery);
			String singlefriend;
			while(sqlRst.next())
			{
				singlefriend=sqlRst.getString("Friendname");
				friends.add(singlefriend);
			}
		}
		
		
		friends.add(username);  //自己的动态也要显示
	//	System.out.println("输出朋友列表+自己");
		n=friends.size();
	//	for(i=0;i<n;i++)
	//		System.out.println("friend"+i+":"+friends.get(i));
		String currentFriend=null;
		Action currentaction;
		int tt=0;
		int k;
		for(i=0;i<n;i++)   //查询每个好友的动态并插入list
		{
			currentFriend=friends.get(i);
			System.out.println("==========================下一个好友:"+currentFriend);
	//		System.out.println("currentFriend"+i+":"+currentFriend);
			sqlQuery="select * from action where Username='"+currentFriend+"'";
			sqlRst=st.executeQuery(sqlQuery);
			//查找用户头像
			sqlQuery="select Headimage from user where Username='"+currentFriend+"'";
			sqlRst_user=st_user.executeQuery(sqlQuery);
			String headimage=null;
					
			if(sqlRst_user.next())
			{
		//		System.out.println("这是头像的查询结果");
				headimage=sqlRst_user.getString("Headimage");
			//	System.out.println(headimage);
			}
			while(sqlRst.next())
			{
				currentaction=new Action(null,null,null,null,null,null,null);
				
		/*		System.out.println("");
					System.out.println("插入一个动态到list");
					System.out.println(sqlRst.getString("ActionText"));
					System.out.println(sqlRst.getString("ActionImage"));
					System.out.println(sqlRst.getString("CreateTime"));*/
					
					currentaction.setActiontext(sqlRst.getString("ActionText"));
					currentaction.setActionimage(sqlRst.getString("ActionImage"));
					currentaction.setPublishtime(sqlRst.getString("CreateTime"));
					currentaction.setUsername(currentFriend);
					currentaction.setShared(sqlRst.getString("Shared"));
					currentaction.setHeadimagename(headimage);
					//!!!!查询当前动态的评论列表
					st_comment=conn.createStatement();
					                //------------- CreateTime转换为  timestamp类型
					String cutpublishtime=null;
					cutpublishtime=sqlRst.getString("CreateTime").substring(0, 19);
					//System.out.println(cutpublishtime);
					                //将日期字符串转化为timestamp类型
					Format f = new SimpleDateFormat("yyyy-MM-dd' 'HH:mm:ss");
					Date d = (Date) f.parseObject(cutpublishtime);
			        Timestamp ts = new Timestamp(d.getTime());
			     //   System.out.println(ts);
					                 //-------------
			//		sqlQuery="select CommentUser,CommentTime,CommentContent,Repeat from comments where WhoseAction='"+currentFriend+"' and PublishTime='"+cutpublishtime+"'";
					sqlQuery="select * from comments where WhoseAction='"+currentFriend+"' and PublishTime='"+cutpublishtime+"'";

				//	System.out.println("SQL--------->"+sqlQuery);
					sqlRst_comment=st_comment.executeQuery(sqlQuery);
					Comment thiscomment;
					
					List<Comment> currentcomments = new ArrayList<Comment>();
					List<Comment> currentcomments2;
					while(sqlRst_comment.next())
					{
						thiscomment=new Comment("","","","","");
						System.out.println(sqlRst_comment.getString("CommentTime")+sqlRst_comment.getString("CommentContent"));						
						thiscomment.setUsername(sqlRst_comment.getString("CommentUser"));
						thiscomment.setCommenttime(sqlRst_comment.getString("CommentTime"));
						thiscomment.setCommentcontent(sqlRst_comment.getString("CommentContent"));
						thiscomment.setRepeat(sqlRst_comment.getString("Reply"));
						//查询评论者的头像
						st_commentuser=conn.createStatement();
						sqlQuery="select Headimage from user where Username='"+sqlRst_comment.getString("CommentUser")+"'";
						sqlRst_commentuser=st_commentuser.executeQuery(sqlQuery);
						if(sqlRst_commentuser.next())
							thiscomment.setHeadimagename(sqlRst_commentuser.getString("Headimage"));
						currentcomments.add(thiscomment);

						
					}
					//-----------------下面对所有评论按时间排序
					Comment tempcomment;
					
					int p,q,commentnum;
					commentnum=currentcomments.size();
					for(p=0;p<commentnum-1;p++)   //冒泡排序
					{
						for(q=commentnum-2;q>=p;q--)
						{				
							if(TimeToLong(currentcomments.get(q).getCommenttime())<
							   TimeToLong(currentcomments.get(q+1).getCommenttime()))
							{							
								tempcomment=new Comment("","","","","");
								tempcomment=currentcomments.get(q);
								
								currentcomments.set(q, currentcomments.get(q+1));
								currentcomments.set(q+1, tempcomment);
							}
						}
					}
		//			System.out.println("-------------------评论时间排序结果");
		//			for(p=0;p<commentnum-1;p++)
		//				System.out.println(currentcomments.get(p).getCommenttime());
		//			System.out.println("++++++++++++++++++++-评论时间排序结果");
					//-----------------评论按时间排序完成
		//			System.out.println("当前动态的评论个数："+currentcomments.size());	
					currentaction.setComments(currentcomments);  //添加当前动态的评论			
					
					System.out.println("----------------------动态内容"+currentaction.getActiontext());
					
					friendactions.add(currentaction);
			/*		List<Action> friendactions2 = new ArrayList<Action>();
					friendactions2=friendactions;
					friendactions2.add(currentaction);
					friendactions=friendactions2;*/
				//	System.out.println("tt="+tt+"  "+friendactions.get(tt).getPublishtime());
				//	if(tt>0)System.out.println("tt-1="+(tt-1)+"  "+friendactions.get(tt-1).getPublishtime());
					tt++;
				
			}
			//sqlRst.close();
			
		}
		int friendactionnum=0;
		friendactionnum=friendactions.size();
		int j;
		Action tempaction;
		tempaction=new Action(null,null,null,null,null,null,null);
	//	System.out.println("friendactionnum="+friendactionnum);
	//	for(i=0;i<friendactionnum;i++)   //冒泡排序---把动态列表按时间排序
	//		System.out.println(friendactions.get(i).getPublishtime());
		
		for(i=0;i<friendactionnum-1;i++)   //冒泡排序---把动态列表按时间排序
		{
			for(j=friendactionnum-2;j>=i;j--)
			{
	/*			System.out.println("----------------------------------------");
				System.out.println("i="+i+" j="+j);
				for(k=0;k<friendactionnum;k++)
				{	System.out.println("交换前");
				System.out.println(k+" "+friendactions.get(k).getPublishtime());
				}//System.out.println("交换前friendactions.get(2).getPublishtime()="+friendactions.get(2).getPublishtime());
				System.out.println("第"+j+"个动态时间转换数字："+TimeToLong(friendactions.get(j).getPublishtime()));
				System.out.println("第"+(j+1)+"个动态时间转换数字："+TimeToLong(friendactions.get(j+1).getPublishtime()));*/
				if(TimeToLong(friendactions.get(j).getPublishtime())<
				   TimeToLong(friendactions.get(j+1).getPublishtime()))
				{	tempaction=friendactions.get(j);
				
					friendactions.set(j, friendactions.get(j+1));
					friendactions.set(j+1, tempaction);
				//	System.out.println("交换后friendactions.get(1).getPublishtime()="+friendactions.get(1).getPublishtime());
				//System.out.println("交换后friendactions.get(2).getPublishtime()="+friendactions.get(2).getPublishtime());
				}
		/*		for(k=0;k<friendactionnum;k++)
				{	System.out.println("交换后");
				System.out.println(k+" "+friendactions.get(k).getPublishtime());
				}
				System.out.println("----------------------------------------");*/
			}
		}
	//	for(i=0;i<friendactionnum;i++)
	//		System.out.println(friendactions.get(i).getActiontext()+"--"+friendactions.get(i).getPublishtime());
		
		
	/*			sqlQuery="select CreateTime from action where Username='xia'";
		
	    String xxx = null;
		sqlRst=st.executeQuery(sqlQuery);
		if(sqlRst.next())
			xxx=sqlRst.getString(1);
		int m=xxx.length();
		System.out.println(m);
		System.out.println(xxx);
		System.out.println(">"+xxx.charAt(0)+"<");
		System.out.println(">"+xxx.charAt(m-1)+"<");
	*/
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
		return friendactions;
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
