
package com.cg.cbmapp.service;

 

import com.cg.cbmapp.entities.ProductsLine;
import com.cg.cbmapp.exception.CustomException;
import com.cg.cbmapp.repository.ProductLineRepository;

 

import jakarta.transaction.Transactional;
import java.util.List;

 

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

 

@Service
public class ProductLineServiceImpl implements ProductLineService {

    private final ProductLineRepository productLineRepository;

    @Autowired
    public ProductLineServiceImpl(ProductLineRepository productLineRepository) {
        this.productLineRepository = productLineRepository;
    }



//-----------------------------------POST(89 Successful)------------------------------

    //Add new Product line object into DB
    @Override
    public ProductsLine addProductLine(ProductsLine productLine) {
        ProductsLine save = productLineRepository.save(productLine);
        if(save != null) {
            return save;
        }
        else {
            throw new CustomException("Product Details not found");
        }
    }

 

//--------------------------------------PUT(90) E----------------------------------------

    @Transactional
    @Override
    public void updateTextDescription(String productLine, String textDescription) {
        productLineRepository.updateTextDescription(productLine, textDescription);

    }
//--------------------------------------GET(91) E---------------------------------------

    @Override
    public List<ProductsLine> searchProductLineByTextDescription(String textDescription) {
        // Implement the search logic using the repository
        List<ProductsLine> findByTextDescription = productLineRepository.findByTextDescription(textDescription);
        if(findByTextDescription.size()>0) {
            return findByTextDescription;
        }
        else {
            throw new CustomException("Product Details not found");
        }
    }


    @Override
    public void addProductsLine(ProductsLine productsLine) {
        

 

    }



	@Override
	public List<ProductsLine> getAll() {
	List<ProductsLine> productList=productLineRepository.findAll();
		return productList;
	}



}
