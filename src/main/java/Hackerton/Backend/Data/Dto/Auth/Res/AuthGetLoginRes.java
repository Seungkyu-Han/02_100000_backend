package Hackerton.Backend.Data.Dto.Auth.Res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthGetLoginRes {

    private String accessToken;
    private String refreshToken;
}
