package service;

import model.Product;

import java.util.List;

public interface ProductService {
    void save(Product product);
    Product findByIndex(int index);
    List<Product> showAll();
    void update(int id,Product product);
    void remove(int id);
    Product findByName(String name);
    int findIndex(int id);
}
