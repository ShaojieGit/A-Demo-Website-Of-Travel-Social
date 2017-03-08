package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.FocusService;
import service.LoginService;

import javax.servlet.http.*;

import service.CancelFansService;
public class CancelFansAction extends ActionSupport {

	private String username;
	HttpSession session=ServletActionContext.getRequest().getSession();
	private CancelFansService CurrentCancelFansService=new CancelFansService();
	public String execute() throws Exception {
		String currentusername;
		currentusername=(String)session.getAttribute("user");
		CurrentCancelFansService.cancelfans(currentusername,username);
		
		return SUCCESS;
	}
	// getter和setter方法声明
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

