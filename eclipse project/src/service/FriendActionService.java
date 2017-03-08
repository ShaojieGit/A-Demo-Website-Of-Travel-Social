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
	{    //�˷����ȿ��Ի�ú��Ѷ�̬�б�Ҳ���Ի��ĳһ���ض��û��Ķ�̬�б�
		System.out.println("FriendActionService");
		int i,n;
		List<String> friends = new ArrayList<String>();
		List<Action> friendactions = new ArrayList<Action>();
		
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
		st=conn.createStatement();
		st_user=conn.createStatement();
		//�����õ�ǰ��¼���ѵĹ�ע�б�
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
		
		
		friends.add(username);  //�Լ��Ķ�̬ҲҪ��ʾ
	//	System.out.println("��������б�+�Լ�");
		n=friends.size();
	//	for(i=0;i<n;i++)
	//		System.out.println("friend"+i+":"+friends.get(i));
		String currentFriend=null;
		Action currentaction;
		int tt=0;
		int k;
		for(i=0;i<n;i++)   //��ѯÿ�����ѵĶ�̬������list
		{
			currentFriend=friends.get(i);
			System.out.println("==========================��һ������:"+currentFriend);
	//		System.out.println("currentFriend"+i+":"+currentFriend);
			sqlQuery="select * from action where Username='"+currentFriend+"'";
			sqlRst=st.executeQuery(sqlQuery);
			//�����û�ͷ��
			sqlQuery="select Headimage from user where Username='"+currentFriend+"'";
			sqlRst_user=st_user.executeQuery(sqlQuery);
			String headimage=null;
					
			if(sqlRst_user.next())
			{
		//		System.out.println("����ͷ��Ĳ�ѯ���");
				headimage=sqlRst_user.getString("Headimage");
			//	System.out.println(headimage);
			}
			while(sqlRst.next())
			{
				currentaction=new Action(null,null,null,null,null,null,null);
				
		/*		System.out.println("");
					System.out.println("����һ����̬��list");
					System.out.println(sqlRst.getString("ActionText"));
					System.out.println(sqlRst.getString("ActionImage"));
					System.out.println(sqlRst.getString("CreateTime"));*/
					
					currentaction.setActiontext(sqlRst.getString("ActionText"));
					currentaction.setActionimage(sqlRst.getString("ActionImage"));
					currentaction.setPublishtime(sqlRst.getString("CreateTime"));
					currentaction.setUsername(currentFriend);
					currentaction.setShared(sqlRst.getString("Shared"));
					currentaction.setHeadimagename(headimage);
					//!!!!��ѯ��ǰ��̬�������б�
					st_comment=conn.createStatement();
					                //------------- CreateTimeת��Ϊ  timestamp����
					String cutpublishtime=null;
					cutpublishtime=sqlRst.getString("CreateTime").substring(0, 19);
					//System.out.println(cutpublishtime);
					                //�������ַ���ת��Ϊtimestamp����
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
						//��ѯ�����ߵ�ͷ��
						st_commentuser=conn.createStatement();
						sqlQuery="select Headimage from user where Username='"+sqlRst_comment.getString("CommentUser")+"'";
						sqlRst_commentuser=st_commentuser.executeQuery(sqlQuery);
						if(sqlRst_commentuser.next())
							thiscomment.setHeadimagename(sqlRst_commentuser.getString("Headimage"));
						currentcomments.add(thiscomment);

						
					}
					//-----------------������������۰�ʱ������
					Comment tempcomment;
					
					int p,q,commentnum;
					commentnum=currentcomments.size();
					for(p=0;p<commentnum-1;p++)   //ð������
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
		//			System.out.println("-------------------����ʱ��������");
		//			for(p=0;p<commentnum-1;p++)
		//				System.out.println(currentcomments.get(p).getCommenttime());
		//			System.out.println("++++++++++++++++++++-����ʱ��������");
					//-----------------���۰�ʱ���������
		//			System.out.println("��ǰ��̬�����۸�����"+currentcomments.size());	
					currentaction.setComments(currentcomments);  //��ӵ�ǰ��̬������			
					
					System.out.println("----------------------��̬����"+currentaction.getActiontext());
					
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
	//	for(i=0;i<friendactionnum;i++)   //ð������---�Ѷ�̬�б�ʱ������
	//		System.out.println(friendactions.get(i).getPublishtime());
		
		for(i=0;i<friendactionnum-1;i++)   //ð������---�Ѷ�̬�б�ʱ������
		{
			for(j=friendactionnum-2;j>=i;j--)
			{
	/*			System.out.println("----------------------------------------");
				System.out.println("i="+i+" j="+j);
				for(k=0;k<friendactionnum;k++)
				{	System.out.println("����ǰ");
				System.out.println(k+" "+friendactions.get(k).getPublishtime());
				}//System.out.println("����ǰfriendactions.get(2).getPublishtime()="+friendactions.get(2).getPublishtime());
				System.out.println("��"+j+"����̬ʱ��ת�����֣�"+TimeToLong(friendactions.get(j).getPublishtime()));
				System.out.println("��"+(j+1)+"����̬ʱ��ת�����֣�"+TimeToLong(friendactions.get(j+1).getPublishtime()));*/
				if(TimeToLong(friendactions.get(j).getPublishtime())<
				   TimeToLong(friendactions.get(j+1).getPublishtime()))
				{	tempaction=friendactions.get(j);
				
					friendactions.set(j, friendactions.get(j+1));
					friendactions.set(j+1, tempaction);
				//	System.out.println("������friendactions.get(1).getPublishtime()="+friendactions.get(1).getPublishtime());
				//System.out.println("������friendactions.get(2).getPublishtime()="+friendactions.get(2).getPublishtime());
				}
		/*		for(k=0;k<friendactionnum;k++)
				{	System.out.println("������");
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
		System.out.println("��Mysql���ݿ�رճɹ�!");
		}
		catch(Exception e)
		{
		System.out.println("���ݿ�ر�ʱ����");
		System.out.println(e.getMessage());
		}
		return friendactions;
	}
	
	public long TimeToLong(String time)    //�Ѵ�CreateTime �ֶ�ȡ����ʱ��ת��Ϊһ�����ֱ�������
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
