package Bean;

public class User {
	private String username,headimagename,focused,focusme;  //focused表示是否被当前用户
	public User(String username,String headimagename,String focused,String focusme)
	{
		this.username=username;
		this.headimagename=headimagename;
		this.focused=focused;
		this.focusme=focusme;
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
	public String getFocused()
	{
		return focused;
	}
	public void setFocused(String focused)
	{
		this.focused=focused;
	}
	public String getFocusme()
	{
		return focusme;
	}
	public void setFocusme(String focusme)
	{
		this.focusme=focusme;
	}
}
