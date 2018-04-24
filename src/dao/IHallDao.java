package dao;

import java.util.Collection;

import pojos.Cinema;
import pojos.Hall;

public interface IHallDao {

	public void addHall(Hall h) throws Exception;

	public void deleteHall(Hall h) throws Exception;

	public Collection<Hall> getAllHalls() throws Exception;

	public Collection<Hall> getAllHallsForACinema(Cinema c) throws Exception;

	public Hall getHallById(int id) throws Exception;
	
}
