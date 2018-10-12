package architecture.logic;
import architecture.data.RentalRepository;

import java.util.List;

public class RentalsService {
    private RentalRepository rentalRepository;

    public RentalsService(RentalRepository rentalRepository) {
        this.rentalRepository = rentalRepository;
    }


    public void save(Rental rental) {
        rentalRepository.save(rental);
    }

    public List<Rental> getAllRentals() {
        return rentalRepository.getAllRentals();
    }

    public int calculateTotalDaysRented(Rental rental) {
        return rentalRepository.calculateTotalDaysRented(rental);
    }

    public int calculateTotalPrice(int price, int days) {
        return price*days;
    }

}
