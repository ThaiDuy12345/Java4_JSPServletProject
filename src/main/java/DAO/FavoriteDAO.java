package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Class.Favorite;
import Class.Share;
import Class.User;
import Class.Video;
import SPClass.jdbc;

public class FavoriteDAO {
	public static int add(Favorite Object) {
		String sql = "Insert into Favorites(UserID, VideoID, LikeDate) values (?,?,?)";
		return jdbc.executeUpdate(
				sql,
				Object.getUserID(),
				Object.getVideoID(),
				Object.getLikeDate()
		);	
	}
	public static int update(Favorite Object) {
		String sql = "Update Favorites set UserID = ?, VideoID = ?, LikeDate = ? where ID = ?";
		return jdbc.executeUpdate(
				sql,
				Object.getUserID(),
				Object.getVideoID(),
				Object.getLikeDate(),
				Object.getID()
		);	
	}
	public static int delete(Favorite Object) {
		String sql = "Delete from Favorites where ID = ?";
		return jdbc.executeUpdate(
				sql,
				Object.getID()
		);	
	}
	public static Favorite searchByID(Favorite Object) throws SQLException {
		ResultSet rs = jdbc.executeQuery("Select * from Shares where ID = ?", Object.getID());
		ArrayList<Favorite> list = new ArrayList<Favorite>();
		while(rs.next()) {
			return new Favorite(
					rs.getInt("ID"),
					rs.getInt("UserID"),
					rs.getInt("VideoID"),
					rs.getString("LikeDate")
		    );
		}
		return null;
	}
	public static ArrayList<Favorite> searchAll() throws SQLException{
		String sql = "Select * from Favorites";
		ArrayList<Favorite> list = new ArrayList<Favorite>();
		ResultSet rs = jdbc.executeQuery(sql);
		while(rs.next()) {
			Favorite u = new Favorite(
					rs.getInt("ID"),
					rs.getInt("UserID"),
					rs.getInt("VideoID"),
					rs.getString("LikeDate")
			);
			list.add(u);
		}
		return list;
	}
	public static Favorite sp_SearchFavoriteByUserAndVideo(User u, Video v) throws SQLException{
		String sql = "exec sp_SearchFavoriteByUserAndVideo ?,?";
		ResultSet rs = jdbc.executeQuery(sql, u.getID(), v.getID());
		while(rs.next()) {
			return new Favorite(
					rs.getInt("ID"),
					rs.getInt("UserID"),
					rs.getInt("VideoID"),
					rs.getString("LikeDate")
			);
		}
		return null;
	}
	
}
