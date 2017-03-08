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

	
	// myFile����������װ�ϴ����ļ�
	private File myFile;
	
	// myFileContentType����������װ�ϴ��ļ�������
	private String myFileContentType;

	// myFileFileName����������װ�ϴ��ļ����ļ���
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	private RegistService CurrentRegistService=new RegistService();
	public String execute() throws Exception {
		usertype="commonuser";   //Ĭ��Ϊ��ͨ�û�
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
			setMessage("�û����Ѿ����ڣ�");
			return INPUT;
		}
		if(session.getAttribute("user")!=null)
			session.removeAttribute("user");
		session.setAttribute("user", username);
		
		if (!isInvalid(getEmail()))    //����û�ע��ʱ��д��������Ϣ�� ע������
			CurrentRegistService.regist_email(email,username);
		if (!isInvalid(getSex()))    //����û�ע��ʱ��д��������Ϣ�� ע������
			CurrentRegistService.regist_sex(sex,username);
		//------------------�ϴ�ͷ��
		if(!isInvalid(getMyFileFileName()))
		{
			//ͷ��·���������ݿ�

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			String time=(String)df.format(new Date());
			String timeString=time.substring(0, 4)+time.substring(5,7)+time.substring(8, 10)+time.substring(11, 13)+time.substring(14, 16)+time.substring(17, 19);
			myFileFileName=(String)session.getAttribute("user")+timeString+myFileFileName;
			CurrentRegistService.regist_headimage(getMyFileFileName(),username);
			//����myFile����һ���ļ�������
			InputStream is = new FileInputStream(myFile);
			
			// �����ϴ��ļ�Ŀ¼
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/HeadImage");
			
			// ����Ŀ���ļ�
			File toFile = new File(uploadPath, this.getMyFileFileName());
			
			// ����һ�������
			OutputStream os = new FileOutputStream(toFile);

			//���û���
			byte[] buffer = new byte[1024];

			int length = 0;

			//��ȡmyFile�ļ������toFile�ļ���
			while ((length = is.read(buffer)) > 0) {
				os.write(buffer, 0, length);
			}
			
			//�ر�������
			is.close();
			
			//�ر������
			os.close();
			//�ѵ�ǰ�û���ͷ���ļ���������session��
			if(session.getAttribute("headimage")!=null)
				session.removeAttribute("headimage");
			session.setAttribute("headimage", myFileFileName);
		}
		else  //û���ϴ�ͷ��
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
			setMessage("��¼�ɹ�");
		return SUCCESS;
		}
		else
		{
			addActionError("��¼ʧ����������:");
			setMessage("��¼ʧ��");
			return ERROR;
		}*/
			
	}

	// ����ַ���������Ϊ��
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
	}

	// getter��setter��������
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
	//�������ϴ�ͷ����صķ���




		//���myFileֵ
		public File getMyFile() {
			return myFile;
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
