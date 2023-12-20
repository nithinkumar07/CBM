package com.cg.cbmapp.repository;


import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cg.cbmapp.entities.Customers;
import com.cg.cbmapp.entities.Payments;


public interface PaymentsRepository extends JpaRepository<Payments, String> {


	@Query("SELECT p FROM Payments p WHERE p.customer.customerNumber = :n")
    List<Payments> findPaymentsByCustomerNumber(@Param("n")int  customerNumber);
	
	@Query("SELECT p FROM Payments p WHERE p.customer.customerName = :cn")
    List<Payments> findPaymentsByCustomerName(@Param("cn") String customerName);

	@Query("SELECT p FROM Payments p WHERE p.customer.customerNumber = :cn")
	Payments findByCustomersBycustomernumber(@Param("cn") Integer customerNumber);
	
	@Query("SELECT p FROM Payments p WHERE p.paymentDate = :paymentDate")
    public List<Payments> findByPaymentDate(@Param("paymentDate") LocalDate paymentDate);
	 

	public Optional<Payments> findByCheckNumber(@Param("checkNumber") String checkNumber);


    @Query("SELECT p FROM Payments p WHERE p.paymentDate BETWEEN :startPayDate AND :endPayDate")
    public List<Payments> findByPaymentDateBetween(
        @Param("startPayDate") LocalDate startpaymentDate,
        @Param("endPayDate") LocalDate endpaymentDate
    );

    @Query("SELECT p FROM Payments p WHERE p.customer.customerNumber = :customerNumber AND p.checkNumber = :checkNumber")
    public Optional<Payments> findCustomersByCustomerNumberAndCheckNumber(
        @Param("customerNumber") Integer customerNumber,
        @Param("checkNumber") String checkNumber
    );
    public Boolean existsByCheckNumber(String checkNumber);
	
}
