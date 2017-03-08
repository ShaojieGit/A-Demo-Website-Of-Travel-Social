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

	
	// myFile����������װ�ϴ����ļ�
	private File myFile;
	
	// myFileContentType����������װ�ϴ��ļ�������
	private String myFileContentType;

	// myFileFileName����������װ�ϴ��ļ����ļ���
	private String myFileFileName;

	HttpSession session=ServletActionContext.getRequest().getSession();
	//private PublishService CurrentPublishService=new PublishService();  //������д�����
	public String execute() throws Exception {
		
		String username;
		PublishService CurrentPublishService;
		CurrentPublishService=new PublishService();
		username=(String)session.getAttribute("user");    //����û���
		System.out.println("��̬�������ݣ�"+actiontext);
		System.out.println("�ϴ��ļ�����"+myFileFileName);
		System.out.println("�û�����"+username);
		
		if(!isInvalid(getMyFileFileName()))   //�û��ϴ���ͷ��
		{
			

			SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
			String time=(String)df.format(new Date());
			String timeString=time.substring(0, 4)+time.substring(5,7)+time.substring(8, 10)+time.substring(11, 13)+time.substring(14, 16)+time.substring(17, 19);
			myFileFileName=(String)session.getAttribute("user")+timeString+myFileFileName;
			//����myFile����һ���ļ�������
			InputStream is = new FileInputStream(myFile);
			
			// �����ϴ��ļ�Ŀ¼
			String uploadPath = ServletActionContext.getServletContext()
					.getRealPath("/ActionImage");
			
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
	public String getActiontext() {
		return actiontext;
	}

	public void setActiontext(String actiontext) {
		this.actiontext = actiontext;
	}

	//�������ϴ�ͷ����صķ���



	//���myFileֵ
	public File getMyFile() {
		return myFile;
	}

	public String getTowhere() {
		return towhere;
	}

	public void setTowhere(String towhere) {
		this.towhere = towhere;
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

