package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import database.DBManager;
import exceptions.InvalidDataException;
import pojos.Cinema;
import pojos.Hall;

public class HallDao implements IHallDao{

	private static HallDao instance;
	private Connection connection;
	
	public synchronized static HallDao getInstance() {
		if(instance == null) {
			instance = new HallDao();
		}
		return instance;
	}
	
	private HallDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	
	@Override
	public void addHall(Hall h) throws SQLException {
		
		PreparedStatement s = connection.prepareStatement("INSERT INTO halls (seats, cinema_id) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
		s.setInt(1, h.getSeats());
		s.setInt(2, h.getCinema_id());
		s.executeUpdate();
		
		// set the ID for the instance of Hall h
		ResultSet result = s.getGeneratedKeys();
		result.next(); // we write next cuz it starts from -1
		h.setId((int)result.getLong("id")); // or 1 instead of id
		
		s.close();
		
	}
	
	@Override
	public void deletehall(Hall h) throws SQLException {
		PreparedStatement s = connection.prepareStatement("DELETE FROM halls WHERE id = ?");
		s.setInt(1, h.getId());
		s.executeUpdate();
		s.close();
	}
	
	@Override
	public Collection<Hall> getAllHalls()  throws SQLException, InvalidDataException {
		PreparedStatement s = connection.prepareStatement("SELECT id,seats,cinemas_id FROM halls");
		ArrayList<Hall> halls = new ArrayList<>();
		ResultSet result = s.executeQuery();
		while(result.next()) {
			Hall h = new Hall(
							  result.getInt("id"),
							  result.getInt("seats"),
							  result.getInt("cinemas_id")
							  );
			halls.add(h);
		}
		return halls;
	}

	@Override
	public Collection<Hall> getAllHallsForACinema(Cinema c) throws Exception {
		PreparedStatement s = connection.prepareStatement("SELECT id,seats,cinemas_id FROM halls WHERE cinemas_id = ?");
		s.setInt(1, c.getId());
		ArrayList<Hall> halls = new ArrayList<>();
		ResultSet result = s.executeQuery();
		while(result.next()) {
			Hall h = new Hall(
							  result.getInt("id"),
							  result.getInt("seats"),
							  result.getInt("cinemas_id")
							  );
			halls.add(h);
		}
		return halls;
	}
}
