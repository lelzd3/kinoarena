package pojos;

import exceptions.InvalidDataException;

public class Movie  {
	
	private int id;
	private String title;
	private String description;
	private double rating;
	private double duration;
	private String file_location;
	// FIX ER DIAGRAM!!!!!!!
	
	public Movie(String title, String description, double rating, double duration) throws InvalidDataException {
		setTitle(title);
		setDescription(description);
		setRating(rating);
		setDuration(duration);
	}

	public Movie(int id,String title, String description, double rating, double duration) throws InvalidDataException {
		this(title,description,rating,duration);
		setId(id);
	}

	public Movie(int id,String title, String description, double rating, double duration,String file_location) throws InvalidDataException {
		this(id,title,description,rating,duration);
		this.file_location=file_location;
	}
	
	// getters
	public int getId() {
		return id;
	}
	
	public String getTitle() {
		return title;
	}
	
	public String getDescription() {
		return description;
	}
	
	public double getRating() {
		return rating;
	}
	
	public double getDuration() {
		return duration;
	}
	
	//setters
	public void setId(int id) {
		this.id = id;
	}
	
	public void setTitle(String title) throws InvalidDataException {
		if(!title.isEmpty()) {
			this.title = title;
		}
		else {
			throw new InvalidDataException("Invalid title");
		}
	}
	
	public void setDescription(String description) throws InvalidDataException {
		if(!description.isEmpty()) {
			this.description = description;
		}
		else{
			throw new InvalidDataException("Invalid description");
		}
	}
	
	public void setRating(double rating) throws InvalidDataException {
		if(rating >= 0) {
			this.rating = rating;
		}
		else {
			throw new InvalidDataException("Invalid rating");
		}
	}
	
	public void setDuration(double duration) throws InvalidDataException {
		if(duration > 0) {
			this.duration = duration;
		}
		else {
			throw new InvalidDataException("Invalid duration");
		}
	}
	
	

}
