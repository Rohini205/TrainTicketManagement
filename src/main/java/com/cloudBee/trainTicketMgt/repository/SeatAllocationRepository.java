package com.cloudBee.trainTicketMgt.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudBee.trainTicketMgt.model.SeatAllocation;

public interface SeatAllocationRepository extends JpaRepository<SeatAllocation, Long> {
	
	List<SeatAllocation> findBySection(String section);
}
