package Hackerton.Backend.Data.Dto.Choice.Res;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChoiceResUsersDto {

    @Schema(description = "아티스트")
    private Artist artist;
    @Schema(description = "아티스트 찜을 누른 유저들")
    private List<User> users;
    @Schema(description = "아티스트의 찜 개수")
    private Integer userCount;
}
