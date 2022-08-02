package com.mb.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.mb.entity.Product;
import com.mb.service.ProductService;

@RestController
@RequestMapping("/api/v1")
public class ProductController
{

	@Autowired
	private ProductService productService;

	@PostMapping("/save")
	public Product saveProduct(@RequestBody Product productmodel)
	{
		return productService.saveProduct(productmodel);
	}

	@GetMapping("/get-product")
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> getProduct()
	{
		return productService.getProduct();
	}

	@GetMapping("/get-product/{id}")
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getProductById(@PathVariable("id") long id)
	{

		Product product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);

	}
}
