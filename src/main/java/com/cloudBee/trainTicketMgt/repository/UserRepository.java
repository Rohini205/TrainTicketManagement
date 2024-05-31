package com.cloudBee.trainTicketMgt.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cloudBee.trainTicketMgt.model.User;

public interface UserRepository extends JpaRepository<User, Long>{

}
