package dao;

import java.sql.SQLException;
import java.util.Collection;

import exceptions.InvalidDataException;
import pojos.Movie;

public interface IMovieDao {

	public void addMovie(Movie m) throws Exception;

	public void deleteMovie(Movie m) throws Exception;

	public Collection<Movie> getAllMovies() throws Exception;

	public Movie getMovieById(int id) throws Exception;

}
