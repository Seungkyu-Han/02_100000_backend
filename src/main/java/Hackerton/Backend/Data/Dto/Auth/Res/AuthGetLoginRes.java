package Hackerton.Backend.Data.Dto.Auth.Res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthGetLoginRes {

    @Schema(name = "AccessToken")
    private String accessToken;

    @Schema(name = "RefreshToken", description = "Patch 메서드에서는 입력한 RefreshToken 그대로 반환")
    private String refreshToken;
}
