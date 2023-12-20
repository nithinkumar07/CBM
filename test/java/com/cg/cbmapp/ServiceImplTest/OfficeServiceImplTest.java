package com.cg.cbmapp.ServiceImplTest;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.cbmapp.entities.Office;

import com.cg.cbmapp.repository.OfficeRepository;
import com.cg.cbmapp.service.OfficeServiceImpl;

import java.util.Arrays;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class OfficeServiceImplTest {

    @Mock
    private OfficeRepository officesRepository;

    @InjectMocks
    private OfficeServiceImpl officeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddOffice_success() {
        Office office = new Office();

        assertDoesNotThrow(() -> officeService.addOffice(office));
        verify(officesRepository, times(1)).save(office);
    }

    @Test
    void testGetOfficeByCode_found() {
        String officeCode = "123";
        Office office = new Office();

        when(officesRepository.findById(officeCode)).thenReturn(Optional.of(office));

        Office result = officeService.getOfficeByCode(officeCode);

        assertEquals(office, result);
    }



    @Test
    void testGetOfficesByCities_found() {
        List<String> cities = Arrays.asList("City1", "City2");
        List<Office> findByCityIn = Arrays.asList(new Office(), new Office());

        when(officesRepository.findByCityIn(cities)).thenReturn(findByCityIn);

        List<Office> result = officeService.getOfficesByCities(cities);

        assertEquals(findByCityIn, result);
    }



    @Test
    void testGetAllOffices_found() {
        List<Office> findAll = Arrays.asList(new Office(), new Office());

        when(officesRepository.findAll()).thenReturn(findAll);

        List<Office> result = officeService.getAllOffices();

        assertEquals(findAll, result);
    }



    @Test
    void testUpdateOffice() {
        Office office = new Office();

        assertDoesNotThrow(() -> officeService.updateOffice(office));
        verify(officesRepository, times(1)).save(office);
    }


}
