package Bean;

public class UserDetail {
	private String username,headimagename,Email,Sex,Usertype,Spot;  //focused表示是否被当前用户

	public UserDetail(String username, String headimagename, String email,
			String sex, String usertype, String spot) {
		super();
		this.username = username;
		this.headimagename = headimagename;
		this.Email = email;
		this.Sex = sex;
		this.Usertype = usertype;
		this.Spot = spot;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHeadimagename() {
		return headimagename;
	}

	public void setHeadimagename(String headimagename) {
		this.headimagename = headimagename;
	}

	public String getEmail() {
		return Email;
	}

	public void setEmail(String email) {
		Email = email;
	}

	public String getSex() {
		return Sex;
	}

	public void setSex(String sex) {
		Sex = sex;
	}

	public String getUsertype() {
		return Usertype;
	}

	public void setUsertype(String usertype) {
		Usertype = usertype;
	}

	public String getSpot() {
		return Spot;
	}

	public void setSpot(String spot) {
		Spot = spot;
	}
	
}
