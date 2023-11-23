package Hackerton.Backend.Data.Dto.Choice.Res;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ChoiceResUsersDto {
    private Artist artist;
    private List<User> users;
    private Integer userCount;
}
