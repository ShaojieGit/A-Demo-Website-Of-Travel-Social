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
	
	// myFile����������װ�ϴ����ļ�
	private File myFile;
	
	// myFileContentType����������װ�ϴ��ļ�������
	private String myFileContentType;

	// myFileFileName����������װ�ϴ��ļ����ļ���
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	private ChangeSpotInfoService CurrentChangeSpotInfoService=new ChangeSpotInfoService();
	public String execute() throws Exception {
		
		
		String newspotimage=null;
		if(!isInvalid(getMyFileFileName()))
		{
			newspotimage=getMyFileFileName();
			//����myFile����һ���ļ�������
			InputStream is = new FileInputStream(myFile);
			
			// �����ϴ��ļ�Ŀ¼
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/SpotImage");
			
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

	// ����ַ���������Ϊ��
	private boolean isInvalid(String value) {
		return (value == null || value.length() == 0);
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
