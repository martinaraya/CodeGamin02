package com.plexus.martin.exam.services.impl;

import java.io.FileInputStream;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plexus.martin.exam.entities.Product;
import com.plexus.martin.exam.entities.Size;
import com.plexus.martin.exam.entities.Stock;
import com.plexus.martin.exam.repositories.ProductRepository;
import com.plexus.martin.exam.repositories.SizeRepository;
import com.plexus.martin.exam.repositories.StockRepository;
import com.plexus.martin.exam.services.IFileService;
import com.plexus.martin.exam.utils.CSVHelper;

@Service
public class FileServiceImpl implements IFileService {

	private static Logger log = LoggerFactory.getLogger(FileServiceImpl.class);
	private String dir;
	private String path;

	@Autowired
	ProductRepository productRepository;

	@Autowired
	SizeRepository sizeRepository;

	@Autowired
	StockRepository stockRepository;

	void init() {
		dir = System.getProperty("user.dir");
		path = "/src/main/resources/data/";
	}

	@Override
	public void loadProduct(final String fileName) {

		init();
		
		try (FileInputStream inputStream = new FileInputStream(dir + path + fileName);) {

			List<Product> products = CSVHelper.csvToProduct(inputStream);
			productRepository.saveAll(products);

		} catch (Exception e) {
			log.error(String.format("Error loading product: %s", e.getMessage()));
		}
	}

	@Override
	public void loadSize(final String fileName) {
		
		init();
		
		try (FileInputStream inputStream = new FileInputStream(dir + path + fileName);) {

			List<Size> sizes = CSVHelper.csvToSize(inputStream);
			sizeRepository.saveAll(sizes);

		} catch (Exception e) {
			log.error(String.format("Error loading product: %s", e.getMessage()));
		}
	}

	@Override
	public void loadStock(final String fileName) {
		
		init();
		
		try (FileInputStream inputStream = new FileInputStream(dir + path + fileName);) {

			List<Stock> stocks = CSVHelper.csvToStock(inputStream);
			stockRepository.saveAll(stocks);

		} catch (Exception e) {
			log.error(String.format("Error loading product: %s", e.getMessage()));
		}
	}

}
