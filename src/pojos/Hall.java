package pojos;

import java.time.LocalTime;
import java.util.HashMap;


public class Hall {
	
	private static Cinema cinema;
	private int number;
	private int sits;
	private HashMap<Movie,LocalTime> movies = new HashMap<>(); // can be replaced with list of Broadcast objects
	
	public Hall(int number,Cinema cinema) {
		this.number = number;
		this.sits = 50;
		Hall.cinema = cinema;
	}
	
	public int getNumber() {
		return number;
	}
	
	public int getSits() {
		return sits;
	}
	
	void addMovie(Movie movie,LocalTime time) {
		this.movies.put(movie, time);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + number;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hall other = (Hall) obj;
		if (number != other.number)
			return false;
		return true;
	}

}
