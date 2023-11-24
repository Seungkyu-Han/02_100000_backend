package Hackerton.Backend.Data.Dto.Choice.Res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChoiceGetRankRes {

    private String name;

    private Long count;

}
