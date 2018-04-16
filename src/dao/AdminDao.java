package dao;

import java.sql.Connection;

import database.DBManager;

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

}
