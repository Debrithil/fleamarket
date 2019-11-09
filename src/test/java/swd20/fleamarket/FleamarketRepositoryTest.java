package swd20.fleamarket;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import swd20.fleamarket.domain.CustomerRepository;
import swd20.fleamarket.domain.Reservation;
import swd20.fleamarket.domain.ReservationRepository;
import swd20.fleamarket.domain.User;
import swd20.fleamarket.domain.UserRepository;

@RunWith(SpringRunner.class)
@DataJpaTest

public class FleamarketRepositoryTest {
    
    // JPA-TESTING
    
    @Autowired
    private ReservationRepository reservationRepository;
    
    @Test
    public void findByReservationPlaceShouldReturnReservation() {
        List<Reservation> reservations = reservationRepository.findByPlace("table 13");
        
        assertThat(reservations).hasSize(1);
        assertThat(reservations.get(0).getPlace()).isEqualTo("table 13");

    }
    
    @Autowired
    private CustomerRepository customerRepository;
    
    @Test
    public void createNewReservation() {
    	Reservation reservation = new Reservation("table 23", 55.00, "10.01-2019-20.03.2020", customerRepository.findByName("Elisa Eloton").get(0)); 
    	reservationRepository.save(reservation);
    	assertThat(reservation.getId()).isNotNull();
    	assertThat(reservation.getPlace());
    	
    }
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void createNewUser() {
    	User user6 = new User("matti", "$2a$04$Ih3AXEv3f/BBmC7jsmmlhukaeOvVJEkG7UlfPFmKYL/N2UiVO84yu", "matti.mukava@hotmail.com", "MATTI");
    	userRepository.save(user6);
    	assertThat(user6.getId()).isNotNull();
    	assertThat(user6.getPasswordHash());
    	assertThat(user6.getEmail());
    }

}