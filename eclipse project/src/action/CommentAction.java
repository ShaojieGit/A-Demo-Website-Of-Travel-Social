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

import service.RegistService;
public class CommentAction extends ActionSupport {
	private String message,commentcontent,username,whoseaction,publishtime;
	//commentcontent_reply
	public HttpSession session=ServletActionContext.getRequest().getSession();
	
	private RegistService CurrentRegistService=new RegistService();
	public String execute() throws Exception {
		CommentService CurrentCommentService;
		CurrentCommentService=new CommentService();
		String currentuser;
	/*	if(commentcontent_reply!=null||commentcontent_reply!="")
			commentcontent=commentcontent_reply;*/
		System.out.println("CommentAction");
		System.out.println("评论内容："+commentcontent);
		System.out.println("评论人："+username);
		currentuser=(String)session.getAttribute("user");
		CurrentCommentService.comment(currentuser,commentcontent,whoseaction,publishtime,"");
		return SUCCESS;
	}




	// getter和setter方法声明


	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getWhoseaction() {
		return whoseaction;
	}

	public void setWhoseaction(String whoseaction) {
		this.whoseaction = whoseaction;
	}
	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}




/*	public String getCommentcontent_reply() {
		return commentcontent_reply;
	}




	public void setCommentcontent_reply(String commentcontent_reply) {
		this.commentcontent_reply = commentcontent_reply;
	}   */
}
