package Class;
public class User {
	private int ID;
	private String Password;
	private String Email;
	private String FullName;
	private boolean Admin;
	public User() {}
	public User(int iD, String password, String email, String fullName, boolean admin) {
		super();
		ID = iD;
		Password = password;
		Email = email;
		FullName = fullName;
		Admin = admin;
	}
	public int getID() {
		return ID;
	}
	public void setID(int iD) {
		ID = iD;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail() {
		return Email;
	}
	public void setEmail(String email) {
		Email = email;
	}
	public String getFullName() {
		return FullName;
	}
	public void setFullName(String fullName) {
		FullName = fullName;
	}
	public boolean isAdmin() {
		return Admin;
	}
	public void setAdmin(boolean admin) {
		Admin = admin;
	}

}
