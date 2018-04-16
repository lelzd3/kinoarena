package pojos;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.sun.media.sound.InvalidDataException;

public class Reservation {
	
	private int broadcastId;
	private ArrayList<Seat> allSeatsReserved;
	private LocalDateTime timeReservationIsMade;
	
	
	public Reservation(int broadcastId, int places) {
		allSeatsReserved= new ArrayList<>();
		setBroadcastId(broadcastId);
		this.timeReservationIsMade = LocalDateTime.now();
	}


	//getters:
	public int getBroadcastId() {
		return broadcastId;
	}


	public LocalDateTime getTimeReservationIsMade() {
		return timeReservationIsMade;
	}


	//setters:
	public void setTimeReservationIsMade(LocalDateTime timeReservationIsMade) throws InvalidDataException {
		if(timeReservationIsMade == null) {
			throw new InvalidDataException("Invalid data entered in reservation pojo");
		}
		this.timeReservationIsMade = timeReservationIsMade;
	}

	public void setBroadcastId(int broadcastId) {
		this.broadcastId = broadcastId;
	}


	public ArrayList<Seat> getAllSeatsReserved() {
		return allSeatsReserved;
	}


	public void setAllSeatsReserved(ArrayList<Seat> allSeatsReserved) throws InvalidDataException {
		if(allSeatsReserved == null || allSeatsReserved.isEmpty()) {
		throw new InvalidDataException("Oops, error in setting reserved seats in reservation ");
		}
		this.allSeatsReserved = allSeatsReserved;
	}

	


	

}
