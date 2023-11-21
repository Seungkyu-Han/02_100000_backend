package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.Auth.Res.AuthGetLoginRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.io.IOException;

public interface AuthService {
    ResponseEntity<AuthGetLoginRes> login(String code) throws IOException;
    ResponseEntity<AuthGetLoginRes> login(Authentication authentication);
    ResponseEntity<HttpStatus> logout(Authentication authentication);
}
