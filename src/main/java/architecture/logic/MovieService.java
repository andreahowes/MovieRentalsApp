package architecture.logic;

import architecture.data.MovieRepository;

import java.util.List;

public class MovieService {
    private MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository){
        this.movieRepository = movieRepository;
    }

    public void save(Movie movie){
        movieRepository.save(movie);
    }
    public List<Movie> getAllMovies(){
        return movieRepository.getAllMovies();
    }
    public Movie getMovieByTitle(String title){
        return movieRepository.getMovieByTitle(title);
    }

}
