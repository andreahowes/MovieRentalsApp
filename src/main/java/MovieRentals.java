import java.sql.*;
import java.util.Scanner;

import static java.sql.Types.NULL;

public class MovieRentals {
    public static final String COURSE_NAME = "java";

    public static void main(String[] args) throws SQLException {
        Connection connection = null;
        ResultSet resultSet = null;
        MovieRentals example = new MovieRentals();

        // create object of class so we can call non-static readDataBase() method
        try {
            connection = DriverManager.getConnection(createConnectionUrl());
            resultSet = connection.createStatement().executeQuery("select * from blockbuster.movies;");
            //example.writeResultSet(resultSet);
            //example.writeNewMovieChoice();
            //example.writeResultSet(resultSet);
            example.writeNewRental();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }

            if (connection != null) {
                connection.close();
            }
        }
    }

    private static String createConnectionUrl() {
        String host = "localhost";
        String dbName = "blockbuster";
        String user = "andrea";
        String password = "andrea";
        return "jdbc:mysql://" + host + "/" + dbName + "?" + "user=" + user + "&password=" + password + "&useSSL=false&allowPublicKeyRetrieval=true";
    }

    private void writeMetaData(ResultSet resultSet) throws SQLException {
        System.out.println("The columns in the table are: ");
        System.out.println("Table: " + resultSet.getMetaData().getTableName(1));
        for (int i = 1; i <= resultSet.getMetaData().getColumnCount(); i++) {
            System.out.println("Column " + i + " " + resultSet.getMetaData().getColumnName(i));
        }
    }

    private void writeResultSet(ResultSet resultSet) throws SQLException {
        while (resultSet.next()) {
            System.out.println("---------------------------------");
            System.out.println("Movie_Title: " + resultSet.getString("M_title"));
            System.out.println("Main_Actor: " + resultSet.getString("M_mainactor"));
            System.out.println("Year: " + resultSet.getInt("M_year"));
            System.out.println("Genre: " + resultSet.getString("M_genre"));
            System.out.println("---------------------------------");

        }
    }

    private void writeNewMovieChoice() throws SQLException {
        Connection connection = DriverManager.getConnection(createConnectionUrl());
        ;
        //Statement statement = null;
        PreparedStatement preparedStatement;
        //ResultSet resultSet = null;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Type in the movie title and press enter");
        String title = myScanner.nextLine();
        System.out.println("Type in the main actor and press enter");
        String mainactor = myScanner.nextLine();
        System.out.println("Type in the year of the movie and press enter");
        int year = myScanner.nextInt();
        System.out.println("Type in the genre and press enter");
        myScanner.nextLine();
        String genre = myScanner.nextLine();
        myScanner.nextLine();
        String query = "INSERT INTO movies VALUES (?, ?, ?, ?, ?)";

        // create the mysql insert prepared statement
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, title);
        preparedStatement.setString(2, mainactor);
        preparedStatement.setInt(3, year);
        preparedStatement.setString(4, genre);
        preparedStatement.setInt(5, NULL);

        // execute the prepared statement
        preparedStatement.execute();

    }

    private void writeNewRental() throws SQLException {
        Connection connection = DriverManager.getConnection(createConnectionUrl());
        //Statement statement = null;
        PreparedStatement preparedStatement;
        //ResultSet resultSet = null;
        Scanner myScanner = new Scanner(System.in);
        System.out.println("Type in the rental date and press enter");
        String rentalDate = myScanner.nextLine();
        System.out.println("Type in the return date and press enter");
        String returnDate = myScanner.nextLine();
        System.out.println("Type in the price and press enter");
        int price = myScanner.nextInt();
        myScanner.nextLine();
        System.out.println("Type in the name of the person and press enter");
        String person = myScanner.nextLine();
        System.out.println("Type in the movie title selected and press enter");
        String movie = myScanner.nextLine();
        myScanner.nextLine();

        //this is the portion of the method for creating the nameID
        ResultSet nameResult = null;
        PreparedStatement preparedStatementName;
        String nameQuery = "SELECT C_ID FROM rentals INNER JOIN customers ON rentals.R_C_ID=customers.C_ID WHERE C_lastname = ?";
        preparedStatementName = connection.prepareStatement(nameQuery);
        preparedStatementName.setString(1, person);
        Integer nameID = 0;
        nameResult = preparedStatementName.executeQuery();
        while (nameResult.next()) {
            nameID = nameResult.getInt("C_ID");
        }

        //this is the portion of the method for creating the movieID
        ResultSet movieResult = null;
        PreparedStatement preparedStatementMovie;
        String movieQuery = "SELECT M_ID FROM rentals INNER JOIN movies ON rentals.R_M_ID=movies.M_ID WHERE M_title = ? LIMIT 1";
        preparedStatementMovie = connection.prepareStatement(movieQuery);
        preparedStatementMovie.setString(1, movie);
        Integer movieID = 0;
        movieResult = preparedStatementMovie.executeQuery();
        while (movieResult.next()) {
            movieID = movieResult.getInt("M_ID");
        }

        String query = "INSERT INTO rentals VALUES (?, ?, ?, ?, ?, ?)";

        // create the mysql insert prepared statement
        preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, rentalDate);
        preparedStatement.setString(2, returnDate);
        preparedStatement.setInt(3, price);
        preparedStatement.setInt(4, NULL);
        preparedStatement.setInt(5, nameID);
        preparedStatement.setInt(6, movieID);

        // execute the prepared statement
        preparedStatement.execute();

    }
}

