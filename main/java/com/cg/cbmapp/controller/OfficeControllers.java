package com.cg.cbmapp.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.cg.cbmapp.entities.Office;
import com.cg.cbmapp.service.OfficeServices;

import jakarta.validation.Valid;

@Validated
@RestController
@RequestMapping("/api/v1")
public class OfficeControllers {
	@Autowired
	private OfficeServices officesService;

	@Autowired
	public OfficeControllers(OfficeServices officesService) {
		this.officesService = officesService;
	}

	@PostMapping("/admin/office")
	public ResponseEntity<String> addOffice(@Valid @RequestBody Office office) {
		String result = officesService.addOffice(office);
		return ResponseEntity.status(HttpStatus.OK).body(result);

	}

	@GetMapping("/admin/office/{officeCode}")
	public ResponseEntity<Office> getOfficeByCode(@PathVariable("officeCode") String officeCode) {
		Office office = officesService.getOfficeByCode(officeCode);
		if (office != null) {
			return ResponseEntity.ok(office);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/admin/office/{officeCode}")
	public ResponseEntity<String> updateOfficeAddress(@Valid @PathVariable("officeCode") String officeCode,
			@RequestBody Office updatedOffice) {
		Office office = officesService.getOfficeByCode(officeCode);
		if (office != null) {
			office.setAddressLine1(updatedOffice.getAddressLine1());
			office.setAddressLine2(updatedOffice.getAddressLine2());
			office.setCity(updatedOffice.getCity());
			office.setState(updatedOffice.getState());
			office.setCountry(updatedOffice.getCountry());
			office.setPostalCode(updatedOffice.getPostalCode());
			office.setTerritory(updatedOffice.getTerritory());
			officesService.updateOffice(office);
			return ResponseEntity.status(HttpStatus.OK).body("Office address updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Office details not found");
		}
	}

	@GetMapping("/customer/office/{city1}/{city2}/{cityN}")
	public ResponseEntity<List<Office>> getOfficesByCities(@PathVariable("city1") String city1,
			@PathVariable("city2") String city2, @PathVariable("cityN") String cityN) {
		List<String> cities = Arrays.asList(city1, city2, cityN);
		List<Office> offices = officesService.getOfficesByCities(cities);
		if (!offices.isEmpty()) {
			return ResponseEntity.ok(offices);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
	}

	@PutMapping("/admin/office/{officeCode}/{phone}")
	public ResponseEntity<String> updateOfficePhoneNumber(@Valid @PathVariable("officeCode") String officeCode,
			@PathVariable("phone") String phone) {
		Office office = officesService.getOfficeByCode(officeCode);
		if (office != null) {
			office.setPhone(phone);
			officesService.updateOffice(office);
			return ResponseEntity.status(HttpStatus.OK).body("Office phone number updated successfully");
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Office details not found");
		}
	}

}