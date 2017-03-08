package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import service.CommentService;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.CollectSpotService;

import javax.servlet.http.*;

import service.RegistService;
public class CollectSpotAction extends ActionSupport {
	private String spotname,username;

	public HttpSession session=ServletActionContext.getRequest().getSession();
	
	private CollectSpotService CurrentCollectSpotService=new CollectSpotService();
	public String execute() throws Exception {
		System.out.println("Ҫ�ղص��û��;��㣺"+username+spotname);
		
		if(session.getAttribute("spotname")!=null)
			session.removeAttribute("spotname");
		session.setAttribute("spotname", spotname);
		CurrentCollectSpotService.collectspot(username,spotname);
	/*	CommentService CurrentCommentService;
		CurrentCommentService=new CommentService();
		String currentuser;
		System.out.println("CommentAction");
		System.out.println("�������ݣ�"+commentcontent);
		System.out.println("�����ˣ�"+username);
		currentuser=(String)session.getAttribute("user");
		CurrentCommentService.comment(currentuser,commentcontent,whoseaction,publishtime);*/
		return SUCCESS;
	}




	// getter��setter��������

	public String getSpotname() {
		return spotname;
	}

	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
}
