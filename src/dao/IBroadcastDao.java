package dao;

import java.time.LocalDateTime;

import pojos.Broadcast;

public interface IBroadcastDao {
	
	public void addBroadcast(Broadcast b) throws Exception;
	
	public void deleteBroadcast(Broadcast b) throws Exception;
	
	public void changeBroadcastProjectionTime(Broadcast b) throws Exception;
	// the 2nd parameter is useless the Broadcast constructor wants projectionTime
	void addBroadcast(Broadcast b, LocalDateTime projectionTime) throws Exception;

}
