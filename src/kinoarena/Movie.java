package kinoarena;

import java.io.Serializable;
import java.time.LocalTime;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class Movie implements Serializable, Comparable<Movie> {
	
	private String name;
	private String info;
	private int rating; 
	private HashMap<Hall,LocalTime> broadcasts = new HashMap<>(); // check to remove it
	
	public Movie(String name,String info) {
		this.name = name;
		this.info = info;
		this.rating = 0;
	}
	
	public String getName() {
		return name;
	}
	public String getInfo() {
		return info;
	}
	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		if(this.rating == 0) {
			this.rating = rating;
		}else {
			this.rating = (this.rating + rating) / 2;
		}
	}
	
	
	public Map<Hall, LocalTime> getBroadcasts() {
		return Collections.unmodifiableMap(this.broadcasts);
	}
	

   void addBroadcast(Hall hall, LocalTime time) {
     	this.broadcasts.put(hall, time);
 	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Movie other = (Movie) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	

	@Override
	public int compareTo(Movie movie) {
		return this.name.compareTo(movie.name);
	}
	

}
