package pojos;

import java.time.LocalDateTime;
import java.time.LocalTime;

import exceptions.InvalidDataException;



public class Broadcast {

	private int id;
	private int cinema_id;
    private int movie_id;
    private int hall_id;
    private LocalDateTime projectionTime;
	private int freePlaces;
	
	private static final int TOTAL_PLACES_IN_HALL=100;

	public Broadcast(int cinema_id, int movie_id, int hall_id, LocalDateTime projectionTime) throws InvalidDataException {
		setCinemaId(cinema_id);
		setMovieId(movie_id);
		setHallId(hall_id);
		setProjectionTime(projectionTime);
		this.freePlaces = TOTAL_PLACES_IN_HALL;
	}
	
	public Broadcast(int id, int cinema_id, int movie_id, int hall_id, LocalDateTime projectionTime) throws InvalidDataException {
		this(cinema_id, movie_id, hall_id, projectionTime);
		setId(id);
	}

	//all setters:
	public void setId(int id) {
		this.id = id;
	}
	
	public void setMovieId(int movie_id) throws InvalidDataException  {
		this.movie_id = movie_id;
	}
	
	public void setCinemaId(int cinema_id) throws InvalidDataException {
		this.cinema_id = cinema_id;
	}

	public void setFreePlaces(int freePlaces) throws InvalidDataException {
		if(freePlaces<0) {
			throw new InvalidDataException("Oops , exception in broadcast pojo");
		}
		this.freePlaces = freePlaces;
	}

	public void setProjectionTime(LocalDateTime projectionTime) throws InvalidDataException {
		if(projectionTime == null) {
			throw new InvalidDataException("Oops , exception in broadcast pojo");
		}
		this.projectionTime = projectionTime;
	}

	public void setHallId(int hall_id) throws InvalidDataException {
		
		this.hall_id = hall_id;
	}


	//all getters:
	public int getId() {
		return id;
	}
	
	public int getCinemaId() {
		return cinema_id;
	}

	public int getMovieId() {
		return movie_id;
	}

	public int getFreePlaces() {
		return freePlaces;
	}

	public LocalDateTime getProjectionTime() {
		return projectionTime;
	}

	public int getHallId() {
		return hall_id;
	}
	
	
	
	

}
