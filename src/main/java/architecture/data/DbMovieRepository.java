package architecture.data;

import architecture.logic.Movie;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DbMovieRepository implements MovieRepository {

    @Override
    public void save(Movie movie) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;

        try {
            connection = DriverManager.getConnection(ConnectionToDatabase.createConnectionUrl());

            String query = "INSERT INTO movies (M_title, M_mainactor, M_year, M_genre, M_ID, M_pricePerDay) VALUES (?, ?, ?, ?, ?, ?)";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, movie.getTitle());
            preparedStatement.setString(2, movie.getActor());
            preparedStatement.setInt(3, movie.getYear());
            preparedStatement.setString(4, movie.getGenre());
            preparedStatement.setInt(5, movie.getMovie_ID());
            preparedStatement.setInt(6, movie.getPricePerDay());
            preparedStatement.execute();
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was an error");
        } finally {
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public List<Movie> getAllMovies() {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(ConnectionToDatabase.createConnectionUrl());
            resultSet = connection.createStatement().executeQuery("select * from blockbuster.movies;");

            return changeToMovieList(resultSet);

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("There was an error");
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return Collections.emptyList();
    }

    private List<Movie> changeToMovieList(ResultSet resultSet) throws SQLException {
        List<Movie> movieList = new ArrayList<>();
        while (resultSet.next()) {
            Movie movie = new Movie();
            movie.setTitle(resultSet.getString("M_title"));
            movie.setActor(resultSet.getString("M_mainactor"));
            movie.setYear(resultSet.getInt("M_year"));
            movie.setGenre(resultSet.getString("M_genre"));
            movie.setMovie_ID(resultSet.getInt("M_ID"));
            movie.setPricePerDay(resultSet.getInt("M_pricePerDay"));
            movieList.add(movie);
        }
        return movieList;
    }

    @Override
    public Movie getMovieByTitle(String title) {

        Connection connection = null;
        try {
            connection = DriverManager.getConnection(ConnectionToDatabase.createConnectionUrl());

            ResultSet movieResult = null;
            PreparedStatement preparedStatementMovie;
            String movieQuery = "SELECT * FROM movies WHERE M_title = ?";
            preparedStatementMovie = connection.prepareStatement(movieQuery);
            preparedStatementMovie.setString(1, title);
            movieResult = preparedStatementMovie.executeQuery();
            Movie movie = new Movie();
            while (movieResult.next()) {
                movie.setMovie_ID(movieResult.getInt("M_ID"));
                movie.setGenre(movieResult.getString("M_genre"));
                movie.setYear(movieResult.getInt("M_year"));
                movie.setActor(movieResult.getString("M_mainactor"));
                movie.setTitle(movieResult.getString("M_title"));
                movie.setPricePerDay(movieResult.getInt("M_pricePerDay"));
            }
            return movie;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}



