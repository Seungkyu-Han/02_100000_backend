package Hackerton.Backend.Data.Dto.Choice.Res;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChoiceResArtistsDto {

    @Schema(description = "찜을 누른 유저")
    private User user;
    @Schema(description = "찜 한 아티스트들 목록")
    private List<Artist> artists;
}
