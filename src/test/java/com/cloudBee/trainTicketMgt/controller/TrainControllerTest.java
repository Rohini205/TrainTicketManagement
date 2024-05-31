package com.cloudBee.trainTicketMgt.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import static org.hamcrest.Matchers.hasSize;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.cloudBee.trainTicketMgt.model.SeatAllocation;
import com.cloudBee.trainTicketMgt.model.User;
import com.cloudBee.trainTicketMgt.service.TrainService;
//This class is used to test the controller class

@WebMvcTest(TrainController.class)
public class TrainControllerTest {
	
	@Autowired
    private MockMvc mockMvc;

    @MockBean
    private TrainService trainService;

    
    @Test
    public void testPurchaseTicket() throws Exception {
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("john.doe@example.com");

        SeatAllocation seatAllocation = new SeatAllocation();
        seatAllocation.setUser(user);
        seatAllocation.setSection("A");
        seatAllocation.setPrice(20.0);
        seatAllocation.setSeatNumber(1);

        when(trainService.purchaseTicket(ArgumentMatchers.any(User.class), ArgumentMatchers.anyString(), ArgumentMatchers.anyDouble()))
                .thenReturn(seatAllocation);

        String userJson = "{\"firstName\":\"John\",\"lastName\":\"Doe\",\"email\":\"john.doe@example.com\"}";

        mockMvc.perform(post("/api/train/purchase")
                        .param("section", "A")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(userJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.section").value("A"))
                .andExpect(jsonPath("$.price").value(20.0))
                .andExpect(jsonPath("$.seatNumber").value(1))
                .andExpect(jsonPath("$.user.firstName").value("John"))
                .andExpect(jsonPath("$.user.lastName").value("Doe"))
                .andExpect(jsonPath("$.user.email").value("john.doe@example.com"));

        verify(trainService, times(1)).purchaseTicket( any(User.class), anyString(), anyDouble());
    }
    @Test
    public void testGetSeatsBySection() throws Exception {
        SeatAllocation seatAllocation = new SeatAllocation();
        seatAllocation.setSection("A");
        seatAllocation.setPrice(20.0);
        seatAllocation.setSeatNumber(1);

        when(trainService.getSeatAllocationsBySection("A")).thenReturn(Collections.singletonList(seatAllocation));

        mockMvc.perform(get("/api/train/seats").param("section", "A"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].section").value("A"))
                .andExpect(jsonPath("$[0].price").value(20.0))
                .andExpect(jsonPath("$[0].seatNumber").value(1));

        verify(trainService, times(1)).getSeatAllocationsBySection("A");
    }
    @Test
    public void testRemoveUser() throws Exception {
        doNothing().when(trainService).removeUser(1L);

        mockMvc.perform(delete("/api/train/user/1"))
                .andExpect(status().isNoContent());

        verify(trainService, times(1)).removeUser(1L);
    }
    @Test
    public void testModifyUserSeat() throws Exception {
        SeatAllocation seatAllocation = new SeatAllocation();
        seatAllocation.setSection("B");
        seatAllocation.setSeatNumber(2);
        seatAllocation.setPrice(20.0);

        when(trainService.modifyUserSeat(1L, "B")).thenReturn(seatAllocation);

        mockMvc.perform(put("/api/train/user/1/modify").param("newSection", "B"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.section").value("B"))
                .andExpect(jsonPath("$.seatNumber").value(2))
                .andExpect(jsonPath("$.price").value(20.0));

        verify(trainService, times(1)).modifyUserSeat(1L, "B");
    }
}
