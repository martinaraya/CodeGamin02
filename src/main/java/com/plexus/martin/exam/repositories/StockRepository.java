package com.plexus.martin.exam.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.plexus.martin.exam.entities.Stock;

@Repository
public interface StockRepository extends JpaRepository<Stock, Integer> {
}
