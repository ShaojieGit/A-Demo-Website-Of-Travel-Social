package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.SpotUserService;

import javax.servlet.http.*;

import service.ShareService;
public class SpotUserAction extends ActionSupport {

	HttpSession session=ServletActionContext.getRequest().getSession();
	private SpotUserService CurrentSpotUserService=new SpotUserService();
	public String execute() throws Exception {
		String username=null;
		username=(String)session.getAttribute("user");
		CurrentSpotUserService.becamespotuser(username);
		return SUCCESS;
	}
}
