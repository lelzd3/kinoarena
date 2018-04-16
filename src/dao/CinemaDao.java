package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Collection;
import java.util.HashSet;

import database.DBManager;
import exceptions.InvalidDataException;
import pojos.Cinema;
import pojos.Hall;

public class CinemaDao implements ICinemaDao{

	private static CinemaDao instance;
	private Connection connection;
	
	public synchronized static CinemaDao getInstance() {
		if(instance == null) {
			instance = new CinemaDao();
		}
		return instance;
	}
	
	private CinemaDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	@Override
	public void addCinema(Cinema c) throws SQLException {
		
		PreparedStatement s = connection.prepareStatement("INSERT INTO cinemas (name, address) VALUES (?,?)",Statement.RETURN_GENERATED_KEYS);
		s.setString(1, c.getName());
		s.setString(2, c.getAddress());
		s.executeUpdate();
		
		// set the ID for the instance of Cinema c
		ResultSet result = s.getGeneratedKeys();
		result.next(); // we write next cuz it starts from -1
		c.setId((int)result.getLong("id")); // or 1 instead of id
		
		s.close();
		
	}
	
	@Override
	public void deleteCinema(Cinema c) throws SQLException {
		PreparedStatement s = connection.prepareStatement("DELETE FROM cinemas WHERE id = ?");
		s.setInt(1, c.getId());
		s.executeUpdate();
		s.close();
	}
	
	@Override
	public Collection<Cinema> getAllCinemas()  throws SQLException, InvalidDataException {
		PreparedStatement s = connection.prepareStatement("SELECT id,name,address FROM cinemas");
		HashSet<Cinema> cinemas = new HashSet<>();
		ResultSet result = s.executeQuery();
		while(result.next()) {
			Cinema c = new Cinema(
							  result.getInt("id"),
							  result.getString("name"),
							  result.getString("address")
							  );
			cinemas.add(c);
		}
		return cinemas;
	}
	
}
