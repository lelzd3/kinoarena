package dao;

import java.sql.Connection;

import database.DBManager;

public class HallDao implements IHallDao{

	private static HallDao instance;
	private Connection connection;
	
	public synchronized static HallDao getInstance() {
		if(instance == null) {
			instance = new HallDao();
		}
		return instance;
	}
	
	private HallDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
}
