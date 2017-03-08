package action;
import java.util.List;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import Bean.SimpleSpot;
import service.SearchSpotService;
public class SearchSpotAction extends ActionSupport{
	private String searchkey,keyname;
	public String execute() throws Exception {
		
    	SearchSpotService CurrentSearchSpotService = new SearchSpotService();
		List<SimpleSpot> searchResult = CurrentSearchSpotService.searchSpot(searchkey,keyname);
		System.out.println(searchkey+keyname);
		System.out.println("搜索到几个景点"+searchResult.size());
		ServletActionContext.getRequest().setAttribute("searchResult", searchResult);
		
		return SUCCESS;
	}
	public String getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}
	public String getKeyname() {
		return keyname;
	}
	public void setKeyname(String keyname) {
		this.keyname = keyname;
	}
	
}
