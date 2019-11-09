package swd20.fleamarket.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import swd20.fleamarket.domain.Customer;
import swd20.fleamarket.domain.CustomerRepository;
import swd20.fleamarket.domain.Reservation;
import swd20.fleamarket.domain.ReservationRepository;

@Controller
public class FleamarketController {
	
	@Autowired
	ReservationRepository reservationRepository;
	
	@Autowired
	CustomerRepository customerRepository;
	
	// Homepage
	@RequestMapping (value="/")
	public String home() {
		return "homepage";
	}
	
	// Login
    @RequestMapping(value="/login")
    public String login() {	
        return "login";
    }	
	
	// Contact
    @RequestMapping(value="/contact")
    public String contact() {	
        return "contact";
    }	
	
	// reservation list
	@RequestMapping(value = "/reservationlist", method = RequestMethod.GET)
	public String getReservations(Model model) {
			List<Reservation> reservations =  (List<Reservation>) reservationRepository.findAll();
			model.addAttribute("reservations", reservations);
			return "reservationlist";
	}
	
	// RESTful reservation list
    @RequestMapping(value="/Apireservations", method = RequestMethod.GET)
    public @ResponseBody List<Reservation> reservationsListRest() {	
        return (List<Reservation>) reservationRepository.findAll();
    }    
    
	// RESTful reservations find by {id}
    @RequestMapping(value="/Apireservations/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Reservation> findReservationRest(@PathVariable("id") Long reservationId) {	
    	return reservationRepository.findById(reservationId);
    }     
    
    // RESTful save a new reservation
	@RequestMapping (value="/reservations", method = RequestMethod.POST)
	public @ResponseBody Reservation saveNewReservationRest(@RequestBody Reservation reservation){
		return reservationRepository.save(reservation);
	}
    
	// Search
    @RequestMapping(value="/search", method = RequestMethod.POST)
    public String searchReservation(@RequestParam(value="searchInformation") String searchInformation, Model model) {	
    	List<Reservation> reservations = reservationRepository.findByPlaceContainingOrDateContainingAllIgnoreCase(searchInformation, searchInformation);
    	model.addAttribute("reservations", reservations);
    	return "reservationlist";
    }

	// add a new reservation
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/newreservation", method = RequestMethod.GET)
	public String getNewReservationForm(Model model) {
		model.addAttribute("reservation", new Reservation());
		model.addAttribute("customers", customerRepository.findAll());
		return "reservationform";
	}

	// save a new reservation
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/savereservation", method = RequestMethod.POST)
	public String saveReservation(@ModelAttribute Reservation reservation, Customer customer) {
		reservationRepository.save(reservation);
		customerRepository.save(customer);
		return "redirect:/reservationlist";
	}
	
	// edit a reservation
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/editreservation/{id}", method = RequestMethod.GET)
	public String editReservation(@PathVariable("id") Long reservationId, Model model) {
		model.addAttribute("reservation", reservationRepository.findById(reservationId));
		model.addAttribute("customers", customerRepository.findAll());
		return "editreservation";
	}
	
	// delete a reservation
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/deletereservation/{id}", method = RequestMethod.GET)
	public String deleteReservation(@PathVariable("id") Long reservationId) {
		reservationRepository.deleteById(reservationId);
		return "redirect:../reservationlist";
	}
	
	// add a new customer
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/newcustomer", method = RequestMethod.GET)
	public String getNewCustomerForm(Model model) {
		model.addAttribute("customer", new Customer());
		return "newcustomer";
	}
	
	// save a new customer
	@PreAuthorize("hasAuthority('ADMIN')")
	@RequestMapping(value = "/savecustomer", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute Customer customer) {
		customerRepository.save(customer);
		return "redirect:/reservationlist";
	}
	
}