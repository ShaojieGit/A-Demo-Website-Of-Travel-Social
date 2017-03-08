
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

import service.CreateSpotService;

import javax.servlet.http.*;

import service.RegistService;
public class CreateSpotAction extends ActionSupport {
	private String spotname, city, district,spottext,phonenumber,level;
	// myFile属性用来封装上传的文件
	private File myFile;
	
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;

	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	private CreateSpotService CurrentCreateSpotService=new CreateSpotService();
	public String execute() throws Exception {
		String position;   //地点
		position=city+district;
		String username;
		username=(String)session.getAttribute("user");
		String spotimage=null;
		if(!isInvalid(getMyFileFileName()))
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
			String time=(String)df.format(new Date());
			String timeString=time.substring(0, 4)+time.substring(5,7)+time.substring(8, 10)+time.substring(11, 13)+time.substring(14, 16)+time.substring(17, 19);
			myFileFileName=(String)session.getAttribute("user")+timeString+myFileFileName;
			spotimage=getMyFileFileName();
			//基于myFile创建一个文件输入流
			InputStream is = new FileInputStream(myFile);
			
			// 设置上传文件目录
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/SpotImage");
			
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
		
		boolean createresult;
		createresult=CurrentCreateSpotService.createspot(spotname,username,position,spottext,spotimage,phonenumber,level);
		if(createresult)
			return SUCCESS;
		else
		{
			if(session.getAttribute("message")!=null)
				session.removeAttribute("message");
			session.setAttribute("message", "景点名称已经存在！");
			return INPUT;
		}
	}
		
		
	// 检查字符串，不能为空
		private boolean isInvalid(String value) {
			return (value == null || value.length() == 0);
		}




		//获得myFile值
		public File getMyFile() {
			return myFile;
		}

		public String getSpotname() {
			return spotname;
		}

		public void setSpotname(String spotname) {
			this.spotname = spotname;
		}

		public String getCity() {
			return city;
		}

		public void setCity(String city) {
			this.city = city;
		}

		public String getDistrict() {
			return district;
		}

		public void setDistrict(String district) {
			this.district = district;
		}

		public String getSpottext() {
			return spottext;
		}

		public void setSpottext(String spottext) {
			this.spottext = spottext;
		}

		public String getPhonenumber() {
			return phonenumber;
		}

		public void setPhonenumber(String phonenumber) {
			this.phonenumber = phonenumber;
		}

		public String getLevel() {
			return level;
		}

		public void setLevel(String level) {
			this.level = level;
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
