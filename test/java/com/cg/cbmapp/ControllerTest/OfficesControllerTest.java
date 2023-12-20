package com.cg.cbmapp.ControllerTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.cbmapp.controller.OfficeControllers;
import com.cg.cbmapp.entities.Office;
import com.cg.cbmapp.service.OfficeServices;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OfficesControllerTest {

    @Mock
    private OfficeServices officeService;

    @InjectMocks
    private OfficeControllers officeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOffice() {
        Office office = new Office();
        when(officeService.addOffice(office)).thenReturn("Office created successfully");

        ResponseEntity<String> response = officeController.addOffice(office);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Office created successfully", response.getBody());
    }

    @Test
    void testGetOfficeByCode_found() {
        String officeCode = "ABC123";
        Office office = new Office();
        when(officeService.getOfficeByCode(officeCode)).thenReturn(office);

        ResponseEntity<Office> response = officeController.getOfficeByCode(officeCode);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(office, response.getBody());
    }

    @Test
    void testGetOfficeByCode_notFound() {
        String officeCode = "DEF456";
        when(officeService.getOfficeByCode(officeCode)).thenReturn(null);

        ResponseEntity<Office> response = officeController.getOfficeByCode(officeCode);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void testUpdateOfficeAddress_found() {
        String officeCode = "ABC123";
        Office updatedOffice = new Office();
        updatedOffice.setAddressLine1("New Address Line 1");
        updatedOffice.setAddressLine2("New Address Line 2");
        updatedOffice.setCity("New City");
        updatedOffice.setState("New State");
        updatedOffice.setCountry("New Country");
        updatedOffice.setPostalCode("New Postal Code");
        updatedOffice.setTerritory("New Territory");

        Office existingOffice = new Office();
        existingOffice.setAddressLine1("Old Address Line 1");
        existingOffice.setAddressLine2("Old Address Line 2");
        existingOffice.setCity("Old City");
        existingOffice.setState("Old State");
        existingOffice.setCountry("Old Country");
        existingOffice.setPostalCode("Old Postal Code");
        existingOffice.setTerritory("Old Territory");

        when(officeService.getOfficeByCode(officeCode)).thenReturn(existingOffice);

        ResponseEntity<String> response = officeController.updateOfficeAddress(officeCode, updatedOffice);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Office address updated successfully", response.getBody());
        assertEquals("New Address Line 1", existingOffice.getAddressLine1());
        assertEquals("New Address Line 2", existingOffice.getAddressLine2());
        assertEquals("New City", existingOffice.getCity());
        assertEquals("New State", existingOffice.getState());
        assertEquals("New Country", existingOffice.getCountry());
        assertEquals("New Postal Code", existingOffice.getPostalCode());
        assertEquals("New Territory", existingOffice.getTerritory());
        verify(officeService, times(1)).updateOffice(existingOffice);
    }

    @Test
    void testUpdateOfficeAddress_notFound() {
        String officeCode = "DEF456";
        Office updatedOffice = new Office();

        when(officeService.getOfficeByCode(officeCode)).thenReturn(null);

        ResponseEntity<String> response = officeController.updateOfficeAddress(officeCode, updatedOffice);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Office details not found", response.getBody());
        verify(officeService, never()).updateOffice(any(Office.class));
    }

    @Test
    void testGetOfficesByCities_found() {
        String city1 = "City1";
        String city2 = "City2";
        String cityN = "CityN";
        List<String> cities = Arrays.asList(city1, city2, cityN);
        List<Office> offices = Collections.singletonList(new Office());

        when(officeService.getOfficesByCities(cities)).thenReturn(offices);

        ResponseEntity<List<Office>> response = officeController.getOfficesByCities(city1, city2, cityN);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(offices, response.getBody());
    }

    @Test
    void testGetOfficesByCities_notFound() {
        String city1 = "City1";
        String city2 = "City2";
        String cityN = "CityN";
        List<String> cities = Arrays.asList(city1, city2, cityN);

        when(officeService.getOfficesByCities(cities)).thenReturn(Collections.emptyList());

        ResponseEntity<List<Office>> response = officeController.getOfficesByCities(city1, city2, cityN);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals(null, response.getBody());
    }

    @Test
    void testUpdateOfficePhoneNumber_found() {
        String officeCode = "ABC123";
        String phone = "1234567890";

        Office existingOffice = new Office();
        existingOffice.setPhone("Old Phone");

        when(officeService.getOfficeByCode(officeCode)).thenReturn(existingOffice);

        ResponseEntity<String> response = officeController.updateOfficePhoneNumber(officeCode, phone);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Office phone number updated successfully", response.getBody());
        assertEquals("1234567890", existingOffice.getPhone());
        verify(officeService, times(1)).updateOffice(existingOffice);
    }

    @Test
    void testUpdateOfficePhoneNumber_notFound() {
        String officeCode = "DEF456";
        String phone = "9876543210";

        when(officeService.getOfficeByCode(officeCode)).thenReturn(null);

        ResponseEntity<String> response = officeController.updateOfficePhoneNumber(officeCode, phone);

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Office details not found", response.getBody());
        verify(officeService, never()).updateOffice(any(Office.class));
    }
}
