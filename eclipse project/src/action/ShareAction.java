package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.LoginService;

import javax.servlet.http.*;

import service.ShareService;
public class ShareAction extends ActionSupport {
	private String actiontext,actionimage,actionuser;


	HttpSession session=ServletActionContext.getRequest().getSession();
	private ShareService CurrentShareService=new ShareService();
	public String execute() throws Exception {
		String username=null;
		username=(String)session.getAttribute("user");
		CurrentShareService.share(actiontext,actionimage,username,actionuser);
		return SUCCESS;

			
	}


	// getter和setter方法声明
	public String getActiontext() {
		return actiontext;
	}

	public void setActiontext(String actiontext) {
		this.actiontext = actiontext;
	}
	public String getActionimage() {
		return actionimage;
	}

	public void setActionuser(String actionuser) {
		this.actionuser = actionuser;
	}
	public String getActionuser() {
		return actionuser;
	}

	public void setActionimage(String actionimage) {
		this.actionimage = actionimage;
	}

}
