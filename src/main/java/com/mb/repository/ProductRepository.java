package com.mb.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.mb.entity.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>
{
	Product findProductById(long id);
}
