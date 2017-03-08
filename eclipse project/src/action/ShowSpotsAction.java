package action;


import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.LoginService;
import service.ImageService;
import service.ShowSpotsService;
import service.FriendActionService;

import javax.servlet.http.*;

import test.test_returnaction;

import java.util.ArrayList;
import java.util.List;

import Bean.SimpleSpot;

	
	
public class ShowSpotsAction extends ActionSupport {
	private String message;
	public int i,n;
	

	private List<SimpleSpot> allspots = new ArrayList<SimpleSpot>();
	public String execute() throws Exception {
		ShowSpotsService CurrentShowSpotsService=new ShowSpotsService();
		String username;
		allspots=CurrentShowSpotsService.getSpotList();
		int i;
		for(i=0;i<allspots.size();i++)
			System.out.println(allspots.get(i).getSpotname());
		ServletActionContext.getRequest().setAttribute("allspots", allspots);
	//	System.out.println("ShowActionAction里输出：");
		n=allspots.size();
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
	public List<SimpleSpot> getAllspots() {
		return allspots;
	}

	public void setAllspots(List<SimpleSpot> allspots) {
		this.allspots = allspots;
	}

	
}
