package action;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import service.LoginService;
import javax.servlet.http.*;
public class QuitAction extends ActionSupport {
	
	HttpSession session=ServletActionContext.getRequest().getSession();
	public String execute(){
			if(session.getAttribute("user")!=null)
				session.removeAttribute("user");
			return SUCCESS;
	}
}