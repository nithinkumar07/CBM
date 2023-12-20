package com.cg.cbmapp.ControllerTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cg.cbmapp.controller.ProductLineController;
import com.cg.cbmapp.entities.ProductsLine;
import com.cg.cbmapp.service.ProductLineService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class ProductLineControllerTest {

    @Mock
    private ProductLineService productLineService;

    @InjectMocks
    private ProductLineController productLineController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testAddProductLine() {
        ProductsLine productLine = new ProductsLine();
        when(productLineService.addProductLine(productLine)).thenReturn(productLine);

        ResponseEntity<String> response = productLineController.addProductLine(productLine);

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertEquals("Product line details added successfully", response.getBody());
    }

    @Test
    void testUpdateTextDescription() {
        String productLine = "product_line";
        String textDescription = "new_text_description";

        productLineController.updateTextDescription(productLine, textDescription);

        verify(productLineService, times(1)).updateTextDescription(productLine, textDescription);
        // Add any additional assertions if necessary
    }

    @Test
    void testSearchProductLineByTextDescription_found() {
        String textDescription = "search_text";
        List<ProductsLine> productLines = new ArrayList<>();
        productLines.add(new ProductsLine());

        when(productLineService.searchProductLineByTextDescription(textDescription)).thenReturn(productLines);

        ResponseEntity<?> response = productLineController.searchProductLineByTextDescription(textDescription);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(productLines, response.getBody());
    }

}
