package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;

import com.sun.media.sound.InvalidDataException;

import database.DBManager;
import exceptions.WrongCredentialsException;
import pojos.Movie;
import pojos.User;

public class UserDao implements IUserDao{
	
	private static UserDao instance;
	private Connection connection;
	
	public synchronized static UserDao getInstance() {
		if(instance == null) {
			instance = new UserDao();
		}
		return instance;
	}
	
	private UserDao() {
		connection = DBManager.getInstance().getConnection();
	}
	

	@Override
	public void loginCheck(String username, String password) throws SQLException, WrongCredentialsException {
		
		try (PreparedStatement ps = connection.prepareStatement("SELECT password FROM users WHERE username = ?")) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			//!BCrypt.checkpw(password, rs.getString("password")
			if (!rs.next() || !rs.getString("password").equals(password)) {
				throw new WrongCredentialsException();
			}
			ps.close();
		}
		
	}

	@Override
	public void addUser(User u) throws Exception {
		
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO users(username, password, email, first_name, last_name , isAdmin) VALUES(?, ?, ?, ?, ?, ?)")) {
			ps.setString(1, u.getUsername());
		//	String hashedPassword = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt());
			ps.setString(2, u.getPassword());
			ps.setString(3, u.getEmail());
			ps.setString(4, u.getFirstname());
			ps.setString(5, u.getLastname());
			ps.setInt(6, 0);
			ps.executeUpdate();
			ps.close();
		}
	}

	@Override
	public void deleteUser(User u) throws Exception {
		
		String sql = "DELETE FROM usres WHERE id=(?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, u.getId());
		stmt.executeUpdate();
	}

	@Override
	public void addMovieToFavoriteList(User u, Movie m) throws Exception {
		
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO users_favorite_movies(user_id , movies_id) VALUES(?, ?)")) {
			ps.setInt(1, u.getId());
			ps.setInt(2, m.getId());
			ps.executeUpdate();
			ps.close();
			
			
		}
		
	}

	@Override
	public void addMovieToWatchList(User u, Movie m) throws Exception {
		
		try (PreparedStatement ps = connection.prepareStatement(
				"INSERT INTO users_watchlist_movies(user_id , movies_id) VALUES(?, ?)")) {
			ps.setInt(1, u.getId());
			ps.setInt(2, m.getId());
			ps.executeUpdate();
			ps.close();
			
			
		}
	}

	@Override // rating should be between 1 and 10
	public void rateMovie(User u, Movie m,int rating) throws Exception {
		//ADD isAlreadyRated in DB!!@ TODO
		try (PreparedStatement ps = connection.prepareStatement("SELECT isAlreadyRated FROM users WHERE username = ?")) {
			ps.setString(1, u.getUsername());
			ResultSet rs = ps.executeQuery();
			rs.next();
			int isTrue = rs.getInt("isAlreadyRated");
			if(isTrue == 1 ) {
				connection.setAutoCommit(false);
					//TODO
			}
		}
			
			 
	}

	@Override
	public void existingUserNameCheck(String username) throws InvalidDataException, SQLException {
		
		try (PreparedStatement ps = connection.prepareStatement("SELECT username FROM users WHERE username = ?")) {
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			// TODO check if this works
			if (rs.next()) {
				throw new InvalidDataException("Oops,problem in User Dao , this username is already used");
			}
			ps.close();
		}
		
	}
	
	
	
	
}
