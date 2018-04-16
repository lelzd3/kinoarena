package dao;

import java.sql.Connection;

import database.DBManager;

public class SeatDao implements ISeatDao{

	private static SeatDao instance;
	private Connection connection;
	
	public synchronized static SeatDao getInstance() {
		if(instance == null) {
			instance = new SeatDao();
		}
		return instance;
	}
	
	private SeatDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
	//empty for now, no methods needed
}
