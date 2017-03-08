package action;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.LoginService;
import service.ImageService;
import service.CommonService;
import service.FriendActionService;

import javax.servlet.http.*;

import test.test_returnaction;

import java.util.ArrayList;
import java.util.List;

import Bean.Action;

	
	
public class ShowActionAction extends ActionSupport {
	private String message;
	public int i,n;
	private List<Action> friendaction = new ArrayList<Action>();
	public String execute() throws Exception {
		System.out.println("ShowActionAction");
		FriendActionService CurrentFriendActionService=new FriendActionService();
		String username;
		HttpSession session=ServletActionContext.getRequest().getSession();
		username=(String)session.getAttribute("user");
		friendaction=CurrentFriendActionService.getFriendAction(username,false);
		int i;
		for(i=0;i<friendaction.size();i++)
			System.out.println(friendaction.get(i).getComments().size());
		ServletActionContext.getRequest().setAttribute("friendaction", friendaction);
	//	System.out.println("ShowActionAction里输出：");
		n=friendaction.size();
	//	for(i=0;i<n;i++)
	//		System.out.println(friendaction.get(i).getUsername()+friendaction.get(i).getHeadimagename()+friendaction.get(i).getActiontext()+"--"+friendaction.get(i).getActionimage());
		return SUCCESS;
	}

	

	// getter和setter方法声明
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	public List<Action> getFriendaction() {
		return friendaction;
	}

	public void setFriendaction(List<Action> friendaction) {
		this.friendaction = friendaction;
	}

	
}
