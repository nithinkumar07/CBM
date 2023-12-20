package com.cg.cbmapp.service;

import java.util.List;

import com.cg.cbmapp.entities.ProductsLine;

public interface ProductLineService {
	
	
	
//--------------POST(89 Successful)----------------------------------------------------
    
    //Add new Product line object into DB
	 ProductsLine addProductLine(ProductsLine productLine);
	 
	 
//---------------PUT(90)------------------------------------------------------------------	 
	 
	 void updateTextDescription(String productLine, String textDescription);
	 List<ProductsLine> searchProductLineByTextDescription(String textDescription);
	
	
//--------------------GET(91)------------------------------------------------------------
	
	 void addProductsLine(ProductsLine productsLine);
	 //List<ProductsLine> searchProductLineByTextDescription(String textDescription);
	
	 List<ProductsLine>getAll();
}
