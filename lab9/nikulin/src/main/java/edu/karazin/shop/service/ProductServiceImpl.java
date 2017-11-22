package edu.karazin.shop.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import edu.karazin.shop.model.Product;

@Service
public class ProductServiceImpl implements ProductService {

	// TODO: Move data to Data Base
	private static final List<Product> PRODUCTS = Arrays.asList(
			new Product(1L, "apple", ""),
			new Product(2L, "apricot", ""),
			new Product(3L, "banana", ""),
			new Product(4L, "grape", ""),
			new Product(5L, "cherry", "")
	);

	@Override
	public List<Product> filter(String searchText) {
		if (searchText == null || searchText.trim().isEmpty()) {
			return PRODUCTS;
		}

		List<Product> results = new ArrayList<>();
		for (Product p : PRODUCTS) {
			if (containsWithNullCheck(p.getTitle(), searchText) || containsWithNullCheck(p.getDescription(), searchText)) {
				results.add(p);
			}
		}
		return results;
	}

	private boolean containsWithNullCheck(String source, String searchText) {
		if (source == null || source.trim().isEmpty()) {
			return false;
		}
		return source.toLowerCase().contains(searchText.toLowerCase());
	}
}
