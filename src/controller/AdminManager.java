package controller;

import java.sql.Connection;

import database.DBManager;

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
	
}
