package com.mb.controller;

import static com.mb.constant.UrlMapping.BASE_URL;
import static com.mb.constant.UrlMapping.PRODUCT;
import static com.mb.constant.UrlMapping.PRODUCTS;
import static com.mb.constant.UrlMapping.SINGLE_PRODUCT;
import java.util.List;
import javax.validation.Valid;
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
import com.mb.dto.ProductDto;
import com.mb.entity.Product;
import com.mb.service.ProductService;

@RestController
@RequestMapping(BASE_URL)
public class ProductController
{

	@Autowired
	private ProductService productService;

	@PostMapping(PRODUCT)
	@CrossOrigin(origins = "http://localhost:4200")
	public Product saveProduct(@RequestBody @Valid ProductDto productDto)
	{
		return productService.saveProduct(productDto);
	}

	@GetMapping(PRODUCTS)
	@CrossOrigin(origins = "http://localhost:4200")
	public List<Product> getProduct()
	{
		return productService.getProduct();
	}

	@GetMapping(SINGLE_PRODUCT)
	@CrossOrigin(origins = "http://localhost:4200")
	public ResponseEntity<?> getProductById(@PathVariable("id") long id)
	{

		Product product = productService.getProductById(id);
		return new ResponseEntity<>(product, HttpStatus.OK);

	}
}
