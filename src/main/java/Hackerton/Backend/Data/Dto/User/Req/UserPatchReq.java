package Hackerton.Backend.Data.Dto.User.Req;

import Hackerton.Backend.Data.Enum.InterestEnum;
import Hackerton.Backend.Data.Enum.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserPatchReq {

    @Schema(description = "유저의 이름(최대 10글쟈)", example = "박종혁")
    private String name;

    @Schema(description = "유저의 권한", example = "ARTIST")
    private RoleEnum role;

    @Schema(description = "유저의 관심사", example = "HIPHOP")
    private InterestEnum interest;

}
