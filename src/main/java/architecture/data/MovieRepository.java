package architecture.data;

import architecture.logic.Movie;

import java.util.List;

public interface MovieRepository {
    void save(Movie movie);
    List<Movie> getAllMovies();
    Movie getMovieByTitle(String title);
}
