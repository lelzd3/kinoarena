package dao;


import java.sql.SQLException;
import java.util.Collection;

import exceptions.InvalidDataException;
import pojos.Cinema;

public interface ICinemaDao {

	public void addCinema(Cinema c) throws Exception;

	public void deleteCinema(Cinema c) throws Exception;

	public Collection<Cinema> getAllCinemas() throws Exception;

	public Cinema getCinemaById(int id) throws Exception;

}
