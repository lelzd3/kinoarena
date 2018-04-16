package pojos;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import exceptions.InvalidDataException;


public class Cinema {
	

	private int id;
	private String name;
	private String address;
	
	Cinema(String name,String address) throws InvalidDataException{
		setName(name);
		setAddress(address);
	}
	
	
	
	public Cinema(int id, String name, String address) throws InvalidDataException {
		this(name,address);
		setId(id);
	}



	//getters
	public int getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getAddress() {
		return address;
	}
	
	//setters
	public void setId(int id) {
		this.id = id;
	}
	public void setName(String name) throws InvalidDataException {
		if(!name.isEmpty()) {
			this.name = name;
		}
		else {
			throw new InvalidDataException("Invalid name");
		}
	}
	public void setAddress(String address) throws InvalidDataException {
		if(!address.isEmpty()) {
			this.address = address;
		}
		else {
			throw new InvalidDataException("Invalid address");
		}
	}

	
	
//	public void setTheBroadcasts() {
//		int i = 0;
//		ArrayList<Hall> halls = new ArrayList<>(this.halls);
//			
//		//Get all the movies and shuffle them so we can get a random movie every week
//		ArrayList<Movie> movies = new ArrayList<>(this.allMovies);
//		Collections.shuffle(movies);
//		for (Movie m : movies) {
//			this.broadcasts.add(new Broadcast(m, LocalTime.of(10, 00), halls.get(i)));
//			this.broadcasts.add(new Broadcast(m, LocalTime.of(14, 00), halls.get(i)));
//			this.broadcasts.add(new Broadcast(m, LocalTime.of(19, 00), halls.get(i)));
//			this.broadcasts.add(new Broadcast(m, LocalTime.of(22, 30), halls.get(i)));
//			if(Cinema.MAX_MOVIE_FOR_THE_WEEK == i) {
//				break;
//			}
//			i++;
//		}
//	}
//
//		public void printBroadcasts() {
//			System.out.println("#########Broadcast for the week in cinema " + this.name + "########");
//			System.out.println();
//			for (Broadcast b : this.broadcasts) {
//				System.out.println(b.getMovie().getName() + " from " + b.getProjectionTime() + " in hall: " + b.getProjectionHall().getNumber());
//			}
//			System.out.println();
//			System.out.println();
//		}
//
//		public Reservation checkReservation(Broadcast b, int places) {
//			// check is there enough places for this broadcast
//			if (b.getPlaces() < places) {
//				//  if free places are not enough --> message
//				return null;
//			}
//			// if there are enough places --> return reservation for this broadcast with places
//			return new Reservation(b, places);
//		}
//
//		public void addMovie(Movie movie) {
//			boolean containsMovie = this.allMovies.contains(movie);
//			if (movie != null) {
//				this.allMovies.add(movie);
//			}
//			if(!containsMovie) {
//				addMovieToArchive(movie);	
//			}
//		}
//
//		private void addMovieToArchive(Movie m) {
//			try {
//				//Add movies to file
//				File movieFile = new File(Cinema.PATH_FOR_MOVIES);
//				if(!movieFile.exists()) {
//					movieFile.createNewFile();
//				}
//				FileOutputStream fos = new FileOutputStream(movieFile);
//				ObjectOutputStream oos = new ObjectOutputStream(fos);
//				oos.writeObject(this.allMovies);
//				oos.flush();
//				oos.close();
//			
//			}catch (IOException e) {
//				System.out.println("Somethin is wrong with the movie arhive file on output!!");
//			}
//		}
//		
//		public void removeMovie(Movie movie) {
//			if (movie != null) {
//				this.allMovies.remove(movie);
//			}
//		}
//
//		public Set<Broadcast> getBroadcasts() {
//			return Collections.unmodifiableSet(this.broadcasts);
//		}
//
//		public static boolean registrationCheck(String inputUsername, String inputPassword) {
//			if(users.containsKey(inputUsername)) {
//				if(users.get(inputUsername).getPassword().equals(inputPassword)) {
//					return true;
//				}
//			}
//			return false;
//		}
//
//		public static void registrateUser(User user) {
//			users.put(user.getUsername(), user);
//		}
//
//		public String getName() {
//			return name;
//		}
//		
//		
//
//		public void removeUser(User u) {
//			System.out.println("User " + u.getUsername() + " will be removed!");
//			users.remove(u.getUsername());
//		}
//		
//		public void showAllMovies(){
//			for(Movie m : allMovies) {
//				System.out.println(m);
//			}
//		}
//
//		public static User getUser(String username) {
//			return users.get(username);
//		}

}
