package com.cloudBee.trainTicketMgt.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cloudBee.trainTicketMgt.model.SeatAllocation;
import com.cloudBee.trainTicketMgt.model.User;
import com.cloudBee.trainTicketMgt.repository.SeatAllocationRepository;
import com.cloudBee.trainTicketMgt.repository.UserRepository;
import com.cloudBee.trainTicketMgt.service.TrainServiceImpl;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

public class TrainServiceImplTest {
	
	@Mock
    private UserRepository userRepository;

    @Mock
    private SeatAllocationRepository seatAllocationRepository;

    @InjectMocks
    private TrainServiceImpl trainServiceImpl;
    
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }
    
    @Test
    void testPurchaseTicket() {
        User user = new User(1L, "John", "Doe", "john.doe@example.com");
        SeatAllocation seatAllocation = new SeatAllocation(1L, "A", 10, 20.0, user);

        when(userRepository.save(any(User.class))).thenReturn(user);
        when(seatAllocationRepository.save(any(SeatAllocation.class))).thenReturn(seatAllocation);

        SeatAllocation result = trainServiceImpl.purchaseTicket(user, "A", 20.0);

        assertNotNull(result);
        assertEquals("A", result.getSection());
        assertEquals(20.0, result.getPrice());
        assertNotNull(result.getUser());
        verify(userRepository, times(1)).save(user);
        verify(seatAllocationRepository, times(1)).save(any(SeatAllocation.class));
    }
    @Test
    void testGetSeatAllocationsBySection() {
        SeatAllocation seat1 = new SeatAllocation(1L, "A", 10, 20.0, new User());
        SeatAllocation seat2 = new SeatAllocation(2L, "A", 11, 20.0, new User());

        when(seatAllocationRepository.findBySection("A")).thenReturn(Arrays.asList(seat1, seat2));

        java.util.List<SeatAllocation> result = trainServiceImpl.getSeatAllocationsBySection("A");

        assertNotNull(result);
        assertEquals(2, result.size());
        verify(seatAllocationRepository, times(1)).findBySection("A");
    }
    @Test
    void testRemoveUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        trainServiceImpl.removeUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }
    @Test
    void RemoveUser() {
        Long userId = 1L;
        doNothing().when(userRepository).deleteById(userId);

        trainServiceImpl.removeUser(userId);

        verify(userRepository, times(1)).deleteById(userId);
    }


}
