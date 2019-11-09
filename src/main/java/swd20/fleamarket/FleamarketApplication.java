package swd20.fleamarket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

import swd20.fleamarket.domain.Customer;
import swd20.fleamarket.domain.CustomerRepository;
import swd20.fleamarket.domain.Reservation;
import swd20.fleamarket.domain.ReservationRepository;
import swd20.fleamarket.domain.User;
import swd20.fleamarket.domain.UserRepository;


@SpringBootApplication
public class FleamarketApplication {
		
		private static final Logger log = LoggerFactory.getLogger(FleamarketApplication.class);

		public static void main(String[] args) {
			SpringApplication.run(FleamarketApplication.class, args);
		}	

		@Bean	
		public CommandLineRunner reservationDemo(ReservationRepository reservationRepository, CustomerRepository customerRepository, UserRepository userRepository) { 
			return (args) -> {
				log.info("save a couple of reservations");
				
				customerRepository.save(new Customer("Maija Mahtava", "1 (844) 727-5967"));
				customerRepository.save(new Customer("Juha Juntti", "1 (833) 373-8937"));
				customerRepository.save(new Customer("Elisa Eloton", "1 (855) 646-2481"));
				customerRepository.save(new Customer("Anna Avuton", "1 (888) 285-6361"));
				
				reservationRepository.save(new Reservation("table 1", 45.00, "04.12.2019-30.12.2019", customerRepository.findByName("Maija Mahtava").get(0)));
				reservationRepository.save(new Reservation("table 13", 30.00, "10.12.2019-12.01.2020", customerRepository.findByName("Juha Juntti").get(0)));
				reservationRepository.save(new Reservation("table 23", 55.00, "10.01-2019-20.03.2020", customerRepository.findByName("Elisa Eloton").get(0)));
				reservationRepository.save(new Reservation("table 33", 75.00, "22.02.2020-30.04.2020", customerRepository.findByName("Anna Avuton").get(0)));
				
		// create a new user: user1 / salasana: maija / user2 / salasana: juha / user3 / salasana: elisa / user4 / salasana: anna / user5 / salasana: admin 
				
				User user1 = new User("maija", "$2a$04$Ih3AXEv3f/BBmC7jsmmlhukaeOvVJEkG7UlfPFmKYL/N2UiVO84yu", "maija.mahtava@hotmail.com", "MAIJA");
				User user2 = new User("juha", "$2a$04$Tb/xmQCPOA0hF73DD1Etne6Ff8G6BcLp4Q4TggU7jL0EItjVE2RAC", "juha.juntti@gmail.com","JUHA");
				User user3 = new User("elisa", "$2a$04$ekuv.o4OPvaTcU3aGiqgUOeOilVQWn54I/XpsGjCVuuYVznkkH/DS", "elisa.eloton@gmail.com","ELISA");
				User user4 = new User("anna", "$2a$04$pY9UwV1fmIFbm8n4Fr23dOoziSl5nVSeCbF04HWKz1mwFPqcGFjra", "anna.avuton@hotmail.com","ANNA");
				User user5 = new User("admin", "$2a$04$EbfoXG7FV86i3/r2erxxn.L6Z3UE8OunZrO3ovilEiLTb1FEj63wm", "admin@fleamarket.com","ADMIN");
				
				userRepository.save(user1);
				userRepository.save(user2);
				userRepository.save(user3);
				userRepository.save(user4);
				userRepository.save(user5);
				
		// fetch all reservations
				
				log.info("fetch all reservations");
				for (Reservation reservation : reservationRepository.findAll()) {
					log.info(reservation.toString());
				}

			};
		}
	}