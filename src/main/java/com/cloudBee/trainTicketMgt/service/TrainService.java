package com.cloudBee.trainTicketMgt.service;

import java.util.List;

import com.cloudBee.trainTicketMgt.model.SeatAllocation;
import com.cloudBee.trainTicketMgt.model.User;

public interface TrainService {

	SeatAllocation purchaseTicket(User user, String section, double price);

	List<SeatAllocation> getSeatAllocationsBySection(String section);

	SeatAllocation modifyUserSeat(Long id, String newSection);

	void removeUser(Long id);
	

}
