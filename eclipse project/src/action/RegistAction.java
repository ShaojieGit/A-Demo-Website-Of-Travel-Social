package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import java.util.Date;
import java.text.SimpleDateFormat;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.LoginService;

import javax.servlet.http.*;

import service.RegistService;
public class RegistAction extends ActionSupport {
	private String message, username, password,email,sex,usertype;

	
	// myFile属性用来封装上传的文件
	private File myFile;
	
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;

	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	private RegistService CurrentRegistService=new RegistService();
	public String execute() throws Exception {
		usertype="commonuser";   //默认为普通用户
		System.out.print(username+password+email+sex);
		if (isInvalid(getUsername()))
			return INPUT;
		if (isInvalid(getPassword()))
			return INPUT;
		if (isInvalid(getUsertype()))
			return INPUT;
		String username_chek;
		username_chek=CurrentRegistService.regist_must(username,password,usertype);
		if(username_chek=="exist")
		{
			setMessage("用户名已经存在！");
			return INPUT;
		}
		if(session.getAttribute("user")!=null)
			session.removeAttribute("user");
		session.setAttribute("user", username);
		
		if (!isInvalid(getEmail()))    //如果用户注册时填写了邮箱信息就 注册邮箱
			CurrentRegistService.regist_email(email,username);
		if (!isInvalid(getSex()))    //如果用户注册时填写了邮箱信息就 注册邮箱
			CurrentRegistService.regist_sex(sex,username);
		//------------------上传头像
		if(!isInvalid(getMyFileFileName()))
		{
			//头像路径存入数据库

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String time=(String)df.format(new Date());
			String timeString=time.substring(0, 4)+time.substring(5,7)+time.substring(8, 10)+time.substring(11, 13)+time.substring(14, 16)+time.substring(17, 19);
			myFileFileName=(String)session.getAttribute("user")+timeString+myFileFileName;
			CurrentRegistService.regist_headimage(getMyFileFileName(),username);
			//基于myFile创建一个文件输入流
			InputStream is = new FileInputStream(myFile);
			
			// 设置上传文件目录
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/HeadImage");
			
			// 设置目标文件
			File toFile = new File(uploadPath, this.getMyFileFileName());
			
			// 创建一个输出流
			OutputStream os = new FileOutputStream(toFile);

			//设置缓存
			byte[] buffer = new byte[1024];

			int length = 0;

			//读取myFile文件输出到toFile文件中
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			
			//关闭输入流
			is.close();
			
			//关闭输出流
			os.close();
			//把当前用户的头像文件名保存在session里
			if(session.getAttribute("headimage")!=null)
				session.removeAttribute("headimage");
			session.setAttribute("headimage", myFileFileName);
		}
		else  //没有上传头像
		{
			CurrentRegistService.regist_headimage("default_headimage.jpg",username);
			if(session.getAttribute("headimage")!=null)
				session.removeAttribute("headimage");
			session.setAttribute("headimage", "default_headimage.jpg");
		}
		return SUCCESS;
		/*if(CurrentLoginService.isCorrect(username,password))
		{
			if(session.getAttribute("user")!=null)
				session.removeAttribute("user");
			session.setAttribute("user", username);
			//ServletActionContext.getRequest().setAttribute("login", "true");
			setMessage("登录成功");
		return SUCCESS;
		}
		else
		{
			addActionError("登录失败重新输入:");
			setMessage("登录失败");
			return ERROR;
		}*/
			
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
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getUsertype() {
		return usertype;
	}

	public void setUsertype(String usertype) {
		this.usertype = usertype;
	}
	//下面是上传头像相关的方法




		//获得myFile值
		public File getMyFile() {
			return myFile;
		}

		//设置myFile值
		public void setMyFile(File myFile) {
			this.myFile = myFile;
		}

		//获得myFileContentType值
		public String getMyFileContentType() {
			return myFileContentType;
		}

		//设置myFileContentType值
		public void setMyFileContentType(String myFileContentType) {
			this.myFileContentType = myFileContentType;
		}

		//获得myFileFileName值
		public String getMyFileFileName() {
			return myFileFileName;
		}

		//设置myFileFileName值
		public void setMyFileFileName(String myFileFileName) {
			this.myFileFileName = myFileFileName;
		}
}
