package action;
import java.util.List;
import java.util.ArrayList;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import Bean.User;
import service.SearchUserService;
public class SearchUserAction extends ActionSupport{
	private String searchkey;
	public String execute() throws Exception {
		
		SearchUserService CurrentSearchUserService = new SearchUserService();
		List<User> searchResult = CurrentSearchUserService.searchUser(searchkey);
		
		ServletActionContext.getRequest().setAttribute("searchResult", searchResult);
		
		return SUCCESS;
	}
	public String getSearchkey() {
		return searchkey;
	}
	public void setSearchkey(String searchkey) {
		this.searchkey = searchkey;
	}
	
}
