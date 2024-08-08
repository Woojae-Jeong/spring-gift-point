package gift.option.controller;

import gift.option.entity.Option;
import gift.option.dto.RequestOptionDto;
import gift.option.dto.ResponseOptionDto;
import gift.option.service.OptionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@Tag(name = "Option", description = "Option API")
@RestController
@RequestMapping("/api/products/{product-id}/options")
public class OptionController {
    private final OptionService optionService;

    public OptionController(OptionService optionService){
        this.optionService = optionService;
    }


    @Operation(summary = "옵션 추가", description = "옵션을 추가합니다")
    @ApiResponse(responseCode = "201", description = "추가 완료",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "400", description = "요청값이 올바르지 않습니다. 요청값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "404", description = "상품이 존재하지 않습니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "409", description = "상품 내에 동일한 이름의 옵션이 존재합니다.",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @PostMapping
    public ResponseEntity<String> addOption (@PathVariable("product-id") Long productId, @Valid @RequestBody RequestOptionDto requestOptionDTO){
        Option option = optionService.addOption(productId, requestOptionDTO);
        return ResponseEntity.created(URI.create("/api/products/"+productId+"/options/"+ option.getId())).body("옵션이 정상적으로 추가되었습니다");
    }

    @Operation(summary = "옵션 조회", description = "옵션 목록을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 완료",
            content= {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseOptionDto.class)))
    })
    @ApiResponse(responseCode = "400", description = "요청값이 올바르지 않습니다. 요청값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "404", description = "상품이 존재하지 않습니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @GetMapping
    public ResponseEntity<List<ResponseOptionDto>> getOptions (@PathVariable("product-id") Long productId){
        List<ResponseOptionDto> optionList = optionService.getOptions(productId);
        return ResponseEntity.ok(optionList);
    }

    @Operation(summary = "옵션 수정", description = "옵션을 수정합니다")
    @ApiResponse(responseCode = "200", description = "수정 완료",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "400", description = "요청값이 올바르지 않습니다. 요청값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "404", description = "옵션이 존재하지 않습니다.",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "409", description = "상품 내에 바꾸러는 옵션 이름과 동일한 이름을 가진 옵션이 존재합니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @PutMapping("/{option-id}")
    public ResponseEntity<String> editOption(@PathVariable("product-id") Long productId, @PathVariable("option-id") Long optionId, @Valid @RequestBody RequestOptionDto requestOptionDTO){
        optionService.editOption(productId, optionId, requestOptionDTO);
        return ResponseEntity.ok("옵션이 정상적으로 수정되었습니다");
    }

    @Operation(summary = "옵션 삭제", description = "옵션을 삭제합니다")
    @ApiResponse(responseCode = "200", description = "추가 완료",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 입력값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "404", description = "옵션이 존재하지 않습니다.",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "409", description = "상품 내에 하나밖에 남지않은 옵션입니다. 삭제할 수 없습니다.",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @DeleteMapping("/{option-id}")
    public ResponseEntity<String> deleteOption(@PathVariable("product-id") Long productId, @PathVariable("option-id") Long optionId) {
        optionService.deleteOption(productId, optionId);
        return ResponseEntity.ok("옵션이 정상적으로 삭제되었습니다");
    }
}
