package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.Choice.Req.ChoiceReqDto;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceGetRankRes;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceResArtistsDto;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceResUsersDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;


public interface ChoiceService {
    ResponseEntity<ChoiceResArtistsDto> getArtists (Authentication authentication);

    ResponseEntity<ChoiceResUsersDto> getUserNum(Authentication authentication);

    ResponseEntity<HttpStatus> choiceArtist(ChoiceReqDto dto, Authentication authentication);

    ResponseEntity<HttpStatus> deleteChoiceArtist(ChoiceReqDto dto,  Authentication authentication);

    ResponseEntity<List<ChoiceGetRankRes>> getChoiceRank();
}

