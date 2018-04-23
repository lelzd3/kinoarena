package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;

import database.DBManager;
import exceptions.IlligalAdminActionException;
import exceptions.InvalidDataException;
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
		
		PreparedStatement ps = connection.prepareStatement("INSERT INTO users(username, password, email, first_name, last_name , is_Admin) VALUES(?, ?, ?, ?, ?, ?)");
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
		
		String sql = "DELETE FROM users WHERE id=(?)";
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
		
		double newRating = 0;
		int oldRatingGiven = 0;
		PreparedStatement ps = null;
		try {
			connection.setAutoCommit(false);
			
			//check if user has rated and put value in oldRatingGiven
			ps = connection.prepareStatement("SELECT rating FROM users_rated_movies WHERE users_id = ?");
			ps.setInt(1, u.getId());
			ResultSet r = ps.executeQuery();
			if(r.next()) {
				oldRatingGiven = r.getInt(1);
			}
			
			//if oldRatingGiven is > 0 -> delete old entry of user rating in users_rated_movies
			if(oldRatingGiven > 0) {
				ps = connection.prepareStatement("DELETE FROM users_rated_movies WHERE users_id = ?");
				ps.setInt(1,u.getId());
				ps.executeUpdate();
			}
			
			//put the new entry of user rating in users_rated_movies
			ps = connection.prepareStatement("INSERT INTO users_rated_movies (users_id,movies_id,rating) VALUES(?,?,?)");
			ps.setInt(1, u.getId());
			ps.setInt(2, m.getId());
			ps.setInt(3,rating);
			ps.executeUpdate();
			
			//get AVG rating for movies_id = m.getId()
			ps = connection.prepareStatement("SELECT AVG(rating) FROM users_rated_movies WHERE movies_id = ?");
			ps.setInt(1, m.getId());
			ResultSet result2 = ps.executeQuery();
			result2.next();
			newRating = result2.getDouble(1);
			System.out.println("newRating "+newRating);
			
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

	public User getUser(String username) throws InvalidDataException, SQLException {
		String sql = "SELECT id, first_name, last_name, username, password, email , phone_number,is_Admin FROM users WHERE username = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, username);
		ResultSet result = ps.executeQuery();
		result.next();
		
		boolean isAdmin = result.getInt("is_Admin") == 1? true:false;
		
		return new User(
					result.getInt("id"),
					result.getString("username"),
					result.getString("password"),
					result.getString("first_name"),
					result.getString("last_name"),
					result.getString("email"),
					result.getString("phone_number"),
					isAdmin
					);
		
	}
	
	public void createAdmin(String email) throws SQLException, InvalidDataException{
		//will trqq if false here works (for isAdmin) , if it dosent work we should use 0 instead of false ,
		PreparedStatement ps = connection.prepareStatement("SELECT id FROM users WHERE email = ? AND is_Admin = ?");
		ps.setString(1, email);
		ps.setInt(2, 0);
		ResultSet result = ps.executeQuery();
		
		String userId = null;
		while(result.next()){
			userId = result.getString(1);
		}
		ps.close();
		
		if(userId == null){
			throw new InvalidDataException("Oops, problem in creating an admin! The email maybe is incorrect or user is already an admin");
		}
		
		PreparedStatement statement = connection.prepareStatement("UPDATE  users SET is_Admin = ? WHERE id = ?;");
		statement.setInt(1, 1);
		statement.setString(2, userId);
		statement.executeUpdate();
		statement.close();
		
	}

	public void changeUserIsBannedStatus(User u, boolean isBanned) throws IlligalAdminActionException, SQLException {
		
		if (u.getIsBanned() && isBanned) {
			throw new IlligalAdminActionException();
		} else if (!u.getIsBanned() && !isBanned) {
			throw new IlligalAdminActionException();
		} else {
			PreparedStatement ps = connection.prepareStatement("UPDATE users SET isBanned = ? WHERE user_id = ?",
					Statement.RETURN_GENERATED_KEYS);
			ps.setBoolean(1, isBanned);
			ps.setInt(2, u.getId());
			ps.executeUpdate();
			ps.close();
		}
		
	}

	//TODO SHOULD TEST THIS METHOD
	public User getUserByEmail(String email) throws exceptions.InvalidDataException, SQLException {
		String sql = "SELECT id, first_name, last_name, username, password, email , phone_number,is_Admin FROM users WHERE email = ?";
		PreparedStatement ps = connection.prepareStatement(sql);
		ps.setString(1, email);
		ResultSet result = ps.executeQuery();
		result.next();
		
		boolean isAdmin = result.getInt("is_Admin") == 1? true:false;
		
		return new User(
					result.getInt("id"),
					result.getString("username"),
					result.getString("password"),
					result.getString("first_name"),
					result.getString("last_name"),
					result.getString("email"),
					result.getString("phone_number"),
					isAdmin
					);
		
	}

	@Override
	public Collection<User> getAllUsers() throws SQLException, InvalidDataException   {
		PreparedStatement ps = connection.prepareStatement("SELECT id, first_name, last_name, username, password, email , phone_number,is_Admin FROM users");
		ResultSet result = ps.executeQuery();
		ArrayList<User> users = new ArrayList<User>();
		
		
		while(result.next()) {
			boolean isAdmin = result.getInt("is_Admin") == 1? true:false;
			User user = new User(
					result.getInt("id"),
					result.getString("username"),
					result.getString("password"),
					result.getString("first_name"),
					result.getString("last_name"),
					result.getString("email"),
					result.getString("phone_number"),
					isAdmin
					);
			users.add(user);
		}
		return users;
	}
	
	
}
