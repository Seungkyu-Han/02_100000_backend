package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.Choice.Req.ChoiceReqDto;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceResArtistsDto;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceResUsersDto;
import Hackerton.Backend.Service.ChoiceService;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/choice")
@RequiredArgsConstructor
@Tag(name = "찜 관련 API")
public class ChoiceController {

    private final ChoiceService choiceService;

    @GetMapping
    public ResponseEntity<ChoiceResArtistsDto> getArtists(Authentication authentication) {
        return choiceService.getArtists(authentication);
    }
    @GetMapping
    public ResponseEntity<ChoiceResUsersDto> getUsers(Authentication authentication){
        return choiceService.getUserNum(authentication);
    }
    @PostMapping
    public ResponseEntity<HttpStatus> choiceArtist(@RequestBody ChoiceReqDto dto, Authentication authentication){
        return choiceService.choiceArtist(dto,authentication);
    }
    @DeleteMapping
    public ResponseEntity<HttpStatus> deleteChoiceArtist(@RequestBody ChoiceReqDto dto, Authentication authentication){
        return choiceService.deleteChoiceArtist(dto,authentication);
    }

}
