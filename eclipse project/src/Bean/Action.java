package Bean;
import java.util.List;

public class Action {
	private String actiontext,actionimage,publishtime,username,headimagename,shared;
	private List<Comment> comments;
	public Action(String actiontext,String actionimage,String publishtime,String username,String headimagename,String shared,List<Comment> comments)
	{
		this.actiontext=actiontext;
		this.actionimage=actionimage;
		this.publishtime=publishtime;
		this.username=username;
		this.headimagename=headimagename;
		this.shared=shared;
		this.comments=comments;
		
	}
	public String getActiontext()
	{
		return actiontext;
	}
	public void setActiontext(String actiontext)
	{
		this.actiontext=actiontext;
	}
	public String getActionimage()
	{
		return actionimage;
	}
	public void setActionimage(String actionimage)
	{
		this.actionimage=actionimage;
	}
	public String getPublishtime()
	{
		return publishtime;
	}
	public void setPublishtime(String publishtime)
	{
		this.publishtime=publishtime;
	}
	public String getUsername()
	{
		return username;
	}
	public void setUsername(String username)
	{
		this.username=username;
	}
	public String getHeadimagename()
	{
		return headimagename;
	}
	public void setHeadimagename(String headimagename)
	{
		this.headimagename=headimagename;
	}
	public String getShared()
	{
		return shared;
	}
	public void setShared(String shared)
	{
		this.shared=shared;
	}
	public List<Comment> getComments()
	{
		return comments;
	}
	public void setComments(List<Comment> comments)
	{
		this.comments=comments;
	}
}
