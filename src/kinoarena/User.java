package kinoarena;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

import controller.UserManager;
import dao.AdminDao;
import dao.UserDao;
import exceptions.InvalidDataExeption;
import exceptions.InvalidUserExeption;

public class User {
	
	protected int id;
	protected String firstname; 
	protected String lastname; 
	protected String username;
	protected String password;
	protected String email;
	protected String phone;
	public boolean activeAccount;
	private Set<Movie> favoriteList = new HashSet<Movie>();
	private List<Reservation> reservations = new ArrayList<>();
	private Cinema cinema;
	
	public User(String username,String password,String firstname,String lastname,String email, String phoneNumber) throws InvalidUserExeption {
		setUsername(username);
		setPassword(password);
		setFirstName(firstname);
		setLastName(lastname);
		setEmail(email);
		setPhone(phoneNumber);
	}
	
	public User(int id,String username,String password, String firstname,String lastname, String email, String phoneNumber) throws InvalidUserExeption {
		this(username, password, firstname, lastname, email, phoneNumber);
		setId(id);
	}
	
	
	
	// getters:
	public String getPassword() {
		return password;
	}

	public String getUsername() {
		return username;
	}
	
	public boolean getAccountStatus() {
		return this.activeAccount;
	}
	
	public Cinema getCinema() {
		return cinema;
	}
	
	public Set<Movie> getFavoriteList() {
		return Collections.unmodifiableSet(favoriteList);
	}
	
	public List<Reservation> getReservations() {
		return Collections.unmodifiableList(reservations);
	}
	
	// setters:
	public void setId(int id) {
		this.id = id;
	}
	
	public void setUsername(String username) throws exceptions.InvalidUserExeption {
		try {
			if(UserManager.verifyUsername(username)){
				this.username = username;
			}
		} catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}

	public void setPassword(String password) throws InvalidUserExeption {
		try {
			if(UserManager.verifyPassword(password)){
				this.password = password;
			}
		} catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}
	
	public void setFirstName(String name) throws InvalidUserExeption {
		try {
			if(UserManager.validation(name)) {
				this.firstname = name;
			}
		}
		catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}

	
	public void setLastName(String name) throws InvalidUserExeption {
		try {
			if(UserManager.validation(name)) {
				this.lastname = name;
			}
		}
		catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}
	
	public void setEmail(String email) throws InvalidUserExeption{
		try {
			if(UserManager.verifyEmail(email)){
				this.email = email;
			}
		}
		catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
		
	}

	public void setPhone(String phone) throws InvalidUserExeption{
		try {
			if(UserManager.verifyPhoneNumber(phone)){
				this.phone = phone;
			}
		} 
		catch (InvalidDataExeption e) {
			throw new InvalidUserExeption(e.getMessage(), e);
		}
	}
	
	public void addMovieToFavoriteList(User u, Movie m) {
		if(m != null){
			u.favoriteList.add(m);
		}
	}
	
	public void rateMovie(Movie m, int rate) {
		m.setRating(rate);
	}
	
	public void reciveTicket(Reservation r) {
		this.reservations.add(r);
	}
	
	public void removeReservation(Reservation r) {
		// TODO
	}

	public String getEmail() {
		return email;
	}
}
