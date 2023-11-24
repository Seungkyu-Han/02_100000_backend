package Hackerton.Backend.Data.Dto.Choice.Req;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChoiceReqDto {

    @Schema(description = "찜을 누른 유저")
    private User user;
    @Schema(description = "찜 한 아티스트")
    private Artist artist;

}
