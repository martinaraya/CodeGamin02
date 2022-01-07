package com.plexus.martin.exam;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.plexus.martin.exam.repositories.ProductRepository;
import com.plexus.martin.exam.repositories.SizeRepository;
import com.plexus.martin.exam.repositories.StockRepository;
import com.plexus.martin.exam.services.impl.FileServiceImpl;

class FileServiceTest {

	@InjectMocks
	FileServiceImpl underTest;
	
	@Mock
	ProductRepository productRepository;
	
	@Mock
	SizeRepository sizeRepository;
	
	@Mock
	StockRepository stockRepository;	

	@BeforeEach
	void init() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void loadStockTestOK() {
		underTest.loadStock("stock.csv");

	}

	@Test
	void loadStockTestKO() {
		underTest.loadStock("__stock.csv" );

	}

	@Test
	void loadProductTestOK() {
		underTest.loadProduct("product.csv");

	}

	@Test
	void loadProductTestKO() {
		underTest.loadProduct("___product.csv");
	
	}

	@Test
	void loadSizeTestOK() {
		underTest.loadSize("size.csv");

	}

	@Test
	void loadSizeTestKO() {
		underTest.loadSize("___size.csv");

	}

}
