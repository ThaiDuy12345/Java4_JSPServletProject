package Class;



public class Video {

	private int ID;
	private String Title;
	private String Poster;
	private int Views;
	private String Description;
	private String Link;
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public String getPoster() {
		return Poster;
	}
	public void setPoster(String poster) {
		Poster = poster;
	}
	public int getViews() {
		return Views;
	}
	public void setViews(int views) {
		Views = views;
	}
	public String getDescription() {
		return Description;
	}
	public void setDescription(String description) {
		Description = description;
	}
	public String getLink() {
		return Link;
	}
	public void setLink(String link) {
		Link = link;
	}
	public Video(int iD, String title, String poster, int views, String description, String link) {
		super();
		ID = iD;
		Title = title;
		Poster = poster;
		Views = views;
		Description = description;
		Link = link;
	}

	public Video() {}
}
