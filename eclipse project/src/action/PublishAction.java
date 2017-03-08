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
import service.PublishService;
import javax.servlet.http.*;

import service.RegistService;
public class PublishAction extends ActionSupport {
	private String actiontext,towhere;

	
	// myFile属性用来封装上传的文件
	private File myFile;
	
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;

	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	//private PublishService CurrentPublishService=new PublishService();  //夏雨来写这个类
	public String execute() throws Exception {
		
		String username;
		PublishService CurrentPublishService;
		CurrentPublishService=new PublishService();
		username=(String)session.getAttribute("user");    //获得用户名
		System.out.println("动态文字内容："+actiontext);
		System.out.println("上传文件名："+myFileFileName);
		System.out.println("用户名："+username);
		
		if(!isInvalid(getMyFileFileName()))   //用户上传了头像
		{
			

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String time=(String)df.format(new Date());
			String timeString=time.substring(0, 4)+time.substring(5,7)+time.substring(8, 10)+time.substring(11, 13)+time.substring(14, 16)+time.substring(17, 19);
			myFileFileName=(String)session.getAttribute("user")+timeString+myFileFileName;
			//基于myFile创建一个文件输入流
			InputStream is = new FileInputStream(myFile);
			
			// 设置上传文件目录
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/ActionImage");
			
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
		}
		CurrentPublishService.publish(actiontext,getMyFileFileName(),username);
		if(towhere.equals("zone"))
			return "zone";
		else
			return "friendaction";
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
	public String getActiontext() {
		return actiontext;
	}

	public void setActiontext(String actiontext) {
		this.actiontext = actiontext;
	}

	//下面是上传头像相关的方法



	//获得myFile值
	public File getMyFile() {
		return myFile;
	}

	public String getTowhere() {
		return towhere;
	}

	public void setTowhere(String towhere) {
		this.towhere = towhere;
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

