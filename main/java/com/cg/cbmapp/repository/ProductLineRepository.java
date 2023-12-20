package com.cg.cbmapp.repository;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cg.cbmapp.entities.ProductsLine;

public interface ProductLineRepository extends JpaRepository<ProductsLine, String> {

	//---------------------POST(89)--------------------------------------------------
	
	@Modifying
    @Query("UPDATE ProductsLine pl SET pl.textDescription = :textDescription WHERE pl.productLine = :productLine")
    void updateTextDescriptionByProductLine(String productLine, String textDescription);
	
	//---------------------PUT(90)-----------------------------------------------
	
	
	@Modifying
    @Query("UPDATE ProductsLine p SET p.textDescription = ?2 WHERE p.productLine = ?1")
    void updateTextDescription(String productLine, String textDescription);
	
	
	//--------------------------GET(91)-----------------
	
	
	List<ProductsLine> findByTextDescription(String textDescription);
	
	
	
	
		
	}

