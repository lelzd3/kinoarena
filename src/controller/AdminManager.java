package controller;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;

import dao.BroadcastDao;
import dao.MovieDao;
import dao.UserDao;
import database.DBManager;
import exceptions.IlligalAdminActionException;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import pojos.Broadcast;
import pojos.Movie;
import pojos.User;

public class AdminManager {


	//Singleton and DBconnection
	private static Connection connection;
	private static AdminManager instance;

	public synchronized static  AdminManager getInstance() {
		if (instance == null) {
			instance = new AdminManager();
		}
		return instance;
	}

	private AdminManager() {
		connection = DBManager.getInstance().getConnection();
	}
	
	public void addNewMovie(Movie m, User admin) throws SQLException, NotAnAdminException {
		if(admin.getIsAdmin()){
			MovieDao.getInstance().addMovie(m);
		}else{
			throw new NotAnAdminException();
		}
	}
	


	public void addNewBroadcast(Broadcast b,User admin) throws SQLException, NotAnAdminException, InvalidDataException {
		if(admin.getIsAdmin()){
			BroadcastDao.getInstance().addBroadcast(b);
		}else{
			throw new NotAnAdminException();
		}	
	}



	public void removeMovie(Movie m, User admin) throws SQLException, NotAnAdminException {
		if(admin.getIsAdmin()){
			MovieDao.getInstance().deleteMovie(m);;
		}else{
			throw new NotAnAdminException();
		}	
	}


	public void removeBroadcast(Broadcast b, User admin) throws SQLException, NotAnAdminException, InvalidDataException {	
		if(admin.getIsAdmin()){
			BroadcastDao.getInstance().deleteBroadcast(b);;
		}else{
			throw new NotAnAdminException();
		}
	}


	public void changeUserIsAdminStatus(User admin, String email) throws NotAnAdminException, SQLException, InvalidDataException {
		if(admin.getIsAdmin()){
			UserDao.getInstance().createAdmin(email.trim());
		}else{
			throw new NotAnAdminException();
		}
	}


	public void changeUserIsBannedStatus(User admin, User u, boolean isBanned) throws NotAnAdminException, SQLException, IlligalAdminActionException {
		if(admin.getIsAdmin()){
			UserDao.getInstance().changeUserIsBannedStatus(u, isBanned);
		}else{
			throw new NotAnAdminException();
		}
	}


	public void setPromoPercent(User admin, Broadcast b, double promoPercent) throws SQLException, NotAnAdminException {
		if(admin.getIsAdmin()){
			BroadcastDao.getInstance().setPromoPercent(b, promoPercent);
		}else{
			throw new NotAnAdminException();
		}
	}
	
}
