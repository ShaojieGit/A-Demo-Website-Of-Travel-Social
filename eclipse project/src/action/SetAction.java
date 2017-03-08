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

import Bean.UserDetail;
import service.ShareService;
public class SetAction extends ActionSupport {
	private String username,headimagename,Email,Sex,Usertype,Spot,message; 


	//HttpSession session=ServletActionContext.getRequest().getSession();
	private ShareService CurrentShareService=new ShareService();
	public String execute() throws Exception {

		UserDetail UserDetailToSet=new UserDetail(null, null,  null, null,  null,  null);
		UserDetailToSet.setEmail(Email);
		UserDetailToSet.setHeadimagename(headimagename);
		UserDetailToSet.setSex(Sex);
		UserDetailToSet.setSpot(Spot);
		UserDetailToSet.setUsername(username);
		UserDetailToSet.setUsertype(Usertype);
		ServletActionContext.getRequest().setAttribute("UserDetailToSet", UserDetailToSet);
		return SUCCESS;

			
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
	public String getHeadimagename() {
		return headimagename;
	}
	public void setHeadimagename(String headimagename) {
		this.headimagename = headimagename;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getSex() {
		return Sex;
	}
	public void setSex(String sex) {
		Sex = sex;
	}
	public String getUsertype() {
		return Usertype;
	}
	public void setUsertype(String usertype) {
		Usertype = usertype;
	}
	public String getSpot() {
		return Spot;
	}
	public void setSpot(String spot) {
		Spot = spot;
	}


	// getter和setter方法声明

}
