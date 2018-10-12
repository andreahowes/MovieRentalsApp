package architecture.data;

import architecture.logic.Rental;

import java.util.ArrayList;
import java.util.List;

public class InMemoryRentalRepository implements RentalRepository{

    private List<Rental> rentalList = new ArrayList<>();

    @Override
    public void save(Rental rental) {
        rentalList.add(rental);
    }

    @Override
    public List<Rental> getAllRentals() {
        return rentalList;
    }

    @Override
    public int calculateTotalDaysRented(Rental rental) {
        return 0;
    }

}
