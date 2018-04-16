package dao;


import java.util.Collection;

import pojos.Cinema;

public interface ICinemaDao {

	public void addCinema(Cinema c) throws Exception;

	public void deleteCinema(Cinema c) throws Exception;

	public Collection<Cinema> getAllCinemas() throws Exception;

}
