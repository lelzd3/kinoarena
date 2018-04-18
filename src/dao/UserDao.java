package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


import database.DBManager;
import exceptions.InvalidDataException;
import exceptions.WrongCredentialsException;
import pojos.Movie;
import pojos.Seat;
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
		
		PreparedStatement ps = connection.prepareStatement("SELECT password FROM users WHERE username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		//TODO encryption but in SERVLET!
		if (!rs.next() ) {
			// if there is nothing in result set -> username doesnt exists
			throw new WrongCredentialsException("Invalid username");
		}
		if (! BCrypt.checkpw(password, rs.getString("password") )) { //TODO in servlet?
			// if there is username but the pass doesnt match -> invalid password
			throw new WrongCredentialsException("Invalid password");
		}
		ps.close();
	}

	@Override
	public void addUser(User u) throws SQLException {
		
		PreparedStatement ps = connection.prepareStatement("INSERT INTO users(username, password, email, first_name, last_name , isAdmin) VALUES(?, ?, ?, ?, ?, ?)");
		ps.setString(1, u.getUsername());
		String hashedPassword = BCrypt.hashpw(u.getPassword(), BCrypt.gensalt()); // TODO encryption but in SERVLET!
		ps.setString(2, hashedPassword);
		ps.setString(3, u.getEmail());
		ps.setString(4, u.getFirstname());
		ps.setString(5, u.getLastname());
		ps.setInt(6, 0);
		ps.executeUpdate();
		ps.close();
	}
 
	@Override
	public void deleteUser(User u) throws SQLException {
		
		String sql = "DELETE FROM usres WHERE id=(?)";
		PreparedStatement stmt = connection.prepareStatement(sql);
		stmt.setInt(1, u.getId());
		stmt.executeUpdate();
		stmt.close();
	}

	@Override
	public void addMovieToFavoriteList(User u, Movie m) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO users_favorite_movies(users_id , movies_id) VALUES(?, ?)");
		ps.setInt(1, u.getId());
		ps.setInt(2, m.getId());
		ps.executeUpdate();
		ps.close();
		
	}

	@Override
	public void addMovieToWatchList(User u, Movie m) throws SQLException {
		PreparedStatement ps = connection.prepareStatement("INSERT INTO users_watchlist_movies(users_id , movies_id) VALUES(?, ?)");
		ps.setInt(1, u.getId());
		ps.setInt(2, m.getId());
		ps.executeUpdate();
		ps.close();
	
	}

	@SuppressWarnings("resource")
	@Override // rating should be between 1 and 10 
	public void rateMovie(User u, Movie m,int rating) throws SQLException, InvalidDataException {
		// TODO rateMovie method in UserDao , SHOULD INSERT DOUBLE RATING IN DB!!!!
		// TODO MOVE rating validation in USER MANAGER or <unknown> SERVLET
		//movies_id and users_id should be PK
		if(rating<1 || rating>10) {
			throw new InvalidDataException("Invalid entered rating. It should be between 1 and 10");
		}
		
		int sum = 0;
		int count = 0;
		double newRating = 0;
		int oldRatingGiven = 0;
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			
			//check if user has rated
			ps = connection.prepareStatement("SELECT rating FROM users_rated_movies WHERE users_id = ?");
			ps.setInt(1, u.getId());
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				oldRatingGiven = r.getInt(1);
			}
			
			// gets the number of users that have rated the movie
			ps = connection.prepareStatement("SELECT COUNT(users_id) FROM users_rated_movies WHERE movie_id = ?");
			ps.setInt(1, m.getId());
			ResultSet result = ps.executeQuery();
			result.next();
			count = result.getInt(1);
			
			// gets the sum of users ratings by now
			ps = connection.prepareStatement("SELECT SUM(rating) FROM users_rated_movies WHERE movie_id = ?");
			ps.setInt(1, m.getId());
			ResultSet result1 = ps.executeQuery();
			result1.next();
			sum = result.getInt(1);
			
			//delete old entry of user rating in users_rated_movies
			ps = connection.prepareStatement("DELETE FROM users_rated_movies WHERE users_id = ?");
			ps.setInt(1,u.getId());
			ps.executeUpdate();
			
			//put the new entry of user rating in users_rated_movies
			ps = connection.prepareStatement("INSERT INTO users_rated_movies (users_id,movies_id,rating) VALUES(?,?,?)");
			ps.setInt(1, u.getId());
			ps.setInt(2, m.getId());
			ps.setInt(3,rating);
			ps.executeUpdate();
			
			//if user has already rated decrement count
			if(oldRatingGiven > 0) {
				count--;
			}
			
			// Sum - old rating 
			//calculates the new rating , works even if sum,count and oldRatingGiven = 0
			newRating = ((sum-oldRatingGiven) + rating) / (count+1);
			
			// updates the movie
			ps = connection.prepareStatement("UPDATE movies SET rating = ? WHERE id = ?");
			ps.setDouble(1, newRating);
			ps.setInt(2, m.getId());
			ps.executeUpdate();
			
			connection.commit();
		}
		catch(SQLException e){
			connection.rollback();
			throw e;
		}
		finally {
			ps.close();
			connection.setAutoCommit(true);
		}

	}

	@Override
	public void existingUserNameCheck(String username) throws InvalidDataException, SQLException {
	
		PreparedStatement ps = connection.prepareStatement("SELECT username FROM users WHERE username = ?");
		ps.setString(1, username);
		ResultSet rs = ps.executeQuery();
		// TODO check if this works
		if (rs.next()) {
			throw new InvalidDataException("Oops,problem in User Dao , this username is already used");
		}
		ps.close();
	}

	public User getUser(String username, String pass) throws exceptions.InvalidDataException, SQLException {
		String sql = "SELECT id, first_name, last_name, username, password, email , phone_number FROM users WHERE username = ? AND password = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username);
		ps.setString(2, pass);
		ResultSet result = ps.executeQuery();
		if(result.next()) {
			return new User(result.getInt("id"),
					result.getString("username"),
					result.getString("password"),
					result.getString("first_name"),
					result.getString("last_name"),
					result.getString("email"),
					result.getString("phone_number")
					);
		}
		else {
			return null;
		}
	}

	
	
	
	
	
}
