package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.Auth.Res.AuthGetLoginRes;
import org.apache.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface AuthService {
    ResponseEntity<AuthGetLoginRes> login(String code) throws IOException;
}
