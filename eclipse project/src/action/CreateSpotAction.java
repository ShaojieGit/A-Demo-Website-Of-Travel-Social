
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
	// myFile����������װ�ϴ����ļ�
	private File myFile;
	
	// myFileContentType����������װ�ϴ��ļ�������
	private String myFileContentType;

	// myFileFileName����������װ�ϴ��ļ����ļ���
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	private CreateSpotService CurrentCreateSpotService=new CreateSpotService();
	public String execute() throws Exception {
		String position;   //�ص�
		position=city+district;
		String username;
		username=(String)session.getAttribute("user");
		String spotimage=null;
		if(!isInvalid(getMyFileFileName()))
		{
			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			String time=(String)df.format(new Date());
			String timeString=time.substring(0, 4)+time.substring(5,7)+time.substring(8, 10)+time.substring(11, 13)+time.substring(14, 16)+time.substring(17, 19);
			myFileFileName=(String)session.getAttribute("user")+timeString+myFileFileName;
			spotimage=getMyFileFileName();
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
		
		boolean createresult;
		createresult=CurrentCreateSpotService.createspot(spotname,username,position,spottext,spotimage,phonenumber,level);
		if(createresult)
			return SUCCESS;
		else
		{
			if(session.getAttribute("message")!=null)
				session.removeAttribute("message");
			session.setAttribute("message", "���������Ѿ����ڣ�");
			return INPUT;
		}
	}
		
		
	// ����ַ���������Ϊ��
		private boolean isInvalid(String value) {
			return (value == null || value.length() == 0);
		}




		//���myFileֵ
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
