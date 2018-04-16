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
import pojos.Movie;

public class MovieDao implements IMovieDao{

	private static MovieDao instance;
	private Connection connection;
	
	public synchronized static MovieDao getInstance() {
		if(instance == null) {
			instance = new MovieDao();
		}
		return instance;
	}
	
	private MovieDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	@Override
	public void addMovie(Movie m) throws SQLException {
		
		PreparedStatement s = connection.prepareStatement("INSERT INTO movies (title, description,rating,duration) VALUES (?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		s.setString(1,m.getTitle());
		s.setString(2, m.getDescription());
		s.setDouble(3, m.getRating());
		s.setDouble(4, m.getDuration());
		s.executeUpdate();
		
		// set the ID for the instance of Movie m
		ResultSet result = s.getGeneratedKeys();
		result.next(); // we write next cuz it starts from -1
		m.setId((int)result.getLong("id")); // or 1 instead of id
		
		s.close();
		
	}
	
	@Override
	public void deleteMovie(Movie m) throws SQLException {
		PreparedStatement s = connection.prepareStatement("DELETE FROM movies WHERE id = ?");
		s.setInt(1, m.getId());
		s.executeUpdate();
		s.close();
	}
	
	@Override
	public Collection<Movie> getAllMovies()  throws SQLException, InvalidDataException {
		PreparedStatement s = connection.prepareStatement("SELECT id,title,description,rating,duration FROM movies");
		HashSet<Movie> movies = new HashSet<>();
		ResultSet result = s.executeQuery();
		while(result.next()) {
			Movie m = new Movie(
					result.getInt("id"),
					result.getString("title"),
					result.getString("description"),
					result.getDouble("rating"),
					result.getDouble("duration")
					);
			movies.add(m);
		}
		return movies;
	}
	
}
