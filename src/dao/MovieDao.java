package dao;

import java.sql.Connection;

import database.DBManager;

public class MovieDao implements IMovieDao{

	private static MovieDao instance;
	private Connection connection;
	
	public synchronized static MovieDao getInstance() {
		if(instance == null) {
			instance = new MovieDao();
		}
		return instance;
	}
	
	private MovieDao() {
		connection = DBManager.getInstance().getConnection();
	}
	
}
