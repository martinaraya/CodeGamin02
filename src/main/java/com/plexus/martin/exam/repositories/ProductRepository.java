package com.plexus.martin.exam.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.plexus.martin.exam.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select distinct p from Product p inner join fetch p.sizes s inner join fetch s.stock t")
	public List<Product> findProductsWithSize();
}
