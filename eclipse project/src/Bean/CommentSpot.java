package Bean;
import java.util.List;

public class CommentSpot {
	private String commentuser,spotname,commentcontent,commenttime,headimagename;

	public CommentSpot(String commentuser, String spotname,
			String commentcontent, String commenttime,String headimagename) {
		super();
		this.commentuser = commentuser;
		this.spotname = spotname;
		this.commentcontent = commentcontent;
		this.commenttime = commenttime;
		this.headimagename = headimagename;
	}

	public String getHeadimagename() {
		return headimagename;
	}

	public void setHeadimagename(String headimagename) {
		this.headimagename = headimagename;
	}

	public String getCommentuser() {
		return commentuser;
	}

	public void setCommentuser(String commentuser) {
		this.commentuser = commentuser;
	}

	public String getSpotname() {
		return spotname;
	}

	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}

	public String getCommentcontent() {
		return commentcontent;
	}

	public void setCommentcontent(String commentcontent) {
		this.commentcontent = commentcontent;
	}

	public String getCommenttime() {
		return commenttime;
	}

	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
	}
	
}
