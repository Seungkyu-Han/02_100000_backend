package Hackerton.Backend.Data.Dto.Choice.Req;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChoiceReqDto {

    private User user;
    private Artist artist;

}
