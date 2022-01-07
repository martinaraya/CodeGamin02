package com.plexus.martin.exam;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.plexus.martin.exam.services.IFileService;
import com.plexus.martin.exam.services.IShopService;

@SpringBootApplication
public class ExamApplication implements CommandLineRunner{

	@Autowired
	private IShopService shopService;
	
	@Autowired
	private IFileService fileService;
	
	private static Logger log = LoggerFactory.getLogger(ExamApplication.class);
	
	public static void main(String[] args) {
		log.info("Starting application");
		SpringApplication.run(ExamApplication.class, args);
		log.info("Application finished");
	}
		
	@Override
	public void run(String... args) throws Exception {
		fileService.loadProduct("product.csv");
		fileService.loadSize("size.csv");
		fileService.loadStock("stock.csv");
		shopService.showFilteredProducts();
		
	}

}
