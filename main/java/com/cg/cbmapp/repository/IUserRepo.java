package com.cg.cbmapp.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cg.cbmapp.entities.*;

public interface IUserRepo extends JpaRepository<User, Integer> {
	
	Optional<User> findByEmail(String email);

}
