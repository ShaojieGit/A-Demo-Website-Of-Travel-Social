package Bean;

import java.util.List;

public class SimpleSpot {
	private String spotname,image;

	public SimpleSpot(String spotname, String image) {
		super();
		this.spotname = spotname;
		this.image = image;
	}

	public String getSpotname() {
		return spotname;
	}

	public void setSpotname(String spotname) {
		this.spotname = spotname;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	} 
	
}
