package gift.product.repository;

import gift.category.entity.Category;
import gift.product.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> {

    Page<Product> findByCategory(Category category, Pageable pageable);
}
