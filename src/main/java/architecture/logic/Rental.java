package architecture.logic;

import java.time.LocalDate;

public class Rental {
    private LocalDate rentalDate;
    private LocalDate returnDate;
    private int totalPrice;
    private int id;
    private int customerID;
    private int movieID;
    private int totalDaysRented;

    public int getTotalDaysRented() { return totalDaysRented; }

    public void setTotalDaysRented(int totalDaysRented) { this.totalDaysRented = totalDaysRented; }

    public LocalDate getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(LocalDate rentalDate) {
        this.rentalDate = rentalDate;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getMovieID() {
        return movieID;
    }

    public void setMovieID(int movieID) {
        this.movieID = movieID;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "rentalDate=" + rentalDate +
                ", returnDate=" + returnDate +
                ", totalPrice=" + totalPrice +
                ", id=" + id +
                ", customerID=" + customerID +
                ", movieID=" + movieID +
                ", totalDaysRented=" + totalDaysRented +
                '}';
    }
}
