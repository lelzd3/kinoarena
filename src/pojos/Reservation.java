package pojos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.sun.media.sound.InvalidDataException;

public class Reservation {
	
	private int id;
	private int user_id;
	private int broadcast_id;
	private ArrayList<Seat> allSeatsReserved;
	private LocalDateTime timeReservationIsMade;
	
	
	public Reservation(int user_id, int broadcast_id) {
		setUser_id(user_id);
		setBroadcast_id(broadcast_id);
		this.allSeatsReserved = new ArrayList<Seat>();
		this.timeReservationIsMade = LocalDateTime.now();
	}
	
	public Reservation(int user_id, int broadcast_id, ArrayList<Seat> allSeatsReserved) throws InvalidDataException {
		setUser_id(user_id);
		setBroadcast_id(broadcast_id);
		setAllSeatsReserved(allSeatsReserved);
		this.timeReservationIsMade = LocalDateTime.now();
	}
	
	public Reservation(int id,int user_id, int broadcast_id, ArrayList<Seat> allSeatsReserved) throws InvalidDataException {
		this(user_id, broadcast_id, allSeatsReserved);
		setId(id);
	}


	//getters:
	
	public int getId() {
		return id;
	}
	
	
	public int getUser_id() {
		return user_id;
	}
	
	
	public int getBroadcast_id() {
		return broadcast_id;
	}
	
	
	public LocalDateTime getTimeReservationIsMade() {
		return timeReservationIsMade;
	}

	public ArrayList<Seat> getAllSeatsReserved() {
		return allSeatsReserved;
	}
	
	//setters:
	
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUser_id(int user_id) {
		this.user_id = user_id;
	}
	
	public void setBroadcast_id(int broadcast_id) {
		this.broadcast_id = broadcast_id;
	}
	
	public void setTimeReservationIsMade(LocalDateTime timeReservationIsMade) throws InvalidDataException {
		if(timeReservationIsMade == null) {
			throw new InvalidDataException("Invalid data entered in reservation pojo");
		}
		this.timeReservationIsMade = timeReservationIsMade;
	}


	public void setAllSeatsReserved(ArrayList<Seat> allSeatsReserved) throws InvalidDataException {
		if(allSeatsReserved == null || allSeatsReserved.isEmpty()) {
			throw new InvalidDataException("Oops, error in setting reserved seats in reservation ");
		}
		this.allSeatsReserved = allSeatsReserved;
	}


	


	


	

	


	

}
