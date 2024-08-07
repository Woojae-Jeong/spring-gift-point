package gift.order.controller;

import gift.order.dto.RequestOrderDto;
import gift.order.dto.ResponseOrderDto;
import gift.member.entity.Member;
import gift.order.service.OrderService;
import gift.annotation.ValidUser;
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

@Tag(name = "Order", description = "Order API")
@RestController
@RequestMapping("api/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @Operation(summary = "주문 조회", description = "주문 목록을 조회합니다")
    @ApiResponse(responseCode = "200", description = "조회 완료",
            content = {@Content(mediaType = "application/json", array = @ArraySchema(schema = @Schema(implementation = ResponseOrderDto.class)))
    })
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 입력값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @GetMapping
    public ResponseEntity<List<ResponseOrderDto>> getOrders(@ValidUser Member member){
        List<ResponseOrderDto> orders = orderService.getOrders(member);
        return ResponseEntity.ok(orders);
    }

    @Operation(summary = "주문 추가", description = "주문을 추가합니다")
    @ApiResponse(responseCode = "201", description = "추가 완료",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ResponseOrderDto.class))
            })
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 입력값을 확인하거나 옵션이나 재고가 없습니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "403", description = "유효하지 않은 토큰입니다",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @PostMapping
    public ResponseEntity<ResponseOrderDto> createOrder(@ValidUser Member member, @Valid @RequestBody RequestOrderDto requestOrderDTO){
        ResponseOrderDto response =orderService.createOrder(member, requestOrderDTO);
        return ResponseEntity.created(URI.create("api/orders/"+response.getId())).body(response);
    }

    @Operation(summary = "주문 삭제", description = "주문을 삭제합니다")
    @ApiResponse(responseCode = "200", description = "삭제 완료",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "400", description = "잘못된 요청입니다. 입력값을 확인해주세요",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @DeleteMapping()
    public ResponseEntity<String > deleteOrder(@ValidUser Member member, @RequestParam("order-id") Long orderId){
        orderService.deleteOrder(member, orderId);
        return ResponseEntity.ok("주문이 정상적으로 취소되었습니다");
    }
 }
