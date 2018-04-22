package dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import pojos.Broadcast;
import pojos.Movie;

public interface IBroadcastDao {

	public void deleteBroadcast(Broadcast b) throws Exception;
	
	public void changeBroadcastProjectionTime(Broadcast b,LocalDateTime projectionTime) throws Exception;

	void addBroadcast(Broadcast b) throws Exception;

	public Collection<Broadcast> getAllBroadcastsForAMovie(Movie m) throws Exception;
	
	public void setPromoPercent(Broadcast b, double promoPercent) throws Exception;
	public Collection<Broadcast> getAllBroadcasts() throws Exception;
	public Broadcast getBroadcastById(int id) throws Exception;
}
