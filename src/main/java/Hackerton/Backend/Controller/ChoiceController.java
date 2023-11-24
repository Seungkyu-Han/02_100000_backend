package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.Choice.Req.ChoiceReqDto;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceGetRankRes;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceResArtistsDto;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceResUsersDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Service.ChoiceService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/choice")
@RequiredArgsConstructor
@Tag(name = "찜 관련 API")
public class ChoiceController {

    private final ChoiceService choiceService;

    @GetMapping("/userId")
    @Operation(summary = "내가 찜한 아티스트들 조회API", description = "내가 찜한 아티스트 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "찜한 아티스트 조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConcertReviewResDto.class)))),
            @ApiResponse(responseCode = "403", description = "권한 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "찜한 아티스트 조회 실패",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
    })
    public ResponseEntity<ChoiceResArtistsDto> getArtists(Authentication authentication) {
        return choiceService.getArtists(authentication);
    }
    @GetMapping("/Artistname")
    @Operation(summary = "아티스트를 찜한 유저조회API", description = "아티스트를 찜한 유저 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "찜한 유저 조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChoiceResUsersDto.class)))),
            @ApiResponse(responseCode = "403", description = "권한 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "찜한 유저 조회 실패",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
    })
    public ResponseEntity<ChoiceResUsersDto> getUsers(Authentication authentication){
        return choiceService.getUserNum(authentication);
    }
    @PostMapping
    @Operation(summary = "아티스트 찜 등록API", description = "아티스트 찜 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "아티스트 찜 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "아티스트 찜 실패",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
    })
    public ResponseEntity<HttpStatus> choiceArtist(@RequestBody ChoiceReqDto dto, Authentication authentication){
        return choiceService.choiceArtist(dto,authentication);
    }
    @DeleteMapping
    @Operation(summary = "아티스트 찜 삭제API", description = "아티스트 찜 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "아티스트 찜 삭제 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "아티스트 찜 삭제 실패",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
    })
    public ResponseEntity<HttpStatus> deleteChoiceArtist(@RequestBody ChoiceReqDto dto, Authentication authentication){
        return choiceService.deleteChoiceArtist(dto,authentication);
    }

    @GetMapping("/rank")
    @Operation(summary = "아티스트 찜 랭킹 API", description = "아티스트 찜이 높은 순서대로 가져옵니다.")
    @ApiResponses(
            @ApiResponse(responseCode = "200", description = "찜한 아티스트 랭킹 조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ChoiceGetRankRes.class))))
    )
    public ResponseEntity<List<ChoiceGetRankRes>> getChoiceRank(){
        return choiceService.getChoiceRank();
    }

}
