package com.plexus.martin.exam.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.plexus.martin.exam.entities.Product;
import com.plexus.martin.exam.entities.Size;
import com.plexus.martin.exam.entities.Stock;

public class CSVHelper {

	private static Logger log = LoggerFactory.getLogger(CSVHelper.class);

	private CSVHelper() {
		throw new IllegalStateException("Utility class");
	}
	
	public static List<Product> csvToProduct(InputStream is) {

		log.info("Loading Product");
		List<Product> productList = new ArrayList<>();

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withTrim());) {
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Product product = new Product(Integer.parseInt(csvRecord.get(0)), Integer.parseInt(csvRecord.get(1)),
						null);
				productList.add(product);
			}

		} catch (IOException e) {
			log.error(String.format("fail to parse CSV file: %s", e.getMessage()));
		}

		return productList;
	}

	public static List<Size> csvToSize(InputStream is) {

		log.info("Loading Size");
		List<Size> sizeList = new ArrayList<>();

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withTrim());) {
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Size size = new Size(Integer.parseInt(csvRecord.get(0)),
						new Product(Integer.parseInt(csvRecord.get(1)), 0, null),
						Boolean.parseBoolean(csvRecord.get(2)), Boolean.parseBoolean(csvRecord.get(3)), null);
				sizeList.add(size);
			}

		} catch (IOException e) {
			log.error(String.format("fail to parse CSV file: %s", e.getMessage()));
		}

		return sizeList;
	}

	public static List<Stock> csvToStock(InputStream is) {

		log.info("Loading Stock");
		List<Stock> stockList = new ArrayList<>();

		try (BufferedReader fileReader = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8));
				CSVParser csvParser = new CSVParser(fileReader, CSVFormat.DEFAULT.withTrim());) {
			Iterable<CSVRecord> csvRecords = csvParser.getRecords();

			for (CSVRecord csvRecord : csvRecords) {
				Stock stock = new Stock(Integer.parseInt(csvRecord.get(0)), Integer.parseInt(csvRecord.get(1)),
						new Size(Integer.parseInt(csvRecord.get(0)), null, null, null, null));
				stockList.add(stock);
			}

		} catch (IOException e) {
			log.error(String.format("fail to parse CSV file: %s", e.getMessage()));
		}

		return stockList;
	}

}
