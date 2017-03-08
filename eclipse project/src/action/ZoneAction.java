package action;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.*;

import javax.servlet.http.*;

import org.apache.struts2.ServletActionContext;

import test.test_returnaction;

import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.List;

import Bean.*;
	
	
public class ZoneAction extends ActionSupport {
	private String message,username;
	public int i,n;
	private List<Action> myactions = new ArrayList<Action>();   //��̬
	private List<User> focus = new ArrayList<User>();  //��ע
	private List<User> fans = new ArrayList<User>();   //��˿
	private User thisuser = new User(null, null, null, null);   //�������ռ���û���Ӧ�Ķ���Ŀ���ǻ�õ�ǰ�����û��͸��û��Ĺ�ϵ
	private List<SimpleSpot> CollectSpot = new ArrayList<SimpleSpot>();  //�ղصľ���
	private UserDetail ThisUserDetail = new UserDetail(null, null, null, null, null, null);   //��������
	HttpSession session=ServletActionContext.getRequest().getSession();
	public String execute() throws Exception {
		if(username!=null&&username!="")
		{
			session.removeAttribute("username_zone");
			session.setAttribute("username_zone",username);
		}
		else
			setUsername((String)session.getAttribute("username_zone"));
		/*if(session.getAttribute("username_zone")!=null)   //��ע����ǰ���ĸ��û��Ŀռ���
			setUsername((String)session.getAttribute("username_zone"));
		else
			session.setAttribute("username_zone",username);*/
		//   session.removeAttribute("username_zone");
		FriendActionService CurrentFriendActionService=new FriendActionService();
		FocusFansService CurrentFocusFansService=new FocusFansService();
		ShowCollectSpotService CurrentShowCollectSpotService=new ShowCollectSpotService();
		GetUserDetailService CurrentGetUserDetailService= new GetUserDetailService();
		myactions=CurrentFriendActionService.getFriendAction(username,true);
		focus=CurrentFocusFansService.getUserinfo(username,"focus");
		fans=CurrentFocusFansService.getUserinfo(username,"fans");
		thisuser=CurrentFocusFansService.getThisUserinfo(username);
		CollectSpot=CurrentShowCollectSpotService.getCollectSpot(username);
		ThisUserDetail=CurrentGetUserDetailService.getUserService(username);
	/*	System.out.println("��ע����Ϊ��"+focus.size()+"�ֱ���:");
		for(i=0;i<focus.size();i++)
			System.out.println((String)focus.get(i).getUsername());
		System.out.println("�ط�˿����Ϊ��"+fans.size()+"�ֱ���:");
		for(i=0;i<fans.size();i++)
			System.out.println((String)fans.get(i).getUsername());*/
			
		System.out.println("��ǰ�û��Ե�ǰ�ռ������Ƿ��ע��"+thisuser.getFocused());
		ServletActionContext.getRequest().setAttribute("myactions", myactions);
		ServletActionContext.getRequest().setAttribute("focus", focus);
		ServletActionContext.getRequest().setAttribute("fans", fans);
		ServletActionContext.getRequest().setAttribute("CollectSpot", CollectSpot);
		ServletActionContext.getRequest().setAttribute("ThisUserDetail", ThisUserDetail);   
		ServletActionContext.getRequest().setAttribute("thisuser", thisuser); 
		return SUCCESS;
	}

	

	// getter��setter��������
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Action> getMyactions() {
		return myactions;
	}

	public void setMyactions(List<Action> myactions) {
		this.myactions = myactions;
	}

	
}
