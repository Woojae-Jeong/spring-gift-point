package gift.kakaoOauth.controller;

import gift.kakaoOauth.service.KakaoLoginService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.net.URI;

@Tag(name = "카카오 소셜 로그인", description = "카카오 소셜 로그인 API")
@RequestMapping("/members")
@RestController
public class KakaoOauthController {

    private KakaoLoginService kakaoLoginService;

    public KakaoOauthController(KakaoLoginService kakaoLoginService) {
        this.kakaoLoginService = kakaoLoginService;
    }


    @Operation(summary = "카카오 소셜 로그인", description = "카카오 소셜 로그인을 요청합니다")
    @ApiResponse(responseCode = "302", description = "소셜 로그인 요청 완료")
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @GetMapping("/kakao-login")
    public RedirectView requestLogin(){
        URI redirectURI = kakaoLoginService.requestLogin();
        return new RedirectView(redirectURI.toString());
    }

    @Operation(summary = "액세스 토큰 발급", description = "액세스 토큰을 발급합니다")
    @ApiResponse(responseCode = "200", description = "발급 완료",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "400", description = "요청값이 올바르지 않거나 카카오 API 호출 중 400번대 에러가 발생했습니다.",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "500", description = "서버 내부 에러 발생",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @ApiResponse(responseCode = "502", description = "카카오 서버를 현재 이용할 수 없습니다.",
            content = {@Content(mediaType = "application/json", schema = @Schema(implementation = String.class))
            })
    @GetMapping("/login")
    public ResponseEntity<String> loginOrRegisterUser(@RequestParam ("code") String oauthCode) {
        String token = kakaoLoginService.loginOrRegisterUser(oauthCode);
        return ResponseEntity.ok(token);
    }
}
