package gift.option.repository;

import gift.option.entity.Option;
import gift.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OptionRepository extends JpaRepository<Option, Long> {
    List<Option> findByProduct(Product product);

    void deleteByProduct(Product product);
}
