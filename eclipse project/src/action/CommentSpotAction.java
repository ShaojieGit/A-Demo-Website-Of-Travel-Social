package action;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import service.CommentService;
import org.apache.struts2.ServletActionContext;
import javax.servlet.http.*;
import com.opensymphony.xwork2.ActionSupport;

import service.CommentSpotService;


import service.RegistService;
public class CommentSpotAction extends ActionSupport {
	private String commentcontent,commentuser,spotname;

	public String execute() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		CommentSpotService CurrentCommentSpotService;
	    CurrentCommentSpotService=new CommentSpotService();
	/*		String currentuser;
		System.out.println("CommentAction");
		System.out.println("评论内容："+commentcontent);
		System.out.println("评论人："+username);
		currentuser=(String)session.getAttribute("user");*/
	    if(session.getAttribute("spotname")!=null)
			session.removeAttribute("spotname");
		session.setAttribute("spotname", spotname);
		
	    CurrentCommentSpotService.comment(commentuser,commentcontent,spotname);
		return SUCCESS;
	}

	// getter和setter方法声明
	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	public String getCommentuser() {
		return commentuser;
	}

	public void setCommentuser(String commentuser) {
		this.commentuser = commentuser;
	}

	public String getSpotname() {
		return spotname;
	}

	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}

}
