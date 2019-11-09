package swd20.fleamarket.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface ReservationRepository extends CrudRepository<Reservation, Long> {
	
	List<Reservation> findByPlace(String place);
	
	List<Reservation> findByPlaceContainingOrDateContainingAllIgnoreCase(String place, String date);

}
