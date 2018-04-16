package dao;

import java.util.Collection;

import pojos.Movie;

public interface IMovieDao {

	void addMovie(Movie m) throws Exception;

	void deleteMovie(Movie m) throws Exception;

	Collection<Movie> getAllMovies() throws Exception;

}
