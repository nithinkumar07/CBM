
package com.cg.cbmapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;

import org.springframework.transaction.annotation.Transactional;

//import com.cg.cbmapp.entities.Customers;

import com.cg.cbmapp.entities.Office;

import com.cg.cbmapp.exception.CustomException;

import com.cg.cbmapp.repository.OfficeRepository;

import jakarta.persistence.EntityManager;

@Service

@Transactional

public class OfficeServiceImpl implements OfficeServices {

	@Autowired

	private OfficeRepository officesRepository;

	private EntityManager entityManager;

	@Autowired

	public OfficeServiceImpl(OfficeRepository officesRepository, EntityManager entityManager) {

		this.officesRepository = officesRepository;

		this.entityManager = entityManager;

	}

	// -------------------9-----------------------------------------------

	@Override

	public String addOffice(Office office) {

		officesRepository.save(office);

		return "Office details added successfully";

	}

	// -------------------10----------------------------------------------

	@Override

	public Office getOfficeByCode(String officeCode) {

		Office orElse = officesRepository.findById(officeCode).orElse(null);

		if (orElse != null) {

			return orElse;

		}

		else {

			throw new CustomException("Office details not found");

		}

	}

//    ---------14---------

	@Override

	public List<Office> getOfficesByCities(List<String> cities) {

		return officesRepository.findByCityIn(cities);

	}

//--------11----------------

	@Override

	public List<Office> getAllOffices() {

		return officesRepository.findAll();

	}

	@Override

	public void updateOffice(Office office) {

		officesRepository.save(office);

	}

	public String updateOfficePhoneNumber(String officeCode, String phone) {

		Office office = officesRepository.findByOfficeCode(officeCode);

		if (office != null) {

			entityManager.createQuery("UPDATE Offices o SET o.phone = :phone WHERE o.officeCode = :officeCode")

					.setParameter("phone", phone)

					.setParameter("officeCode", officeCode)

					.executeUpdate();

			return "Office phone number updated successfully";

		} else {

			throw new CustomException("Office details not found");

		}

	}

}