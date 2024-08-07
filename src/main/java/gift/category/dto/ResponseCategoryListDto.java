package gift.category.dto;

import java.util.List;

public record ResponseCategoryListDto(
        List<ResponseCategoryDto> categories
){ }
