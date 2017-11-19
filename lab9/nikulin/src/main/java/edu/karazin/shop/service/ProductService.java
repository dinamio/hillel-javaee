package edu.karazin.shop.service;

import java.util.List;

import edu.karazin.shop.model.Product;

public interface ProductService {

	List<Product> filter(String searchText);
}
