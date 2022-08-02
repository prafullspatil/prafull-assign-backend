package com.mb.service;

import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.mb.dto.ProductDto;
import com.mb.entity.Product;
import com.mb.exception.CustomException;
import com.mb.repository.ProductRepository;

@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ModelMapper modelMapper;

	@Override
	public Product saveProduct(ProductDto productDto)
	{
		if (productRepository.findByCode(productDto.getCode()))
		{
			throw new CustomException("Product already exist with code  " + productDto.getCode());
		}
		Product product = modelMapper.map(productDto, Product.class);
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
