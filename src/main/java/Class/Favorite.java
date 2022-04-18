package Class;
public class Favorite {
	private int ID;
	private int UserID;
	private int VideoID;
	private String LikeDate;
	public Favorite() {}
	public Favorite(int iD, int userID, int videoID, String likeDate) {
		super();
		ID = iD;
		UserID = userID;
		VideoID = videoID;
		LikeDate = likeDate;
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
	public String getLikeDate() {
		return LikeDate;
	}
	public void setLikeDate(String likeDate) {
		LikeDate = likeDate;
	}

}
