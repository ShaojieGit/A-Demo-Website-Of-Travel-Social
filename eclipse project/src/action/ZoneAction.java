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
	private List<Action> myactions = new ArrayList<Action>();   //动态
	private List<User> focus = new ArrayList<User>();  //关注
	private List<User> fans = new ArrayList<User>();   //粉丝
	private User thisuser = new User(null, null, null, null);   //所操作空间的用户对应的对象目的是获得当前在线用户和该用户的关系
	private List<SimpleSpot> CollectSpot = new ArrayList<SimpleSpot>();  //收藏的景点
	private UserDetail ThisUserDetail = new UserDetail(null, null, null, null, null, null);   //个人资料
	HttpSession session=ServletActionContext.getRequest().getSession();
	public String execute() throws Exception {
		if(username!=null&&username!="")
		{
			session.removeAttribute("username_zone");
			session.setAttribute("username_zone",username);
		}
		else
			setUsername((String)session.getAttribute("username_zone"));
		/*if(session.getAttribute("username_zone")!=null)   //关注操作前在哪个用户的空间里
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
	/*	System.out.println("关注个数为："+focus.size()+"分别是:");
		for(i=0;i<focus.size();i++)
			System.out.println((String)focus.get(i).getUsername());
		System.out.println("关粉丝个数为："+fans.size()+"分别是:");
		for(i=0;i<fans.size();i++)
			System.out.println((String)fans.get(i).getUsername());*/
			
		System.out.println("当前用户对当前空间主人是否关注？"+thisuser.getFocused());
		ServletActionContext.getRequest().setAttribute("myactions", myactions);
		ServletActionContext.getRequest().setAttribute("focus", focus);
		ServletActionContext.getRequest().setAttribute("fans", fans);
		ServletActionContext.getRequest().setAttribute("CollectSpot", CollectSpot);
		ServletActionContext.getRequest().setAttribute("ThisUserDetail", ThisUserDetail);   
		ServletActionContext.getRequest().setAttribute("thisuser", thisuser); 
		return SUCCESS;
	}

	

	// getter和setter方法声明
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
