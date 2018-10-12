package architecture.data;

import architecture.logic.Rental;

import java.util.List;

public interface RentalRepository {
    void save(Rental rental);

    List<Rental> getAllRentals();

    int calculateTotalDaysRented(Rental rental);

}
