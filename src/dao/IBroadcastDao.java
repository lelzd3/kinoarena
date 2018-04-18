package dao;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Collection;

import pojos.Broadcast;
import pojos.Movie;

public interface IBroadcastDao {

	public void deleteBroadcast(Broadcast b) throws Exception;
	
	public void changeBroadcastProjectionTime(Broadcast b,LocalDateTime projectionTime) throws Exception;
	// the 2nd parameter is useless the Broadcast constructor wants projectionTime
	// but the logic is not layed out and maybe we will make a Broadcast object w/o time set to it
	void addBroadcast(Broadcast b, LocalDateTime projectionTime) throws Exception;

	public Collection<Broadcast> getAllBroadcastsForAMovie(Movie m) throws Exception;
	
	public void setPromoPercent(Broadcast b, double promoPercent) throws Exception;
}
