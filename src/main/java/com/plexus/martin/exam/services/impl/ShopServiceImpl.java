package com.plexus.martin.exam.services.impl;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.plexus.martin.exam.entities.Product;
import com.plexus.martin.exam.entities.Size;
import com.plexus.martin.exam.repositories.ProductRepository;
import com.plexus.martin.exam.services.IFileService;
import com.plexus.martin.exam.services.IShopService;

@Service
public class ShopServiceImpl implements IShopService {

	private static Logger log = LoggerFactory.getLogger(ShopServiceImpl.class);

	@Autowired
	IFileService fileService;

	@Autowired
	ProductRepository productRepository;

	@Override
	public void showFilteredProducts() {
		log.info("Starting filter products");

		List<Product> productList = productRepository.findProductsWithSize();

		// filter
		var filteredProductList = productList.stream().filter(p -> firstCondition(p) || secondCondition(p)).distinct()
				.collect(Collectors.toList());

		// sort by sequence
		Collections.sort(filteredProductList, Comparator.comparingInt(Product::getSequence));
		log.info("Filtered result:");
		// extract only ids to print easily
		var txtResult = String.join(",",
				filteredProductList.stream().map(p -> String.valueOf(p.getId())).collect(Collectors.toList()));
		// print result
		log.info(txtResult);

		log.info("Finish filter products");
	}

	public boolean firstCondition(Product p) {
		// return TRUE if not special and there is stock (or backsoon)
		return p.getSizes().stream().anyMatch(s -> Boolean.FALSE.equals(s.getSpecial()) && thereIsStock(s));
	}

	public boolean secondCondition(Product p) {
		// return TRUE if there is one "special" and one "not special" with stock (or
		// backsoon)
		var special = p.getSizes().stream().filter(s -> s.getSpecial() && thereIsStock(s)).count() > 0;
		var nospecial = p.getSizes().stream().filter(s -> !s.getSpecial() && thereIsStock(s)).count() > 0;
		return special && nospecial;
	}

	public boolean thereIsStock(Size s) {
		// return true if exist stock or backsoon is true
		return ((s.getStock() != null && s.getStock().getQuantity() > 0) || Boolean.TRUE.equals(s.getBackSoon()));

	}

}
