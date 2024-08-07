package gift.category.service;

import gift.category.exception.CategoryNotFoundException;
import gift.category.entity.Category;
import gift.category.dto.RequestCategoryDto;
import gift.category.dto.ResponseCategoryDto;
import gift.vo.Name;
import gift.category.repository.CategoryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryService(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Transactional
    public Category addCategory(RequestCategoryDto requestCategoryDTO) {
        Category category = new Category(requestCategoryDTO.name(), requestCategoryDTO.color(), requestCategoryDTO.imageUrl(), requestCategoryDTO.description());
        return categoryRepository.save(category);
    }
    @Transactional(readOnly = true)
    public List<ResponseCategoryDto> getCategories() {
        return categoryRepository.findAll()
                .stream()
                .map(it->new ResponseCategoryDto(
                        it.getId(),
                        it.getName().getValue(),
                        it.getColor().getValue(),
                        it.getImageUrl().getValue(),
                        it.getDescription().getValue()))
                .toList();
    }

    @Transactional
    public void editCategory(RequestCategoryDto requestCategoryDTO) {
        Category category = categoryRepository.findByName(new Name(requestCategoryDTO.name()))
                .orElseThrow(()->new CategoryNotFoundException());
        category.update(requestCategoryDTO.name(), requestCategoryDTO.color(), requestCategoryDTO.imageUrl(), requestCategoryDTO.description());;
    }

    @Transactional
    public void deleteCategory(Long categoryId) {
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(()-> new CategoryNotFoundException());
        categoryRepository.deleteById(categoryId);
    }
}
