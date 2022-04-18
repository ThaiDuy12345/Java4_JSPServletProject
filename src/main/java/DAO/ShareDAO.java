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
import SPClass.jdbc;

public class ShareDAO {
	public static int add(Share Object) {
		String sql = "Insert into Shares(UserID, VideoID, Email, ShareDate) values (?,?,?,?)";
		return jdbc.executeUpdate(
				sql,
				Object.getUserID(),
				Object.getVideoID(),
				Object.getEmail(),
				Object.getShareDate()
		);	
	}
	public static int update(Share Object) {
		String sql = "Update Shares set UserID = ?, VideoID = ?,Email = ?, ShareDate = ? where ID = ?";
		return jdbc.executeUpdate(
				sql,
				Object.getUserID(),
				Object.getVideoID(),
				Object.getEmail(),
				Object.getShareDate(),
				Object.getID()
		);	
	}
	public static int delete(Share Object) {
		String sql = "Delete from Shares where ID = ?";
		return jdbc.executeUpdate(
				sql,
				Object.getID()
		);	
	}
	public static Share searchByID(Share Object) throws SQLException {
		ResultSet rs = jdbc.executeQuery("Select * from Shares where ID = ?", Object.getID());
		ArrayList<Share> list = new ArrayList<Share>();
		while(rs.next()) {
			return new Share(
					rs.getInt("ID"),
					rs.getInt("UserID"),
					rs.getInt("VideoID"),
					rs.getString("Email"),
					rs.getString("ShareDate")
		    );
		}
		return null;
	}
	public static ArrayList<Share> searchAll() throws SQLException{
		String sql = "Select * from Shares";
		ArrayList<Share> list = new ArrayList<Share>();
		ResultSet rs = jdbc.executeQuery(sql);
		while(rs.next()) {
			Share u = new Share(
					rs.getInt("ID"),
					rs.getInt("UserID"),
					rs.getInt("VideoID"),
					rs.getString("Email"),
					rs.getString("ShareDate")
			);
			list.add(u);
		}
		return list;
	}
}
