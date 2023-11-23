package Hackerton.Backend.Data.Dto.Choice.Res;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChoiceResArtistsDto {

    private User user;
    private List<Artist> artists;
}
