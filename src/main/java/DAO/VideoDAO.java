package DAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

import Class.User;
import Class.Video;
import SPClass.jdbc;

public class VideoDAO {
	public static int add(Video Object) {
		String sql = "Insert into Videos(Title, Poster, Views, Description, Link) values (?,?,?,?,?)";
		return jdbc.executeUpdate(sql, 
				Object.getTitle(),
				Object.getPoster(),
				Object.getViews(),
				Object.getDescription(),
				Object.getLink()
		);
	}
	public static int update(Video Object) {
		String sql = "Update Videos set Title = ?, Poster = ?, Views = ?, Description = ?, Link = ? where ID = ?";
		return jdbc.executeUpdate(sql, 
				Object.getTitle(),
				Object.getPoster(),
				Object.getViews(),
				Object.getDescription(),
				Object.getLink(),
				Object.getID()
		);
	}
	public static int delete(Video Object) {
		String sql = "Delete from Videos where ID = ?";
		return jdbc.executeUpdate(sql,
				Object.getID()
		);
	}
	public static Video searchByID(Video Object) throws SQLException {
		ResultSet rs = jdbc.executeQuery("Select * from Videos where ID = ?", Object.getID());
		while(rs.next()) {
			return new Video(
		           rs.getInt("ID"),
		           rs.getString("Title"),
		           rs.getString("Poster"),
		           rs.getInt("Views"),
		           rs.getString("Description"),
		           rs.getString("Link")
		    );
		}
		return null;
	}
	public static ArrayList<Video> searchAll() throws SQLException{
		ResultSet rs = jdbc.executeQuery("Select * from Videos");
        ArrayList<Video> list = new ArrayList<Video>();
        while(rs.next()){
            Video vd = new Video(
                    rs.getInt("ID"),
                    rs.getString("Title"),
                    rs.getString("Poster"),
                    rs.getInt("Views"),
                    rs.getString("Description"),
                    rs.getString("Link")
            );
            list.add(vd);
        }
        return list;
	}
	public static ArrayList<Video> sp_SearchAllWithOrder() throws SQLException{
		ResultSet rs = jdbc.executeQuery("exec sp_SearchAllWithOrder");
        ArrayList<Video> list = new ArrayList<Video>();
        while(rs.next()){
            Video vd = new Video(
                    rs.getInt("ID"),
                    rs.getString("Title"),
                    rs.getString("Poster"),
                    rs.getInt("Views"),
                    rs.getString("Description"),
                    rs.getString("Link")
            );
            list.add(vd);
        }
        return list;
	}
	public static int sp_GetVideoLike(Video Object) throws SQLException{
		ResultSet rs = jdbc.executeQuery("exec sp_GetVideoLike "+Object.getID());
		while(rs.next()) {
			return rs.getInt("Like");
		}
		return 0;
	}
	public static int sp_GetVideoShare(Video Object) throws SQLException{
		ResultSet rs = jdbc.executeQuery("exec sp_GetVideoShare "+Object.getID());
		while(rs.next()) {
			return rs.getInt("Share");
		}
		return 0;
	}
	public static int sp_IncreaseViews(Video Object) throws SQLException{
		return jdbc.executeUpdate("exec sp_IncreaseViews "+Object.getID());
	}
	public static ArrayList<Video> sp_SearchByTitle(String KeyWord) throws SQLException{
		String sql = "exec sp_SearchByTitle N'"+KeyWord+"'";
		System.out.println(sql);
		ResultSet rs = jdbc.executeQuery(sql);
        ArrayList<Video> list = new ArrayList<Video>();
        while(rs.next()){
            Video vd = new Video(
                    rs.getInt("ID"),
                    rs.getString("Title"),
                    rs.getString("Poster"),
                    rs.getInt("Views"),
                    rs.getString("Description"),
                    rs.getString("Link")
            );
            list.add(vd);
        }
        return list;
	}
	public static ArrayList<Video> sp_SearchAllWithFavorite(int ID) throws SQLException{
		String sql = "exec sp_SearchAllWithFavorite ?";
		ResultSet rs = jdbc.executeQuery(sql, ID);
		ArrayList<Video> list = new ArrayList<Video>();
		while(rs.next()) {
			Video vd = new Video(
					rs.getInt("ID"),
					rs.getString("Title"),
					rs.getString("Poster"),
					rs.getInt("Views"),
					rs.getString("Description"),
					rs.getString("Link")
			);
			list.add(vd);
		}
		return list;
	}
}
