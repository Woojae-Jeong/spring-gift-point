package gift.product.service;

import gift.category.exception.CategoryNotFoundException;
import gift.option.dto.ResponseOptionDTO;
import gift.product.exception.ProductNotFoundException;
import gift.category.entity.Category;
import gift.option.entity.Option;
import gift.product.entity.Product;
import gift.category.repository.CategoryRepository;
import gift.option.repository.OptionRepository;
import gift.product.repository.ProductRepository;
import gift.product.dto.RequestProductDTO;
import gift.product.dto.RequestProductPostDTO;
import gift.product.dto.ResponseProductDTO;
import gift.product.dto.ResponseProductListOfCategoryDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    private final OptionRepository optionRepository;


    public ProductService(ProductRepository productRepository, CategoryRepository categoryRepository, OptionRepository optionRepository) {
        this.productRepository = productRepository;
        this.categoryRepository  = categoryRepository;
        this.optionRepository = optionRepository;
    }

    @Transactional(readOnly = true)
    public Page<Product> getAllProducts(Pageable pageable) {
        Page<Product> productPage = productRepository.findAll(pageable);
        return productPage;
    }

    @Transactional(readOnly = true)
    public Page<ResponseProductListOfCategoryDTO> getAllProducts(Pageable pageable, Long categoryId) {
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new CategoryNotFoundException("매칭되는 category가 없습니다"));
        Page<Product> productPage = productRepository.findByCategory(category, pageable);
        List<ResponseProductListOfCategoryDTO> response = productPage.getContent()
                .stream()
                .map(ResponseProductListOfCategoryDTO:: of)
                .toList();
        Page<ResponseProductListOfCategoryDTO> page = new PageImpl<>(response, pageable, productPage.getTotalElements());
        return page;
    }

    @Transactional(readOnly = true)
    public ResponseProductDTO getProduct(Long productId) {
        Product product = productRepository.findById(productId).orElseThrow(()-> new ProductNotFoundException("매칭되는 product가 없습니다"));
        List<ResponseOptionDTO> options = optionRepository.findByProduct(product)
                .stream()
                .map(ResponseOptionDTO :: of)
                .toList();
        return ResponseProductDTO.of(product, options);
    }

    @Transactional
    public void addProduct(RequestProductPostDTO requestProductPostDTO) {
        Category category = categoryRepository.findById(requestProductPostDTO.categoryId())
                .orElseThrow(()-> new CategoryNotFoundException("매칭되는 카테고리가 없습니다"));
        Product product = new Product(requestProductPostDTO.name(), requestProductPostDTO.price(), requestProductPostDTO.imageUrl(), category);
        productRepository.save(product);
        optionRepository.save(new Option(requestProductPostDTO.optionName(), requestProductPostDTO.optionQuantity(), product));
    }

    @Transactional(readOnly = true)
    public Product selectProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("매칭되는 product가 없습니다"));
        return product;
    }

    @Transactional
    public void editProduct(long id, RequestProductDTO requestProductDTO) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("매칭되는 product가 없습니다"));
        Category category = categoryRepository.findById(requestProductDTO.categoryId())
                .orElseThrow(() -> new CategoryNotFoundException("매칭되는 카테고리가 없습니다"));
        product.update(requestProductDTO.name(), requestProductDTO.price(), requestProductDTO.imageUrl(), category);
    }

    @Transactional
    public void deleteProduct(long id) {
        Product product = productRepository.findById(id).orElseThrow(() -> new ProductNotFoundException("매칭되는 product가 없습니다"));
        optionRepository.deleteByProduct(product);
        productRepository.deleteById(product.getId());
    }


}
