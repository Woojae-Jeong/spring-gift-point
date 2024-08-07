package gift.product.dto;


import gift.option.dto.ResponseOptionDto;
import gift.product.entity.Product;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;


@Schema(description = "단일 상품 조회 응답 DTO")
public class ResponseProductDto {
    @Schema(description = "상품 Id")
    private Long id;

    @Schema(description = "상품 이름")
    private String name;

    @Schema(description = "상품 가격")
    private int price;

    @Schema(description = "상품 이미지url")
    private String imageUrl;

    @Schema(description = "상품 옵션")
    private List<ResponseOptionDto> options;

    public ResponseProductDto(Long id, String name, int price, String imageUrl, List<ResponseOptionDto> options) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.imageUrl = imageUrl;
        this.options = options;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public List<ResponseOptionDto> getOptions() {
        return options;
    }

    public static ResponseProductDto of(Product product, List<ResponseOptionDto> options) {
        return new ResponseProductDto(product.getId(), product.getName().getValue(), product.getPrice().getValue(), product.getImageUrl().getValue(), options);
    }
}