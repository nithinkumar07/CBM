package com.cg.cbmapp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;


import com.cg.cbmapp.entities.Office;

public interface OfficeRepository extends JpaRepository<Office, String>  {
	Optional<Office> findById(String officeCode);
	List<Office> findByCityIn(List<String> cities);
	
	Office findByOfficeCode(String officeCode);

	
	
	
}
