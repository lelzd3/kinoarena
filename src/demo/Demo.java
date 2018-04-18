package demo;


import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Iterator;

import dao.AdminDao;
import dao.BroadcastDao;
import dao.MovieDao;
import dao.UserDao;
import pojos.Broadcast;
import pojos.Movie;
import pojos.User;

public class Demo {
	
	public static void main(String[] args) throws Exception {
		
		User u = UserDao.getInstance().getUser("Stanislav1");
		User u2 = UserDao.getInstance().getUser("Stanislav12");
		User u3 = UserDao.getInstance().getUser("borko94");
//		User userToDelete = UserDao.getInstance().getUser("babaqga"); deleted ;
		User admin = UserDao.getInstance().getUser("Admin1");
		
		System.out.println(u);
		System.out.println(u2);
		System.out.println(u3);
//		System.out.println(userToDelete);
		System.out.println(admin);
		
		HashSet<Movie> movies = (HashSet<Movie>) MovieDao.getInstance().getAllMovies();
		
		Iterator<Movie> iterator = movies.iterator();
		Movie movie1 = iterator.next();
		System.out.println(movie1);
		Movie movie2 = iterator.next();	
		System.out.println(movie2);
		
		System.out.println(BroadcastDao.getInstance().getAllBroadcastsForAMovie(movie1));	
		System.out.println(BroadcastDao.getInstance().getAllBroadcastsForAMovie(movie2));
		
//		UserDao.getInstance().addMovieToFavoriteList(admin, movie1); raboti
//		UserDao.getInstance().addMovieToWatchList(admin, movie2); raboti 
//		UserDao.getInstance().createAdmin("stan123@abv.bg"); raboti
//		UserDao.getInstance().deleteUser(userToDelete); deleted
		
		UserDao.getInstance().rateMovie(admin, movie2, 1);//tyka probvai s razlichni useri , razlichni ratingi i tn

		System.out.println(admin.getIsAdmin());
//		Broadcast b = new Broadcast(1, 1, 1, LocalDateTime.now(),124.12);
//		AdminDao.getInstance().addNewBroadcast(b, LocalDateTime.now() , admin); // vmesto u sloji nqko user det ima isAdmin=true i probvai s drugo vreme

//		Movie m = new Movie("Neveroqtnite Priklucheniq v Java", "mn dosaben film", 0, 69.99, "D://snimki");
//		AdminDao.getInstance().addNewMovie(m, admin);//tyka sushto
//		System.out.println(m);
		//tyka u sushto trqbva da e admin
		
//		AdminDao.getInstance().changeUserIsAdminStatus(u, "tyka sloji emaila na nqkoi user koit ne e admin za da go napravish"); raboti
		Broadcast b = new Broadcast(1, 1, 1, 1, LocalDateTime.now(), 123.123);
//		AdminDao.getInstance().getInstance().removeBroadcast(b, u);//u trqbva da e admin
//		Movie m = new Movie(5, "ds", "asd", 25.2, 2.0, "asd");
//		AdminDao.getInstance().removeMovie(m, u);//tyka pak
		AdminDao.getInstance().setPromoPercent(admin, b, 0.05);//tova namalq cenata na dadena projekciq s procentite v sluchaq 5

		
		
	}

}
