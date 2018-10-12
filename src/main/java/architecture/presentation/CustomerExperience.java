package architecture.presentation;


import architecture.data.DbMovieRepository;
import architecture.data.MovieRepository;
import architecture.logic.MovieService;

import java.util.Scanner;

public class CustomerExperience {
    MovieService movieService = new MovieService(new DbMovieRepository());
    public void startMenu(){
        System.out.println("Hello Blockbuster Customer.");
        System.out.println("Press 1 to browse movies.");
        System.out.println("Press 2 to rent a movie.");
        System.out.println("Press 3 to return a movie.");
        System.out.println("Or press 0 to exit.");
        Scanner scanner = new Scanner(System.in);
        int userInput = scanner.nextInt();
        switch(userInput){
            case 1:
                browseMovies();
                break;
            case 2:
                rentAMovie();
                break;
            case 3:
                returnAMovie();
                break;
            case 0:
                System.exit(0);
            default:
                System.out.println("Invalid response: Please enter 1, 2, or 3");
        }
    }

    private void rentAMovie(){}
    private void browseMovies(){
        System.out.println("You chose to browse the movies. Here are the available choices");
        movieService.getAllMovies();
    }
    private void returnAMovie(){}



}
