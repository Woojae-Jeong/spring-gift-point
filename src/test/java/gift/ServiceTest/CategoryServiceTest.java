package gift.ServiceTest;

import gift.category.dto.RequestCategoryDto;
import gift.category.dto.ResponseCategoryDto;
import gift.category.exception.CategoryNotFoundException;
import gift.category.repository.CategoryRepository;
import gift.category.service.CategoryService;
import gift.ServiceTest.FakeRepository.FakeCategoryRepository;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatNoException;


public class CategoryServiceTest {
    private CategoryRepository categoryRepository = new FakeCategoryRepository();
    private CategoryService categoryService = new CategoryService(categoryRepository);

    @Test
    void addCategoryTest(){
        RequestCategoryDto requestCategoryDTO = new RequestCategoryDto("카테고리1", "#123456", "카테고리 imageUrl", "카테고리 설명");
        assertThatNoException().
                isThrownBy(()-> categoryService.addCategory(requestCategoryDTO));
    }

    @Test
    void getCategoriesTest() {
        List<ResponseCategoryDto> categories = categoryService.getCategories();
        assertAll(
                ()-> assertThat(categories).hasSize(1),
                ()-> assertThat(categories.get(0).getName()).isEqualTo("카테고리 test")
        );
    }

    @Test
    void editCategoryTest() {
        RequestCategoryDto requestCategoryDTO = new RequestCategoryDto("없는 카테고리", "#123456", "카테고리 imageUrl", "카테고리 설명");
        assertThatExceptionOfType(CategoryNotFoundException.class)
                .isThrownBy(()-> categoryService.editCategory(requestCategoryDTO));
    }

    @Test
    void deleteCategoryTest() {
        assertThatNoException().
                isThrownBy(() -> categoryService.deleteCategory(1L));
    }
}
