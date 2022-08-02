package com.mb.service;

import java.util.List;
import com.mb.entity.Product;

public interface ProductService
{
	Product saveProduct(Product product);

	List<Product> getProduct();

	Product getProductById(long id);
}
