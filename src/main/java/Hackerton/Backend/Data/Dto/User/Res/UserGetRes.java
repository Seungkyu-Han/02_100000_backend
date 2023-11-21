package Hackerton.Backend.Data.Dto.User.Res;

import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Data.Enum.InterestEnum;
import Hackerton.Backend.Data.Enum.RoleEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UserGetRes {

    @Schema(name = "유저 이름", description = "박종혁")
    private String name;

    @Schema(name = "유저 권한", description = "ARTIST")
    private RoleEnum role;

    @Schema(name = "유저 관심사", description = "HIPHOP")
    private InterestEnum interest;

    public UserGetRes(User user){
        this.name = user.getName();
        this.role = user.getRole();
        this.interest = user.getInterest();
    }
}
