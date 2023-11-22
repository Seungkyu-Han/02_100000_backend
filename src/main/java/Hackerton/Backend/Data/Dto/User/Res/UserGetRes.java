package Hackerton.Backend.Data.Dto.User.Res;

import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Data.Enum.InterestEnum;
import Hackerton.Backend.Data.Enum.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserGetRes {

    @Schema(description = "유저 이름", example = "박종혁")
    private String name;

    @Schema(description = "유저 권한", example = "ARTIST")
    private RoleEnum role;

    @Schema(description = "유저 관심사", example = "HIPHOP")
    private InterestEnum interest;

    @Schema(description = "유저 이미지 Url", example = "https://avatars.githubusercontent.com/u/114932050?v=4")
    private String imgUrl;

    public UserGetRes(User user){
        this.name = user.getName();
        this.role = user.getRole();
        this.interest = user.getInterest();
        this.imgUrl = user.getImgUrl();
    }
}
