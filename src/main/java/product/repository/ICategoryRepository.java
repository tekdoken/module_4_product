package product.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import product.model.Category;
@Repository
public interface ICategoryRepository extends JpaRepository<Category,Integer> {
Iterable<Category> findAllByName(String name);
Iterable<Category> findAllByNameOrderByName(String name);
}
