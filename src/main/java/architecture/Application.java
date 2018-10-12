package architecture;
import architecture.data.DbMovieRepository;
import architecture.data.DbRentalRepository;
import architecture.logic.Movie;
import architecture.logic.MovieService;
import architecture.logic.Rental;
import architecture.logic.RentalsService;
import architecture.presentation.CustomerExperience;

import java.time.LocalDate;

public class Application {
    public static void main(String[] args) {
        LocalDate rentalDate = LocalDate.of(2020, 11, 24);
        LocalDate returnDate = LocalDate.of(2020, 11, 25);
        RentalsService rentalsService = new RentalsService(new DbRentalRepository());
        MovieService movieService = new MovieService(new DbMovieRepository());
        CustomerExperience customerExperience = new CustomerExperience();

        Rental rental1 = createRental(rentalDate, returnDate,2, movieService, "The Hobbit", rentalsService);
        System.out.println(rental1);

        rentalsService.save(rental1);
        System.out.println(rentalsService.getAllRentals());

        Movie movie1 = createMovie("Awesome Movie 4", "The second best actor", 2010, "the best genre", 1);
        movieService.save(movie1);
        System.out.println(movieService.getAllMovies());

    }

//the thing below will not be used once presentation is created (roi said)
    private static Rental createRental( LocalDate rentalDate, LocalDate returnDate, int customerId, MovieService movieService, String title, RentalsService rentalsService) {
        Rental rental = new Rental();
        rental.setTotalPrice(rentalsService.calculateTotalPrice(movieService.getMovieByTitle(title).getPricePerDay(), rental.getTotalDaysRented()));
        rental.setCustomerID(customerId);
        //rental.setId(id);
        rental.setMovieID(movieService.getMovieByTitle(title).getMovie_ID());
        rental.setRentalDate(rentalDate);
        rental.setReturnDate(returnDate);
        rental.setTotalDaysRented(rentalsService.calculateTotalDaysRented(rental));
        return rental;
    }
    private static Movie createMovie(String title, String actor, int year, String genre, int pricePerDay){
        Movie movie = new Movie();
        movie.setTitle(title);
        movie.setActor(actor);
        movie.setYear(year);
        movie.setGenre(genre);
        movie.setPricePerDay(pricePerDay);
        return movie;
    }

}
