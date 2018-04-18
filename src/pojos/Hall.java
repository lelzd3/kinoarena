package pojos;

import exceptions.InvalidDataException;


public class Hall {

	private static int MAX_SEATS = 100;
	private int id;
	private int seats;
	private int cinema_id;
	
	Hall(int seats,int cinema_id) throws InvalidDataException{
		setSeats(seats);
		setCinema_id(cinema_id);
	}
	
	public Hall(int id,int seats,int cinema_id) throws InvalidDataException{
		this(seats,cinema_id);
		setId(id);
	}

	//getters
	public int getId() {
		return id;
	}

	public int getSeats() {
		return seats;
	}

	public int getCinema_id() {
		return cinema_id;
	}
	
	
	//setters
	public void setId(int id) {
		this.id = id;
	}

	public void setSeats(int seats) throws InvalidDataException {
		if(seats > 0 && seats <= MAX_SEATS) {
			this.seats = seats;
		}
		else {
			throw new InvalidDataException("Invalid seats");
		}
	}

	public void setCinema_id(int cinema_id) {
		this.cinema_id = cinema_id;
	}

	@Override
	public String toString() {
		return "Hall [id=" + id + ", seats=" + seats + ", cinema_id=" + cinema_id + "]";
	}
	

	
	
}
