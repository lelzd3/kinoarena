package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Timestamp;
import java.time.LocalDateTime;

import database.DBManager;
import pojos.Broadcast;

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
	public void addBroadcast(Broadcast b, LocalDateTime projectionTime) throws Exception {
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO broadcasts(cinemas_id , movies_id, halls_id , projection_time , free_sits) VALUES(?, ?, ? , ? , ?)")) {
			ps.setInt(1, b.getCinemaId());
			ps.setInt(2, b.getMovieId());
			ps.setInt(3, b.getHallId());
			Timestamp ts = Timestamp.valueOf(projectionTime);
			ps.setTimestamp(4, ts);
			ps.setInt(5, 100);
			ps.close();
			
		}
		
	}

	@Override
	public void deleteBroadcast(Broadcast b) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void changeBroadcastProjectionTime(Broadcast b) throws Exception {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void addBroadcast(Broadcast b) throws Exception {
		// TODO Auto-generated method stub
		
	}

}
