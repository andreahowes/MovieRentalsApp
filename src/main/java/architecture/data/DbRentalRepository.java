package architecture.data;

import architecture.logic.Rental;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static java.sql.Types.NULL;

public class DbRentalRepository implements RentalRepository {
    @Override
    public void save(Rental rental) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        try {
            String query = "INSERT INTO rentals (R_rentalDate, R_returnDate, R_totalPrice, R_ID, R_C_ID, R_M_ID, R_totalDaysRented) VALUES (?, ?, ?, ?, ?, ?, ?)";
            connection = DriverManager.getConnection(ConnectionToDatabase.createConnectionUrl());
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setDate(1, Date.valueOf(rental.getRentalDate()));
            preparedStatement.setDate(2, Date.valueOf(rental.getReturnDate()));
            preparedStatement.setInt(3, rental.getTotalPrice());
            preparedStatement.setInt(4, NULL);
            preparedStatement.setInt(5, rental.getCustomerID());
            preparedStatement.setInt(6, rental.getMovieID());
            preparedStatement.setInt(7, rental.getTotalDaysRented());
            // execute the prepared statement
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
    public List<Rental> getAllRentals() {
        Connection connection = null;
        ResultSet resultSet = null;
        try {
            connection = DriverManager.getConnection(ConnectionToDatabase.createConnectionUrl());
            resultSet = connection.createStatement().executeQuery("select * from blockbuster.rentals;");

            return changeToRentalList(resultSet);

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

    @Override
    public int calculateTotalDaysRented(Rental rental) {
        Date rentalDate = Date.valueOf(rental.getRentalDate());
        Date returnDate = Date.valueOf(rental.getRentalDate());
        long difference = returnDate.getTime() - rentalDate.getTime();
        return (int) TimeUnit.DAYS.convert(difference, TimeUnit.MILLISECONDS);
    }


    private List<Rental> changeToRentalList(ResultSet resultSet) throws SQLException {
        List<Rental> rentalList = new ArrayList<>();
        while (resultSet.next()) {
            Rental rental = new Rental();
            rental.setId(resultSet.getInt("R_ID"));
            rental.setMovieID(resultSet.getInt("R_M_ID"));
            rental.setCustomerID(resultSet.getInt("R_C_ID"));
            rental.setRentalDate(resultSet.getDate("R_rentalDate").toLocalDate());
            rental.setReturnDate(resultSet.getDate("R_returnDate").toLocalDate());
            rental.setTotalDaysRented(resultSet.getInt("R_totalDaysRented"));
            rentalList.add(rental);
        }
        return rentalList;
    }
}
