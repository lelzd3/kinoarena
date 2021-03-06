package dao;

import java.sql.SQLException;
import java.util.Collection;


import exceptions.WrongCredentialsException;
import pojos.Movie;
import pojos.User;

public interface IUserDao {
	
	public void loginCheck(String username,String password) throws WrongCredentialsException, SQLException;
	
	public void addUser(User u) throws Exception;
	
	public void deleteUser(User u) throws Exception;
	
	public void addMovieToFavoriteList(User u , Movie m) throws Exception;
	
	public void addMovieToWatchList(User u , Movie m) throws Exception;
	
	public void rateMovie(User u , Movie m, int rating) throws Exception;
	
	public void existingUserNameCheck(String username) throws Exception;
	//TODO add phone number method

	public Collection<User> getAllUsers() throws Exception;



}
