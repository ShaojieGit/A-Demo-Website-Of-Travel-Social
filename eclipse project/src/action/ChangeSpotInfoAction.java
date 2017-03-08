package action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.ChangeSpotInfoService;

import javax.servlet.http.*;

import service.RegistService;
public class ChangeSpotInfoAction extends ActionSupport {
	private String spottext, phonenumber, level,spotname;
	
	// myFile属性用来封装上传的文件
	private File myFile;
	
	// myFileContentType属性用来封装上传文件的类型
	private String myFileContentType;

	// myFileFileName属性用来封装上传文件的文件名
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	private ChangeSpotInfoService CurrentChangeSpotInfoService=new ChangeSpotInfoService();
	public String execute() throws Exception {
		
		
		String newspotimage=null;
		if(!isInvalid(getMyFileFileName()))
		{
			newspotimage=getMyFileFileName();
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
		CurrentChangeSpotInfoService.ChangeSpotInfo(spottext,phonenumber,level,newspotimage,spotname);
		return SUCCESS;

			
	}

	public String getSpotname() {
		return spotname;
	}

	public void setSpotname(String spotname) {
		this.spotname = spotname;
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

	// 检查字符串，不能为空
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
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
