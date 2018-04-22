package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;

import database.DBManager;
import exceptions.InvalidDataException;
import pojos.Broadcast;
import pojos.Movie;

public class BroadcastDao implements IBroadcastDao{
	
	private static BroadcastDao instance;
	private Connection connection;
	
	public synchronized static BroadcastDao getInstance() {
		if(instance == null) {
			instance = new BroadcastDao();
		}
		return instance;
	}
	
	private BroadcastDao() {
		connection = DBManager.getInstance().getConnection();
	}


	@Override
	public void addBroadcast(Broadcast b, LocalDateTime projectionTime) throws InvalidDataException, SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO broadcasts(cinemas_id , movies_id, halls_id , projection_time , free_sits,price) VALUES(?, ?, ? , ? , ?,?)");
		ps.setInt(1, b.getCinemaId());
		ps.setInt(2, b.getMovieId());
		ps.setInt(3, b.getHallId());
		Timestamp ts = Timestamp.valueOf(projectionTime);
		// or Timestamp ts = Timestamp.valueOf(b.getProjectionTime());
		ps.setTimestamp(4, ts);
		ps.setInt(5, 100);
		ps.setDouble(6, b.getPrice());
		ps.executeUpdate();
		ps.close();
			
	}
	
	@Override
	public void deleteBroadcast(Broadcast b) throws InvalidDataException, SQLException {
		PreparedStatement ps = connection.prepareStatement("DELETE FROM broadcasts WHERE id = ?");
		ps.setInt(1, b.getId());
		ps.executeUpdate();
	}

	@Override
	public void changeBroadcastProjectionTime(Broadcast b,LocalDateTime projectionTime) throws Exception {
		PreparedStatement ps = connection.prepareStatement("UPDATE broadcasts SET projection_time = ? WHERE id = ?");
		Timestamp time = Timestamp.valueOf(projectionTime);
		ps.setTimestamp(1,time);
		ps.setInt(2,b.getId());
		ps.executeUpdate();
	}


	@Override
	public Collection<Broadcast> getAllBroadcastsForAMovie(Movie m) throws Exception {
		PreparedStatement s = connection.prepareStatement("SELECT id,cinemas_id,movies_id,halls_id,projection_time,free_sits,price FROM broadcasts WHERE movies_id = ?");
		s.setInt(1, m.getId());
		HashSet<Broadcast> broadcasts = new HashSet<>();
		ResultSet result = s.executeQuery();
		while(result.next()) {
			LocalDateTime time = result.getTimestamp("projection_time").toLocalDateTime();
			Broadcast b = new Broadcast(
					result.getInt("id"),
					result.getInt("cinemas_id"),
					result.getInt("movies_id"),
					result.getInt("halls_id"),
					time,
					result.getDouble("price")
					);
			broadcasts.add(b);
		}
		return broadcasts;
	}

	@Override
	public void setPromoPercent(Broadcast b, double promoPercent) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement("Select price FROM broadcasts WHERE id = ? ");
		ps.setInt(1, b.getId());
		ResultSet rs = ps.executeQuery();
		rs.next();
		double price = rs.getDouble("price");
		price = price - (price * promoPercent);
		
	    ps = connection.prepareStatement("UPDATE broadcasts SET price = ? WHERE id = ?");
		ps.setDouble(1, price);
		ps.setLong(2, b.getId());
		ps.executeUpdate();
		ps.close();
		
	}

	@Override
	public Collection<Broadcast> getAllBroadcasts() throws Exception {
		PreparedStatement s = connection.prepareStatement("SELECT id,cinemas_id,movies_id,halls_id,projection_time,free_sits,price FROM broadcasts");
		ArrayList<Broadcast> broadcasts = new ArrayList<>();
		ResultSet result = s.executeQuery();
		while(result.next()) {
			LocalDateTime time = result.getTimestamp("projection_time").toLocalDateTime();
			Broadcast b = new Broadcast(
					result.getInt("id"),
					result.getInt("cinemas_id"),
					result.getInt("movies_id"),
					result.getInt("halls_id"),
					time,
					result.getDouble("price")
					);
			broadcasts.add(b);
		}
		return broadcasts;
	}

	
}
