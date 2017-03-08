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
		System.out.println("传过来的景点名字---："+spotname);

		currentspotdetail=CurrentSpotInfoService.getSpotinfo(spotname);
		System.out.println("景点m名字---："+currentspotdetail.getSpotname());
		System.out.println("景点地点---："+currentspotdetail.getPosition());

			
		
		ServletActionContext.getRequest().setAttribute("currentspotdetail", currentspotdetail);
		spotname=null;
		return SUCCESS;
	}

	

	// getter和setter方法声明
	public String getSpotname() {
		return spotname;
	}

	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}

	
}
