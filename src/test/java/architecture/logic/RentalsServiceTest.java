package architecture.logic;

import architecture.data.DbRentalRepository;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class RentalsServiceTest {
    public static final int RENTAL_DATE = 12;
    public static final int RETURN_DATE = 22;
    public static final int DAYS_OF_RENTAL = RETURN_DATE - RENTAL_DATE;
    private RentalsService rentalsService;
    private DbRentalRepository rentalRepositoryMock;
    private Rental rental1;
    private Rental rental2;
    private int price = 2;


    @Before
    public void setUp() throws Exception {
        rental1 = new Rental();
        rental1.setId(1);
        rental1.setRentalDate(LocalDate.of(2018, 10, RENTAL_DATE));
        rental1.setReturnDate(LocalDate.of(2018, 10, RETURN_DATE));

        rental2 = new Rental();
        rental2.setId(2);

        rentalRepositoryMock = Mockito.mock(DbRentalRepository.class);
        rentalsService = new RentalsService(rentalRepositoryMock);

    }

    @Test
    public void whenSavingARental_shouldSaveIntoRepository() {
        rentalsService.save(rental1);
        verify(rentalRepositoryMock).save(rental1);
    }

    @Test
    public void whenGettingAllRentals_shouldReturnAllRentals() {
        when(rentalRepositoryMock.getAllRentals()).thenReturn(Arrays.asList(rental1, rental2));

        List<Rental> allRentals = rentalsService.getAllRentals();

        assertThat(allRentals).hasSize(2);
        assertThat(allRentals).contains(rental1, rental2);
    }

    @Test
    public void whenCalculatingTotalDaysRented_shouldReturnTotalDaysRented() {
        Rental rental = createRentalWith(RENTAL_DATE, RETURN_DATE);

        when(rentalRepositoryMock.calculateTotalDaysRented(rental)).thenReturn(DAYS_OF_RENTAL);

        int totalDaysRented = rentalsService.calculateTotalDaysRented(rental);

        assertThat(totalDaysRented).isEqualTo(DAYS_OF_RENTAL);

    }

    @Test
    public void whenCalculatingTotalPrice_shouldReturnTotalPrice() {
        double totalPriceResult = rentalsService.calculateTotalPrice(price, DAYS_OF_RENTAL);

        assertThat(totalPriceResult).isEqualTo(price * DAYS_OF_RENTAL);
    }

    private Rental createRentalWith(int startDay, int returnDay) {
        Rental rental1 = new Rental();
        rental1.setId(1);
        rental1.setRentalDate(LocalDate.of(2018, 10, startDay));
        rental1.setReturnDate(LocalDate.of(2018, 10, returnDay));

        return rental1;
    }
}