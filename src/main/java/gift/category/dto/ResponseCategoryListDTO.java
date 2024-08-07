package gift.category.dto;

import java.util.List;

public record ResponseCategoryListDTO (
        List<ResponseCategoryDTO> categories
){ }
