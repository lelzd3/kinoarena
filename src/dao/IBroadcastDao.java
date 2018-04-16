package dao;

import java.time.LocalDateTime;

import pojos.Broadcast;

public interface IBroadcastDao {
	
	public void addBroadcast(Broadcast b) throws Exception;
	
	public void deleteBroadcast(Broadcast b) throws Exception;
	
	public void changeBroadcastProjectionTime(Broadcast b) throws Exception;

	void addBroadcast(Broadcast b, LocalDateTime projectionTime) throws Exception;


}
