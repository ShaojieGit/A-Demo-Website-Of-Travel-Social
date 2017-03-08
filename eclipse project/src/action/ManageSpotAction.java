package action;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.struts2.ServletActionContext;

import Bean.Action;
import Bean.Spot;
import service.ManageSpotService;

import com.opensymphony.xwork2.ActionSupport;

import javax.servlet.http.*;

public class ManageSpotAction extends ActionSupport {

	private String havespot,message;
	
	public int i,n;
	private Spot myspot;
	public Spot getMyspot() {
		return myspot;
	}
	public void setMyspot(Spot myspot) {
		this.myspot = myspot;
	}
	private List<String> spotimages = new ArrayList<String>();
	public String execute() throws SQLException 
	{
		havespot="yes";
		ManageSpotService CurrentManageSpotService=new ManageSpotService();
		HttpSession session=ServletActionContext.getRequest().getSession();
		String username=(String)session.getAttribute("user");
		//��һ���������ҵ�������Ϣ
		
		myspot=CurrentManageSpotService.findspot(username);
		if(myspot==null)
			havespot="no";

		//��managespot.jsp���ж���û���ҵ�������Ϣ,���û���ҵ������Ҫ�½���Ϣ
		ServletActionContext.getRequest().setAttribute("myspot", myspot);
		ServletActionContext.getRequest().setAttribute("havespot", havespot);
		if(session.getAttribute("message")!=null)	
		{
			setMessage((String)session.getAttribute("message"));
			session.removeAttribute("message");
		}
		return SUCCESS;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	
	
}
