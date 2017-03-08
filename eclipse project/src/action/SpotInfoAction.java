package action;
import javax.servlet.http.*;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;

import service.SpotInfoService;

import javax.servlet.http.*;

import org.apache.struts2.ServletActionContext;

import test.test_returnaction;

import javax.servlet.http.*;

import java.util.ArrayList;
import java.util.List;

import Bean.*;

	
public class SpotInfoAction extends ActionSupport {
	private String spotname;
	private SpotDetail currentspotdetail;
	public int i,n;

	public String execute() throws Exception {
		HttpSession session=ServletActionContext.getRequest().getSession();
		if(session.getAttribute("spotname")!=null&&session.getAttribute("spotname")!="")
			setSpotname((String)session.getAttribute("spotname"));
			
		SpotInfoService CurrentSpotInfoService =new SpotInfoService();
		System.out.println("�������ľ�������---��"+spotname);

		currentspotdetail=CurrentSpotInfoService.getSpotinfo(spotname);
		System.out.println("����m����---��"+currentspotdetail.getSpotname());
		System.out.println("����ص�---��"+currentspotdetail.getPosition());

			
		
		ServletActionContext.getRequest().setAttribute("currentspotdetail", currentspotdetail);
		spotname=null;
		return SUCCESS;
	}

	

	// getter��setter��������
	public String getSpotname() {
		return spotname;
	}

	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}

	
}
