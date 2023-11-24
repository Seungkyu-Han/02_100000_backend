package Hackerton.Backend.Data.Dto.Choice.Res;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ChoiceGetRankRes {

    private Long id;

    private String name;

    private Long count;

}
