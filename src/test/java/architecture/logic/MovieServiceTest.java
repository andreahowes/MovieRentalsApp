package architecture.logic;

import architecture.data.DbMovieRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MovieServiceTest {
    public static final String LEGALLY_BLONDE = "Legally Blonde";
    private MovieService movieService;
    private DbMovieRepository movieRepositoryMock;
    private Movie movie1;
    private Movie movie2;

    @Before
    public void setUp() throws Exception {
        movie1= new Movie();
        movie2 = new Movie();
        movie1.setMovie_ID(1);
        movie2.setMovie_ID(2);
        movie1.setTitle(LEGALLY_BLONDE);
        movieRepositoryMock= Mockito.mock(DbMovieRepository.class);
        movieService = new MovieService(movieRepositoryMock);
    }

    @Test
    public void whenSavingAMovie_shouldSaveIntoRepository() {
        movieService.save(movie1);
        verify(movieRepositoryMock).save(movie1);
    }

    @Test
    public void whenGettingAllMovies_shouldReturnAllMovies() {
        when(movieRepositoryMock.getAllMovies()).thenReturn(Arrays.asList(movie1, movie2));

        List<Movie> allMovies = movieService.getAllMovies();

        assertThat(allMovies).hasSize(2);
        assertThat(allMovies).contains(movie1, movie2);
    }

    @Test
    public void whenGetMovieByTitle_shouldReturnMovie() {
        when(movieRepositoryMock.getMovieByTitle(LEGALLY_BLONDE)).thenReturn(movie1);

        Movie movieByTitle = movieService.getMovieByTitle(LEGALLY_BLONDE);

        assertThat(movieByTitle.getTitle()).isEqualTo(LEGALLY_BLONDE);

    }
}