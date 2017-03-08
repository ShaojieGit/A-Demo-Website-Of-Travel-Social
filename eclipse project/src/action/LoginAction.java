package action;

import org.apache.struts2.ServletActionContext;
import com.opensymphony.xwork2.ActionSupport;
import service.LoginService;
import service.ImageService;
import service.CommonService;
import javax.servlet.http.*;
public class LoginAction extends ActionSupport {
	private String message, username, password;
	private String message1,message2;
	HttpSession session=ServletActionContext.getRequest().getSession();
	private LoginService CurrentLoginService=new LoginService();
	private ImageService CurrentImageService=new ImageService();
	private CommonService CurrentCommonService=new CommonService();
	String HeadImageName;
	String usertype;
	public String execute() throws Exception {
		
		System.out.println("execute method");
		System.out.print(username+password);
		int temp=0;
		if (isInvalid(getUsername()))
			{
			temp=1;
			setMessage1("用户名不能为空");
			}
		if (isInvalid(getPassword()))
		{
			temp=1;
			setMessage2("密码不能为空");
		}
		if(temp==1)
		{
			return INPUT;
		}
			
		if(CurrentLoginService.isCorrect(username,password))
		{
			//把用户名存到session里
			if(session.getAttribute("user")!=null)
				session.removeAttribute("user");
			session.setAttribute("user", username);
			//获取用户类型
			usertype=CurrentCommonService.getUsertype(username);  //系统开始注册时默认为游客用户
			if(session.getAttribute("usertype")!=null)
				session.removeAttribute("usertype");
			session.setAttribute("usertype", usertype);
			//获得用户头像
			HeadImageName=CurrentImageService.getHeadImage(username);
			if(session.getAttribute("headimage")!=null)
				session.removeAttribute("headimage");
			if(HeadImageName!=null)   //用户已经上传了头像
			{
				session.setAttribute("headimage", HeadImageName);
			}
			else
			{
				session.setAttribute("headimage", "default_headimage.jpg");
			}
			
			//ServletActionContext.getRequest().setAttribute("login", "true");
			setMessage("登录成功");
		return SUCCESS;
		}
		else
		{
			setMessage("用户名或密码错误！");
			return ERROR;
		}
			
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

	public String getMessage1() {
		return message1;
	}

	public void setMessage1(String message1) {
		this.message1 = message1;
	}
	
	public String getMessage2() {
		return message2;
	}

	public void setMessage2(String message2) {
		this.message2 = message2;
	}
	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
