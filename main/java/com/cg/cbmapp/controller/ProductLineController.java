package com.cg.cbmapp.controller;

import com.cg.cbmapp.entities.ProductsLine;
import com.cg.cbmapp.service.ProductLineService;

import jakarta.validation.Valid;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@Validated
@RestController
@RequestMapping("/api/v1")
public class ProductLineController {

	@Autowired
	private ProductLineService productLineService;

	@Autowired
	public ProductLineController(ProductLineService productLineService) {
		this.productLineService = productLineService;
	}

	// --------------POST(89
	// Successful)----------------------------------------------------
	// Add new Product line object into DB

	@PostMapping("/admin/product_lines/")
	public ResponseEntity<String> addProductLine(@Valid @RequestBody ProductsLine productLine) {
		ProductsLine addedProductLine = productLineService.addProductLine(productLine);
		return new ResponseEntity<>("Product line details added successfully", HttpStatus.CREATED);
	}

	// ------------------PUT(90)-------------------------------------------------------

	@PutMapping("/admin/product_lines/{product_line}/text_description/{text_discription}")
	public ResponseEntity<String> updateTextDescription(@Valid @PathVariable("product_line") String productLine,
			@RequestBody String textDescription) {

		productLineService.updateTextDescription(productLine, textDescription);

		String responseMessage = "Product's text description updated successfully";
		return new ResponseEntity<>(responseMessage, HttpStatus.OK);
	}

	// -------------------GET(91)---------------------------------
	// Search Product_line details by id

	@GetMapping("/admin/product_lines/text_description/{text_description}")
	public ResponseEntity<?> searchProductLineByTextDescription(
			@PathVariable("text_description")@RequestBody String textDescription) {
		List<ProductsLine> productLines = productLineService.searchProductLineByTextDescription(textDescription);
		if (productLines.isEmpty()) {
			return ResponseEntity.notFound().build();
		} else {
			return ResponseEntity.ok(productLines);
		}
	}
	@GetMapping("/getall/ProductsLine")
	public ResponseEntity<List<ProductsLine>> getAll(){
		
	List<ProductsLine> productList=	productLineService.getAll();
	
		return new ResponseEntity<List<ProductsLine>>(productList,HttpStatus.ACCEPTED);
	}

}
