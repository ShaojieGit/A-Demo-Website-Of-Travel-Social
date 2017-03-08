package action;



import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import service.CommentService;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.LoginService;

import javax.servlet.http.*;

import service.DeleteSpotCommentService;
public class DeleteSpotCommentAction extends ActionSupport {
	private String commentuser,commenttime,spotname;


	

	
	public String execute() throws Exception {
		DeleteSpotCommentService CurrentDeleteSpotCommentService;
		CurrentDeleteSpotCommentService=new DeleteSpotCommentService();
		HttpSession session=ServletActionContext.getRequest().getSession();
	/*	String currentuser;
		System.out.println("CommentAction");
		System.out.println("评论内容："+commentcontent);
		System.out.println("评论人："+username);
		currentuser=(String)session.getAttribute("user");
		CurrentCommentService.comment(currentuser,commentcontent,whoseaction,publishtime);*/
		System.out.println("要删除的评论人和时间："+commentuser+"   "+commenttime);
		if(session.getAttribute("spotname")!=null)
			session.removeAttribute("spotname");
		session.setAttribute("spotname", spotname);
		CurrentDeleteSpotCommentService.deleteSpotComment(commentuser,commenttime);
		
		return SUCCESS;
	}
	public String getCommentuser() {
		return commentuser;
	}
	public void setCommentuser(String commentuser) {
		this.commentuser = commentuser;
	}
	public String getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}

	public String getSpotname() {
		return spotname;
	}
	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}


	// getter和setter方法声明
	
}
