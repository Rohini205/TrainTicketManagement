package com.cloudBee.trainTicketMgt.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cloudBee.trainTicketMgt.model.SeatAllocation;
import com.cloudBee.trainTicketMgt.model.User;
import com.cloudBee.trainTicketMgt.repository.SeatAllocationRepository;
import com.cloudBee.trainTicketMgt.repository.UserRepository;

@Service
public class TrainServiceImpl implements TrainService{
	
	//Injecting the repository class into service class to connect with user data
	@Autowired
    private UserRepository userRepository;

	//Injecting the repository class into service class to connect with SeatAllocation data
    @Autowired
    private SeatAllocationRepository seatAllocationRepository;

    //Below method is used to write the logic for save data during purchasing ticket
    public SeatAllocation purchaseTicket(User user, String section, double price) {
        userRepository.save(user);

        SeatAllocation seatAllocation = new SeatAllocation();
        seatAllocation.setUser(user);
        seatAllocation.setSection(section);
        seatAllocation.setPrice(price);
        seatAllocation.setSeatNumber(generateSeatNumber(section));

        return seatAllocationRepository.save(seatAllocation);
    }

    //Randomly it will generate the seatnumber
    private int generateSeatNumber(String section) {
        // Implement seat number generation logic here
        return (int) (Math.random() * 50) + 1;
    }

    
    public List<SeatAllocation> getSeatAllocationsBySection(String section) {
        return seatAllocationRepository.findBySection(section);
    }

    
    public void removeUser(Long userId) {
        userRepository.deleteById(userId);
    }

    
    public SeatAllocation modifyUserSeat(Long userId, String newSection) {
        SeatAllocation seatAllocation = seatAllocationRepository.findById(userId).orElseThrow();
        seatAllocation.setSection(newSection);
        seatAllocation.setSeatNumber(generateSeatNumber(newSection));
        return seatAllocationRepository.save(seatAllocation);
    }

}
