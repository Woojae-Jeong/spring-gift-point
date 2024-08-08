package gift.product.controller;

import gift.option.service.OptionService;
import gift.product.service.ProductService;
import gift.product.dto.ResponseProductDto;
import gift.product.dto.ResponseProductListOfCategoryDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Product", description = "Product API")
@Controller
@RequestMapping("/api")
public class ProductController {

    private final ProductService productService;
    private final OptionService optionService;

    public ProductController(ProductService productService, OptionService optionService) {
        this.productService = productService;
        this.optionService = optionService;
    }

    @Operation(summary = "카테고리에 속하는 상품 목록 조회", description = "상품 목록을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 완료",
            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseProductListOfCategoryDto.class)))
            })
    @ApiResponse(responseCode = "400", description = "요청값이 올바르지 않습니다. 요청값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "404", description = "카테고리가 존재하지 않습니다. 요청값을 다시 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @GetMapping("/products")
    public ResponseEntity<Page<ResponseProductListOfCategoryDto>> getProducts(@PageableDefault(size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable,
                                                                              @RequestParam("categoryId") Long categoryId) {
        Page<ResponseProductListOfCategoryDto> page = productService.getAllProducts(pageable, categoryId);
        return ResponseEntity.ok(page);
    }

    @Operation(summary = "단일 상품 조회", description = "단일 상품을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 완료",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseProductDto.class))
            })
    @ApiResponse(responseCode = "400", description = "요청값이 올바르지 않습니다. 요청값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "404", description = "상품이 존재하지 않습니다. 요청값을 다시 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @GetMapping("/products/{product-id}")
    public ResponseEntity<ResponseProductDto> getProduct(@PathVariable("product-id") Long productId){
        ResponseProductDto response = productService.getProduct(productId);
        return ResponseEntity.ok(response);
    }
}