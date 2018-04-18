package demo;

import java.awt.List;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Random;

import dao.AdminDao;
import dao.BroadcastDao;
import dao.MovieDao;
import dao.UserDao;
import exceptions.InvalidDataException;
import pojos.Broadcast;
import pojos.Movie;
import pojos.User;

public class Demo {
	
	public static void main(String[] args) throws InvalidDataException, SQLException {
		
		User u = UserDao.getInstance().getUser("tyka she napishesh nekuv user");
		User u2 = UserDao.getInstance().getUser("tyka she napishesh nekuv user");
		User u3 = UserDao.getInstance().getUser("tyka she napishesh nekuv user");
		User userToDelete = UserDao.getInstance().getUser("pak pishesh nekuv email na sushtstvuvasht user");
		BroadcastDao.getInstance().getAllBroadcastsForAMovie(m);
		ArrayList<Movie> movies = (ArrayList<Movie>) MovieDao.getInstance().getAllMovies();
		//get random movie
		Movie m = movies.get(new Random().nextInt(movies.size()));
		UserDao.getInstance().addMovieToFavoriteList(u, m);
		UserDao.getInstance().addMovieToWatchList(u, m);
		UserDao.getInstance().createAdmin("nekuv email na user koito ne e admin v db");
		UserDao.getInstance().deleteUser(userToDelete);
		UserDao.getInstance().rateMovie(u, m, 5);//tyka probvai s razlichni useri , razlichni ratingi i tn

		ArrayList<Broadcast> broadcasts = (ArrayList<Broadcast>) BroadcastDao.getInstance().getAllBroadcastsForAMovie(m);
		Broadcast b = broadcasts.get(new Random().nextInt(broadcasts.size()));
		AdminDao.getInstance().addNewBroadcast(b, LocalDateTime.now() , u); // vmesto u sloji nqko user det ima isAdmin=true i probvai s drugo vreme
		AdminDao.getInstance().addNewMovie(m, u);//tyka sushto
		//tyka u sushto trqbva da e admin
		AdminDao.getInstance().changeUserIsAdminStatus(u, "tyka sloji emaila na nqkoi user koit ne e admin za da go napravish");
		AdminDao.getInstance().getInstance().removeBroadcast(b, u);//u trqbva da e admin
		AdminDao.getInstance().removeMovie(m, u);//tyka pak
		AdminDao.getInstance().setPromoPercent(u, b, 5);//tova namalq cenata na dadena projekciq s procentite v sluchaq 5
		//za sega mislq che sa tiq , nqkoi se pripokrivat na User i Admin ama ako rabotat ednite i drugite trqbva da bachkat 
		// tui che ne e problem
		
		
	}

}
