package com.mb.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.entity.Product;
import com.mb.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product saveProduct(Product product)
	{
		return productRepository.save(product);
	}

	@Override
	public List<Product> getProduct()
	{

		return productRepository.findAll();
	}

	@Override
	public Product getProductById(long id)
	{

		return productRepository.findProductById(id);

	}

}
