package dao;

import java.sql.SQLException;
import java.time.LocalDateTime;

import exceptions.IlligalAdminActionException;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import pojos.Broadcast;
import pojos.Movie;
import pojos.User;

public interface IAdminDao {

	public void addNewMovie(Movie m , User admin) throws SQLException, NotAnAdminException;
	
	public void addNewBroadcast(Broadcast b , LocalDateTime projectionTime, User admin) throws Exception;
	
	public void removeMovie(Movie m , User admin) throws Exception;
	
	public void removeBroadcast(Broadcast b , User admin) throws Exception;
	
	public void changeUserIsAdminStatus(User admin, String email) throws Exception;
	
	public void changeUserIsBannedStatus(User admin, User u, boolean isBanned) throws Exception;
	
	// we will use it to make discount on some day/days of the week
	public void setPromoPercent(User admin, Broadcast b, double promoPercent) throws Exception;

	
	

}
