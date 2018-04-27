package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

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
		
		PreparedStatement s = connection.prepareStatement("INSERT INTO movies (title, description,rating,duration,file_location) VALUES (?,?,?,?,?)",Statement.RETURN_GENERATED_KEYS);
		s.setString(1,m.getTitle());
		s.setString(2, m.getDescription());
		s.setDouble(3, m.getRating());
		s.setDouble(4, m.getDuration());
		s.setString(5, m.getFileLocation());
		s.executeUpdate();
		
		// set the ID for the instance of Movie m
		ResultSet result = s.getGeneratedKeys();
		result.next(); // we write next cuz it starts from -1
		m.setId((int)result.getLong(1)); // or 1 instead of id
		
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
		PreparedStatement s = connection.prepareStatement("SELECT id,title,description,rating,duration,file_location FROM movies");
		ArrayList<Movie> movies = new ArrayList<>();
		ResultSet result = s.executeQuery();
		while(result.next()) {
			Movie m = new Movie(
					result.getInt("id"),
					result.getString("title"),
					result.getString("description"),
					result.getDouble("rating"),
					result.getDouble("duration"),
					result.getString("file_location")
					);
			movies.add(m);
		}
		return movies;
	}
	
	@Override
	public Movie getMovieById(int id)  throws SQLException, InvalidDataException {
		PreparedStatement s = connection.prepareStatement("SELECT id,title,description,rating,duration,file_location FROM movies WHERE id=?");
		s.setInt(1, id);
		ResultSet result = s.executeQuery();
		result.next();
		Movie movie = new Movie(
					result.getInt("id"),
					result.getString("title"),
					result.getString("description"),
					result.getDouble("rating"),
					result.getDouble("duration"),
					result.getString("file_location")
					);
		return movie;
	}

	public ArrayList<String> getMoviesContains(String term) throws SQLException {
		
		ArrayList<String> movies = new ArrayList<>();
		String query = "SELECT title FROM movies WHERE title LIKE ?";
		System.out.println("movie");
		try(PreparedStatement ps = connection.prepareStatement(query)){
			ps.setString(1,"%"+ term + "%");
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
                movies.add(rs.getString("title"));
			}
		}
		return movies;
	}

	public ArrayList<String> getAllMoviesNames() throws SQLException {
		
		ArrayList<String> moviesNames = new ArrayList<>();
		String query = "SELECT title FROM movies ";
		System.out.println("movie");
		try(PreparedStatement ps = connection.prepareStatement(query)){
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				moviesNames.add(rs.getString("title"));
			}
		}
		return moviesNames;
	}
}
