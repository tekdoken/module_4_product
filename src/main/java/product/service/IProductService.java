package product.service;

import product.model.Category;
import product.model.Product;

public interface IProductService extends IGeneralService<Product>{
    public Iterable<Product> findAllByCategory(Category category);
    public Iterable<Product> findByName(String name);
}
