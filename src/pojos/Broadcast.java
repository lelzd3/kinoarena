package pojos;

import java.time.LocalTime;

import com.sun.media.sound.InvalidDataException;


public class Broadcast {


    private int movieId;
    private LocalTime projectionTime;
    private int hallId;
    private int cinemaId;
	private int freePlaces;
	private static final int TOTAL_PLACES_IN_HALL=100;
	
	
	public Broadcast(int movieId, LocalTime projectionTime, int hallId, int cinemaId) {
		try {
			setProjectionTime(projectionTime);
		} catch (InvalidDataException e) {
			e.printStackTrace();
		}
		this.movieId = movieId;
		this.hallId = hallId;
		this.cinemaId = cinemaId;
		this.freePlaces = TOTAL_PLACES_IN_HALL;
	}


	
	//all setters:
	public void setMovieId(int movieId) throws InvalidDataException  {
	
		this.movieId = movieId;
		
	}
	

	public void setCinemaId(int cinemaId) throws InvalidDataException {

		this.cinemaId = cinemaId;
	}



	public void setFreePlaces(int freePlaces) throws InvalidDataException {
		if(freePlaces<0) {
			throw new InvalidDataException("Oops , exception in broadcast pojo");
		}
		this.freePlaces = freePlaces;
	}


	public void setProjectionTime(LocalTime projectionTime) throws InvalidDataException {
		if(projectionTime == null) {
			throw new InvalidDataException("Oops , exception in broadcast pojo");
		}
		this.projectionTime = projectionTime;
	}



	public void setHallId(int hallId) throws InvalidDataException {
		
		this.hallId = hallId;
	}


	//all getters:
	public int getCinemaId() {
		return cinemaId;
	}



	public int getMovieId() {
		return movieId;
	}



	public int getFreePlaces() {
		return freePlaces;
	}

	public LocalTime getProjectionTime() {
		return projectionTime;
	}

	public int getHallId() {
		return hallId;
	}
	
	
	
	

}
