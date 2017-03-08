package action;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import service.LoginService;
import service.ImageService;
import service.DeleteActionService;
import javax.servlet.http.*;
public class DeleteAction extends ActionSupport {
	private String message,actionuser,publishtime;
	
	public String execute() throws Exception {
		DeleteActionService CurrentDeleteActionService;
		CurrentDeleteActionService=new DeleteActionService();
		CurrentDeleteActionService.deleteaction(actionuser,publishtime);
		return SUCCESS;
			
	}

	// 检查字符串，不能为空
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// getter和setter方法声明
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getActionuser() {
		return actionuser;
	}

	public void setActionuser(String actionuser) {
		this.actionuser = actionuser;
	}
	
	public String getPublishtime() {
		return publishtime;
	}

	public void setPublishtime(String publishtime) {
		this.publishtime = publishtime;
	}
}
