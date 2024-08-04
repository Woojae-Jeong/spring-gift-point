package gift.DTO;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

@Schema(description = "주문 요청 DTO")
public record RequestOrderDTO (
    @NotNull(message = "옵션을 선택하지 않으셨습니다. 선택해주세요")
    @Min(value = 1, message = "옵션Id 값은 1이상입니다")
    @Schema(description = "옵션 Id")
    Long optionId,

    @NotNull(message = "수량을 입력하지 않으셨습니다. 입력해주세요")
    @Min(value = 1, message = "수량은 적어도 1이상입니다")
    @Schema(description = "주문 수량")
    Integer quantity,

    @Schema(description = "주문 메세지")
    String message,

    @Pattern(
            regexp = "^01[0-9]-\\d{4}-\\d{4}$",
            message = "현금 영수증 휴대폰 번호의 양식이 잘못되었습니다"
    )
    @Schema(description= "현금 영수증 신청 휴대폰 번호")
    String phoneNumber
) { }
