package dao;

import java.sql.Connection;

import database.DBManager;

public class ReservationDao implements IReservationDao {

	private static ReservationDao instance;
	private Connection connection;
	
	public synchronized static ReservationDao getInstance() {
		if(instance == null) {
			instance = new ReservationDao();
		}
		return instance;
	}
	
	private ReservationDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
}
