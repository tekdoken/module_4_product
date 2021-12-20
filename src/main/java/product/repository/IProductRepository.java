package product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.model.Category;
import product.model.Product;
@Repository
public interface IProductRepository extends JpaRepository<Product,Integer> {
Iterable<Product> findAllByCategory(Category category);
    Iterable<Product> findByNameContaining(String name);
    Iterable<Product> findAllByOrderByPriceDesc();
    Iterable<Product> findAllByOrderByPriceAsc();
}
