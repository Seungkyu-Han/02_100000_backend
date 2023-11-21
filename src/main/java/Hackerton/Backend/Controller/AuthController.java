package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.Auth.Res.AuthGetLoginRes;
import Hackerton.Backend.Service.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증 및 로그인 관련 API")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    @Operation(summary = "로그인 API", description = "카카오 code 이용하여 로그인")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = AuthGetLoginRes.class))),
            @ApiResponse(responseCode = "201", description = "회원가입 성공",
                    content = @Content(schema = @Schema(implementation = AuthGetLoginRes.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<AuthGetLoginRes> login(String code) throws IOException {
        return authService.login(code);
    }

    @PatchMapping("/login")
    @Operation(summary = "RefreshToken 사용해서 로그인 API", description = "Authorization 헤더에 RefreshToken 넣어주세요.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그인 성공",
                    content = @Content(schema = @Schema(implementation = AuthGetLoginRes.class))),
            @ApiResponse(responseCode = "403", description = "회원이 아닙니다.",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<AuthGetLoginRes> login(@Parameter(hidden = true) Authentication authentication){
        return authService.login(authentication);
    }

    @DeleteMapping("/login")
    @Operation(summary = "로그아웃 API", description = "서버에 남은 RefreshToken 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "로그아웃 성공",
                    content = @Content(schema = @Schema(implementation = AuthGetLoginRes.class))),
            @ApiResponse(responseCode = "403", description = "회원이 아닙니다.",
                    content = @Content(schema = @Schema(implementation = AuthGetLoginRes.class))),
            @ApiResponse(responseCode = "500", description = "내부 서버 오류",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<HttpStatus> logout(@Parameter(hidden = true) Authentication authentication){
        return authService.logout(authentication);
    }
}
