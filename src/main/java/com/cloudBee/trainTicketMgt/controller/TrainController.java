package com.cloudBee.trainTicketMgt.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloudBee.trainTicketMgt.model.SeatAllocation;
import com.cloudBee.trainTicketMgt.model.User;
import com.cloudBee.trainTicketMgt.service.TrainService;

@RestController
@RequestMapping("/api/train")
public class TrainController {
	
	//Enabled Logging to debbug class
	private static final Logger logger = LoggerFactory.getLogger(TrainController.class);
	
	//Injecting the trainService interface 
	@Autowired
    private TrainService trainService;

	
	// To add the User data into database
    @PostMapping("/purchase")
    public ResponseEntity<SeatAllocation> purchaseTicket(@RequestBody User user, @RequestParam String section) {
    	logger.info("Received request to purchase ticket for user: {} in section: {}", user, section);
         
    	double price = 20.0;
        SeatAllocation seatAllocation = trainService.purchaseTicket(user, section, price);
        
        logger.info("Ticket purchased successfully for user: {}. Seat Allocation: {}", user, seatAllocation);
        return ResponseEntity.ok(seatAllocation);
    }
    
    // This method will retrieve the data by using section
    @GetMapping("/seats")
    public ResponseEntity<List<SeatAllocation>> getSeatsBySection(@RequestParam String section) {
    	 logger.info("Received request to get seats for section: {}", section);
    	 
    	List<SeatAllocation> seatAllocations = trainService.getSeatAllocationsBySection(section);
    	logger.info("Returning seat allocations for section: {}", section);
    	return ResponseEntity.ok(seatAllocations);
    }
    
    //This method is used to remove the user using user id
    @DeleteMapping("/user/{id}")
    public ResponseEntity<Void> removeUser(@PathVariable Long id) {
    	logger.info("Received request to remove user with id: {}", id);
    	
        trainService.removeUser(id);
        logger.info("User with id: {} removed successfully", id);
        return ResponseEntity.noContent().build();
    }
    
    //To modify the user details based on the used id we are using this method
    @PutMapping("/user/{id}/modify")
    public ResponseEntity<SeatAllocation> modifyUserSeat(@PathVariable Long id, @RequestParam String newSection) {
    	logger.info("Received request to modify seat for user with id: {} to new section: {}", id, newSection);
    	
        SeatAllocation seatAllocation = trainService.modifyUserSeat(id, newSection);
        
        logger.info("Seat modified successfully for user with id: {}. New Seat Allocation: {}", id, seatAllocation);
      
        return ResponseEntity.ok(seatAllocation);
    }

}
