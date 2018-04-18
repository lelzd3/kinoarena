package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
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
		PreparedStatement ps = connection.prepareStatement("INSERT INTO broadcasts(cinemas_id , movies_id, halls_id , projection_time , free_sits) VALUES(?, ?, ? , ? , ?)");
		ps.setInt(1, b.getCinemaId());
		ps.setInt(2, b.getMovieId());
		ps.setInt(3, b.getHallId());
		Timestamp ts = Timestamp.valueOf(projectionTime);
		// or Timestamp ts = Timestamp.valueOf(b.getProjectionTime());
		ps.setTimestamp(4, ts);
		ps.setInt(5, 100);
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
		PreparedStatement s = connection.prepareStatement("SELECT id,cinemas_id,movies_id,halls_id,projection_time,free_sits FROM broadcasts WHERE movies_id = ?");
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
					time
					);
			broadcasts.add(b);
		}
		return broadcasts;
	}

	
}
