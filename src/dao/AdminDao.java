package dao;

import java.sql.Connection;
import java.sql.SQLException;

import database.DBManager;
import exceptions.IlligalAdminActionException;
import exceptions.InvalidDataException;
import exceptions.NotAnAdminException;
import pojos.Broadcast;
import pojos.Movie;
import pojos.User;

public class AdminDao implements IAdminDao {
	
	private static AdminDao instance;
	private Connection connection;
	
	public synchronized static AdminDao getInstance() {
		if(instance == null) {
			instance = new AdminDao();
		}
		return instance;
	}
	
	private AdminDao() {
		connection = DBManager.getInstance().getConnection();
	}

	
	// TODO switch all methods used here from USERDAO to HERE, and remove validations! and put validations in MANAGER
	@Override
	public void addNewMovie(Movie m, User admin) throws SQLException, NotAnAdminException {
		if(admin.getIsAdmin()){
			MovieDao.getInstance().addMovie(m);
		}else{
			throw new NotAnAdminException();
		}
	}
	

	@Override
	public void addNewBroadcast(Broadcast b,User admin) throws SQLException, NotAnAdminException, InvalidDataException {
		
		if(admin.getIsAdmin()){
			BroadcastDao.getInstance().addBroadcast(b);
		}else{
			throw new NotAnAdminException();
		}
		
	}


	@Override
	public void removeMovie(Movie m, User admin) throws SQLException, NotAnAdminException {
		if(admin.getIsAdmin()){
			MovieDao.getInstance().deleteMovie(m);;
		}else{
			throw new NotAnAdminException();
		}
		
	}

	@Override
	public void removeBroadcast(Broadcast b, User admin) throws SQLException, NotAnAdminException, InvalidDataException {
		
		if(admin.getIsAdmin()){
			BroadcastDao.getInstance().deleteBroadcast(b);;
		}else{
			throw new NotAnAdminException();
		}
	}

	@Override
	public void changeUserIsAdminStatus(User admin, String email) throws NotAnAdminException, SQLException, InvalidDataException {
		
		if(admin.getIsAdmin()){
			UserDao.getInstance().createAdmin(email.trim());
		}else{
			throw new NotAnAdminException();
		}
		
	}

	@Override
	public void changeUserIsBannedStatus(User admin, User u, boolean isBanned) throws NotAnAdminException, SQLException, IlligalAdminActionException {
		if(admin.getIsAdmin()){
			UserDao.getInstance().changeUserIsBannedStatus(u, isBanned);
		}else{
			throw new NotAnAdminException();
		}
		
	}

	@Override
	public void setPromoPercent(User admin, Broadcast b, double promoPercent) throws SQLException, NotAnAdminException {
		if(admin.getIsAdmin()){
			BroadcastDao.getInstance().setPromoPercent(b, promoPercent);
		}else{
			throw new NotAnAdminException();
		}
		
	}

	

}
