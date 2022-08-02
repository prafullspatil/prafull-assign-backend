package com.mb.service;

import java.util.List;
import com.mb.dto.ProductDto;
import com.mb.entity.Product;

public interface ProductService
{
	Product saveProduct(ProductDto productDto);

	List<Product> getProduct();

	Product getProductById(long id);
}
