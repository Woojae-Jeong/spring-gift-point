package gift.wish.controller;

import gift.wish.dto.RequestWishDTO;
import gift.wish.dto.ResponseWishDTO;
import gift.member.entity.Member;
import gift.wish.entity.Wish;
import gift.wish.service.WishService;
import gift.annotation.ValidUser;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@Tag(name = "Wish", description = "Wish API")
@RestController
@RequestMapping("/api")
public class WishController {
    private final WishService wishService;


    public WishController(WishService wishService) {
        this.wishService = wishService;
    }

    @Operation(summary = "찜 추가", description = "찜을 추가합니다")
    @ApiResponse(responseCode = "201", description = "추가 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 입력값을 확인하거나 상품이 존재하지 않습니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "403", description = "유효하지 않은 토큰입니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @PostMapping("/wishes")
    public ResponseEntity<Void> addWish(@ValidUser Member member, @Valid @RequestBody RequestWishDTO requestWishDTO) {
        Wish wish = wishService.addWish(member, requestWishDTO);
        return ResponseEntity.created(URI.create("api/wishes/" + wish.getId())).build();
    }

    @Operation(summary = "찜 조회", description = "찜 목록을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 완료",
            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation =ResponseWishDTO.class)))
            })
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 입력값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "403", description = "유효하지 않은 토큰입니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @GetMapping("/wishes")
    public ResponseEntity<Page<ResponseWishDTO>> getWish(@ValidUser Member member,
                              @PageableDefault(size = 10, sort = "createdDate", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<ResponseWishDTO> wishList = wishService.getWishList(member, pageable);
        return ResponseEntity.ok(wishList);
    }

    @Operation(summary = "찜 삭제", description = "찜을 삭제합니다")
    @ApiResponse(responseCode = "204", description = "삭제 완료")
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 입력값을 확인하거나 상품이나 찜이 존재하지 않습니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "403", description = "유효하지 않은 토큰입니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @DeleteMapping("/wishes/{product-id}")
    public ResponseEntity<Void> deleteWish(@ValidUser Member member, @PathVariable("product-id") Long productId) {
        wishService.deleteWish(member, productId);

        return ResponseEntity.noContent().build();
    }
}