package Bean;
import java.util.List;

public class Comment {
	private String username,headimagename,commentcontent,commenttime,repeat;
	//private List<Comment> comment;
	public Comment(String username,String headimagename,String commentcontent,String commenttime,String repeat)
	{
		this.username=username;
		this.headimagename=headimagename;
		this.commentcontent=commentcontent;
		this.username=username;
		this.commenttime=commenttime;
		this.repeat=repeat;
	}
	public String getRepeat() {
		return repeat;
	}
	public void setRepeat(String repeat) {
		this.repeat = repeat;
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
	public String getCommentcontent()
	{
		return commentcontent;
	}
	public void setCommentcontent(String commentcontent)
	{
		this.commentcontent=commentcontent;
	}
	public String getCommenttime()
	{
		return commenttime;
	}
	public void setCommenttime(String commenttime)
	{
		this.commenttime=commenttime;
	}
/*	public List<Comment> getComment()
	{
		return comment;
	}
	public void setComment(List<Comment> comment)
	{
		this.comment=comment;
	}*/
}
