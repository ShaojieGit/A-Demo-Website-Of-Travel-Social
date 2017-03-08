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
	
	// myFile����������װ�ϴ����ļ�
	private File myFile;
	
	// myFileContentType����������װ�ϴ��ļ�������
	private String myFileContentType;

	// myFileFileName����������װ�ϴ��ļ����ļ���
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	private ChangeUserInfoService CurrentChangeUserInfoService=new ChangeUserInfoService();
	public String execute() throws Exception {
		
		
		String newspotimage=null;
		if(!isInvalid(getMyFileFileName()))
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			String time=(String)df.format(new Date());
			String timeString=time.substring(0, 4)+time.substring(5,7)+time.substring(8, 10)+time.substring(11, 13)+time.substring(14, 16)+time.substring(17, 19);
			myFileFileName=(String)session.getAttribute("user")+timeString+myFileFileName;
			System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~���ļ���"+myFileFileName);
			headimagename=getMyFileFileName();
			session.setAttribute("headimage", headimagename);
			//����myFile����һ���ļ�������
			InputStream is = new FileInputStream(myFile);
			
			// �����ϴ��ļ�Ŀ¼
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/HeadImage");
			System.out.println("1");
			// ����Ŀ���ļ�
			File toFile = new File(uploadPath, this.getMyFileFileName());
			System.out.println("2");
			// ����һ�������
			OutputStream os = new FileOutputStream(toFile);
			System.out.println("3");
			//���û���
			byte[] buffer = new byte[1024];
			System.out.println("4");
			int length = 0;
			System.out.println("5");
			//��ȡmyFile�ļ������toFile�ļ���
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			System.out.println("6");
			//�ر�������
			is.close();
			
			//�ر������
			os.close();
		}
		
		String change_result=CurrentChangeUserInfoService.ChangeSpotInfo(username,headimagename,Email,Sex,Usertype,Spot);
		if(change_result.equals("usernameexist"))
		{
			setMessage("�û����Ѿ�����");
			return INPUT;
		}
		return SUCCESS;

			
	}


	// ����ַ���������Ϊ��
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}


	//�������ϴ�ͷ����صķ���




		//���myFileֵ
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


		//����myFileֵ
		public void setMyFile(File myFile) {
			this.myFile = myFile;
		}

		//���myFileContentTypeֵ
		public String getMyFileContentType() {
			return myFileContentType;
		}

		//����myFileContentTypeֵ
		public void setMyFileContentType(String myFileContentType) {
			this.myFileContentType = myFileContentType;
		}

		//���myFileFileNameֵ
		public String getMyFileFileName() {
			return myFileFileName;
		}

		//����myFileFileNameֵ
		public void setMyFileFileName(String myFileFileName) {
			this.myFileFileName = myFileFileName;
		}
}
