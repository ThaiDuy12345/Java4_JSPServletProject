package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Class.Share;
import Class.User;
import Class.Video;
import SPClass.jdbc;

public class UserDAO {
	public static int add(User Object) {
		String sql = "Insert into Users(Password, Email, FullName, Admin) values (?,?,?,?)";
		return jdbc.executeUpdate(
				sql,
				Object.getPassword(),
				Object.getEmail(),
				Object.getFullName(),
				Object.isAdmin()
		);	
	}
	public static int update(User Object) {
		String sql = "Update Users set Password = ?, Email = ?, FullName = ?, Admin = ? where ID = ?";
		return jdbc.executeUpdate(
				sql,
				Object.getPassword(),
				Object.getEmail(),
				Object.getFullName(),
				Object.isAdmin(),
				Object.getID()
		);	
	}
	public static int delete(User Object) {
		String sql = "Delete from Users where ID = ?";
		return jdbc.executeUpdate(
				sql,
				Object.getID()
		);	
	}
	public static User searchByID(User Object) throws SQLException {
		ResultSet rs = jdbc.executeQuery("Select * from Users where ID = ?", Object.getID());
		ArrayList<User> list = new ArrayList<User>();
		while(rs.next()) {
			return new User(
					rs.getInt("ID"),
					rs.getString("Password"),
					rs.getString("Email"),
					rs.getString("Fullname"),
					rs.getBoolean("Admin")
		    );
		}
		return null;
	}
	public static User searchByEmailAndPassword(String Password, String Email) throws SQLException{
		String sql = "Select * from Users where password like N'"+Password+"' and email like N'"+Email+"'";
		ResultSet rs = jdbc.executeQuery(sql);
		while(rs.next()) {
			return new User(
				rs.getInt("ID"),
				rs.getString("Password"),
				rs.getString("Email"),
				rs.getString("Fullname"),
				rs.getBoolean("Admin")
			);
		}
		return null;
	}
	public static ArrayList<User> searchAll() throws SQLException{
		String sql = "Select * from Users";
		ArrayList<User> list = new ArrayList<User>();
		ResultSet rs = jdbc.executeQuery(sql);
		while(rs.next()) {
			User u = new User(
					rs.getInt("ID"),
					rs.getString("Password"),
					rs.getString("Email"),
					rs.getString("Fullname"),
					rs.getBoolean("Admin")
			);
			list.add(u);
		}
		return list;
	}
	public static User sp_SearchUserWithEmail(String email) throws SQLException{
		String sql = "exec sp_SearchUserWithEmail '"+email+"'";
		ResultSet rs = jdbc.executeQuery(sql);
		while(rs.next()) {
			return new User(
					rs.getInt("ID"),
					rs.getString("Password"),
					rs.getString("Email"),
					rs.getString("Fullname"),
					rs.getBoolean("Admin")
			);
		}
		return null;
	}
}
