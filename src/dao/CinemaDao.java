package dao;

import java.sql.Connection;

import database.DBManager;

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
	
}
