package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.ChangePasswordService;

import javax.servlet.http.*;

import service.ShareService;
public class ChangePasswordAction extends ActionSupport {
	private String message,oldpassword,newpassword;


	HttpSession session=ServletActionContext.getRequest().getSession();
	private ChangePasswordService CurrentChangePasswordService=new ChangePasswordService();
	public String execute() throws Exception {
		String username=null;
		username=(String)session.getAttribute("user");
		boolean changeresult;
		System.out.println("111111"+oldpassword+"   "+newpassword);
		System.out.println("¾ÉÃÜÂë"+oldpassword);
		System.out.println("ĞÂÃÜÂë"+newpassword);
		changeresult=CurrentChangePasswordService.changepassword(oldpassword,newpassword);
		if(changeresult==false)
		{
			System.out.println("¾ÉÃÜÂë"+oldpassword);
			System.out.println("ĞÂÃÜÂë"+newpassword);
			setMessage("Ô­ÃÜÂë´íÎó");
			return INPUT;
		}
		System.out.println("-----------");
		return SUCCESS;

			
	}
	public String getOldpassword() {
		return oldpassword;
	}
	public void setOldpassword(String oldpassword) {
		this.oldpassword = oldpassword;
	}
	public String getNewpassword() {
		return newpassword;
	}
	public void setNewpassword(String newpassword) {
		this.newpassword = newpassword;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}


	// getterºÍsetter·½·¨ÉùÃ÷

}
