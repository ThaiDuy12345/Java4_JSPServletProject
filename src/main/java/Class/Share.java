 package Class;


public class Share {
	private int ID;
	private int UserID;
	private int VideoID;
	private String Email;
	private String ShareDate;
	public Share() {}
	public Share(int iD, int userID, int videoID, String email, String shareDate) {
		super();
		ID = iD;
		UserID = userID;
		VideoID = videoID;
		Email = email;
		ShareDate = shareDate;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public int getUserID() {
		return UserID;
	}
	public void setUserID(int userID) {
		UserID = userID;
	}
	public int getVideoID() {
		return VideoID;
	}
	public void setVideoID(int videoID) {
		VideoID = videoID;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getShareDate() {
		return ShareDate;
	}
	public void setShareDate(String shareDate) {
		ShareDate = shareDate;
	}

}
