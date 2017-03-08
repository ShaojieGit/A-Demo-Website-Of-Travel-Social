package service;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.apache.struts2.ServletActionContext;

import Bean.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
public class FocusFansService {
     public List<User> getUserinfo(String username,String FocusOrFans) throws SQLException
     {
    	 
    	 List<User> userlist = new ArrayList<User>();
    	 User currentuser;
    	 List<String> namelist = new ArrayList<String>();
    	 String currentusername=null;
    	 String loginusername=null;
    	 String currentuserheadimage=null;
    	 HttpSession session=ServletActionContext.getRequest().getSession();
    	 loginusername=(String)session.getAttribute("user");
    	 
    	 java.sql.Connection conn=null;
    	 java.sql.Statement st=null;
    	 java.sql.ResultSet sqlRst=null;
    	 java.sql.Statement st_headimage=null;
    	 java.sql.ResultSet sqlRst_headimage=null;
    	 java.sql.Statement st_focus=null;
    	 java.sql.ResultSet sqlRst_focus=null;
    	 java.sql.Statement st_focusme=null;
    	 java.sql.ResultSet sqlRst_focusme=null;
    	 
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
    	 String focused;
    	 String focusme;
    	 if(FocusOrFans=="focus")   //��ѯ��ע�����б�
    	 {
    		 //��ȡ��ע���û����б�
    		 st=conn.createStatement();
    	 	 sqlQuery="select Friendname from relation where Username='"+username+"'";
    	 	 sqlRst=st.executeQuery(sqlQuery);
    	 	//��ȡÿ���û���ͷ���ж� ��ǰ��������û�й�ע���û�
    	 	 while(sqlRst.next())   
    	 	 {
    	 		currentusername=sqlRst.getString("Friendname");
    	 		//���ͷ��
    	 		st_headimage=conn.createStatement();
       	 	 	sqlQuery="select Headimage from user where Username='"+currentusername+"'";
       	 	 	sqlRst_headimage=st_headimage.executeQuery(sqlQuery);
       	 	 	if(sqlRst_headimage.next())
       	 	 		currentuserheadimage=sqlRst_headimage.getString("Headimage");
       	 	 	//�Ƿ��Ѿ�����ǰ�û���¼
		   	 	st_focus=conn.createStatement();
		 	 	sqlQuery="select * from relation where Username='"+loginusername+"' and Friendname='"+currentusername+"'";
		 	 	sqlRst_focus=st_focus.executeQuery(sqlQuery);
		 	 	if(sqlRst_focus.next())
		 	 		focused="yes";
		 	 	else
		 	 		focused="no";
		 	 	
		 	 	//������Ҹ��û��ǲ����Լ��ķ�˿
		 	 	st_focusme=conn.createStatement();
		 	 	sqlQuery="select * from relation where Username='"+currentusername+"' and Friendname='"+loginusername+"'";
		 	 	sqlRst_focusme=st_focusme.executeQuery(sqlQuery);
		 	 	if(sqlRst_focusme.next())
		 	 		focusme="yes";
		 	 	else
		 	 		focusme="no";
		 	 	currentuser=new User(currentusername,currentuserheadimage,focused,focusme);
		 	 	userlist.add(currentuser);
    	 	 }
    	 }
    	 else    //��ѯ��˿�б�
    	 {
    		 st=conn.createStatement();
    	 	 sqlQuery="select Username from relation where Friendname='"+username+"'";
    	 	 sqlRst=st.executeQuery(sqlQuery);
    	 	//��ȡÿ���û���ͷ���ж� ��ǰ��������û�й�ע���û�
    	 	 while(sqlRst.next())   
    	 	 {
    	 		currentusername=sqlRst.getString("Username");
    	 		//���ͷ��
    	 		st_headimage=conn.createStatement();
       	 	 	sqlQuery="select Headimage from user where Username='"+currentusername+"'";
       	 	 	sqlRst_headimage=st_headimage.executeQuery(sqlQuery);
       	 	 	if(sqlRst_headimage.next())
       	 	 		currentuserheadimage=sqlRst_headimage.getString("Headimage");
       	 	 	//�Ƿ��Ѿ�����ǰ�û���¼
		   	 	st_focus=conn.createStatement();
		 	 	sqlQuery="select * from relation where Username='"+loginusername+"' and Friendname='"+currentusername+"'";
		 	 	sqlRst_focus=st_focus.executeQuery(sqlQuery);
		 	 	if(sqlRst_focus.next())
		 	 		focused="yes";
		 	 	else
		 	 		focused="no";
		 	 	
		 	 //������Ҹ��û��ǲ����Լ��ķ�˿
		 	 	st_focusme=conn.createStatement();
		 	 	sqlQuery="select * from relation where Username='"+currentusername+"' and Friendname='"+loginusername+"'";
		 	 	sqlRst_focusme=st_focusme.executeQuery(sqlQuery);
		 	 	if(sqlRst_focusme.next())
		 	 		focusme="yes";
		 	 	else
		 	 		focusme="no";
		 	 	currentuser=new User(currentusername,currentuserheadimage,focused,focusme);
		 	 	userlist.add(currentuser);
    	 	 }
    	 }
    	 
 		
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
    	return userlist;
     }
     public User getThisUserinfo(String username) throws SQLException
     {
    	 java.sql.Connection conn=null;
    	 java.sql.Statement st_focus=null;
    	 java.sql.ResultSet sqlRst_focus=null;  	 
    	 
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
    	 
    	 String loginusername=null;
    	 HttpSession session=ServletActionContext.getRequest().getSession();
    	 loginusername=(String)session.getAttribute("user");
    	 String focus;
    	 User thisUser=new User(null, null, null, null);
    	 
    	//��ȡ��ע���û����б�
    	 st_focus=conn.createStatement();
    	  String sqlQuery="select * from relation where Username='"+loginusername+"' and Friendname = '"+username+"'";
    	  sqlRst_focus=st_focus.executeQuery(sqlQuery);
	 	if(sqlRst_focus.next())
	 		focus="yes";
	 	else
	 		focus="no";
	 	thisUser.setFocused(focus);
	 	thisUser.setUsername(username);
    	 return thisUser;
     }
}
