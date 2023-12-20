package com.cg.cbmapp.ServiceImplTest;



import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.cg.cbmapp.entities.ProductsLine;
import com.cg.cbmapp.repository.ProductLineRepository;
import com.cg.cbmapp.service.ProductLineServiceImpl;

@ExtendWith(MockitoExtension.class)
public class ProductLineServiceImplTest {

    @Mock
    private ProductLineRepository productLineRepository;

    @InjectMocks
    private ProductLineServiceImpl productLineService;

    @Test
    public void testAddProductLine() {
        // Prepare
        ProductsLine productLine = new ProductsLine();
        when(productLineRepository.save(productLine)).thenReturn(productLine);

        // Act
        ProductsLine savedProductLine = productLineService.addProductLine(productLine);

        // Assert
        assertNotNull(savedProductLine);
        verify(productLineRepository, times(1)).save(productLine);
    }

    @Test
    public void testUpdateTextDescription() {
        // Prepare
        String productLine = "Product Line A";
        String textDescription = "New Text Description";

        // Act
        productLineService.updateTextDescription(productLine, textDescription);

        // Assert
        verify(productLineRepository, times(1)).updateTextDescription(productLine, textDescription);
    }

    @Test
    public void testSearchProductLineByTextDescription() {
        // Prepare
        String textDescription = "Description";
        ProductsLine productLine1 = new ProductsLine();
        ProductsLine productLine2 = new ProductsLine();
        List<ProductsLine> productLines = Arrays.asList(productLine1, productLine2);

        when(productLineRepository.findByTextDescription(textDescription)).thenReturn(productLines);

        // Act
        List<ProductsLine> foundProductLines = productLineService.searchProductLineByTextDescription(textDescription);

        // Assert
        assertEquals(productLines.size(), foundProductLines.size());
        verify(productLineRepository, times(1)).findByTextDescription(textDescription);
    }



}
