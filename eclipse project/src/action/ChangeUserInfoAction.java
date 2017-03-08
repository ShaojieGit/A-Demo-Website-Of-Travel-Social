package action;

import java.io.File;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.ChangeUserInfoService;

import javax.servlet.http.*;

import service.RegistService;
public class ChangeUserInfoAction extends ActionSupport {
	private String username,headimagename,Email,Sex,Usertype,Spot,message; 
	
	// myFile属性用来封装上传的文件
	private File myFile;
	
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;

	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	private ChangeUserInfoService CurrentChangeUserInfoService=new ChangeUserInfoService();
	public String execute() throws Exception {
		
		
		String newspotimage=null;
		if(!isInvalid(getMyFileFileName()))
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String time=(String)df.format(new Date());
			String timeString=time.substring(0, 4)+time.substring(5,7)+time.substring(8, 10)+time.substring(11, 13)+time.substring(14, 16)+time.substring(17, 19);
			myFileFileName=(String)session.getAttribute("user")+timeString+myFileFileName;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~新文件名"+myFileFileName);
			headimagename=getMyFileFileName();
			session.setAttribute("headimage", headimagename);
			//基于myFile创建一个文件输入流
			InputStream is = new FileInputStream(myFile);
			
			// 设置上传文件目录
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/HeadImage");
			System.out.println("1");
			// 设置目标文件
			File toFile = new File(uploadPath, this.getMyFileFileName());
			System.out.println("2");
			// 创建一个输出流
			OutputStream os = new FileOutputStream(toFile);
			System.out.println("3");
			//设置缓存
			byte[] buffer = new byte[1024];
			System.out.println("4");
			int length = 0;
			System.out.println("5");
			//读取myFile文件输出到toFile文件中
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			System.out.println("6");
			//关闭输入流
			is.close();
			
			//关闭输出流
			os.close();
		}
		
		String change_result=CurrentChangeUserInfoService.ChangeSpotInfo(username,headimagename,Email,Sex,Usertype,Spot);
		if(change_result.equals("usernameexist"))
		{
			setMessage("用户名已经存在");
			return INPUT;
		}
		return SUCCESS;

			
	}


	// 检查字符串，不能为空
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}


	//下面是上传头像相关的方法




		//获得myFile值
		public File getMyFile() {
			return myFile;
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
