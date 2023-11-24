package Hackerton.Backend.Data.Dto.User.Res;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserImgRes {
    @Schema(description = "유저프로필 이미지 Url")
    private String url;
}