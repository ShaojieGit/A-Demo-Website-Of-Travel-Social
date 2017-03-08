package Bean;

import java.util.List;
import Bean.CommentSpot;
public class SpotDetail {
	private String spotname,username,position,level,phonenumber,spottext,collected; 
	private List<String> images;
	private List<CommentSpot> comments;
	public SpotDetail(String spotname, String username, String position,
			String level, String phonenumber, String spottext,String collected,
			List<String> images,List<CommentSpot> comments) {
		super();
		this.spotname = spotname;
		this.username = username;
		this.position = position;
		this.level = level;
		this.phonenumber = phonenumber;
		this.spottext = spottext;
		this.images = images;
		this.collected = collected;
	}
	public String getCollected() {
		return collected;
	}
	public void setCollected(String collected) {
		this.collected = collected;
	}
	public String getSpotname() {
		return spotname;
	}
	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPosition() {
		return position;
	}
	public void setPosition(String position) {
		this.position = position;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getSpottext() {
		return spottext;
	}
	public void setSpottext(String spottext) {
		this.spottext = spottext;
	}
	public List<String> getImages() {
		return images;
	}
	public void setImages(List<String> images) {
		this.images = images;
	}
	public List<CommentSpot> getComments() {
		return comments;
	}
	public void setComments(List<CommentSpot> comments) {
		this.comments = comments;
	}
	
	
	
}
