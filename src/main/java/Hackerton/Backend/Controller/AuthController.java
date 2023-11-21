package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.Auth.Res.AuthGetLoginRes;
import Hackerton.Backend.Service.AuthService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;


@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "인증 및 로그인 관련 API")
public class AuthController {
    private final AuthService authService;

    @GetMapping("/login")
    public ResponseEntity<AuthGetLoginRes> login(String code) throws IOException {
        return authService.login(code);
    }
}
