package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.Concert.Req.ConcertPatchReq;
import Hackerton.Backend.Data.Dto.Concert.Req.ConcertPostReq;
import Hackerton.Backend.Data.Dto.Concert.Res.ConcertGetRes;
import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Service.ConcertService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api/concert")
@RequiredArgsConstructor
@Tag(name = "콘서트 관련 API")
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/{id}")
    @Operation(summary = "콘서트정보 조회 API", description = "콘서트 id 사용하여 콘서트 정보를 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = ConcertGetRes.class)))
    })
    public ResponseEntity<ConcertGetRes> getConcert(@PathVariable Long id){
        return concertService.getConcert(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "콘서트정보 삭제 API", description = "콘서트 id 사용하여 콘서트 정보를 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<HttpStatus> deleteConcert(@PathVariable Long id, @Parameter(hidden = true) Authentication authentication){
        return concertService.deleteConcert(id, authentication);
    }

    @PostMapping
    @Operation(summary = "콘서트정보 생성 API", description = "콘서트 생성에 필요한 정보를 입력")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "생성 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "아티스트가 아닙니다.",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<ConcertGetRes> postConcert(
            ConcertPostReq concertPostReq,
            @Parameter(hidden = true) Authentication authentication) throws ParseException {
        return concertService.postConcert(concertPostReq, authentication);
    }

    @PatchMapping
    @Operation(summary = "콘서트 정보 수정 API", description = "콘서트의 정보를 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "400", description = "해당 콘서트가 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "403", description = "해당 콘서트를 개최한 사람이 아님",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "해당 아티스트가 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<HttpStatus> patchConcert(
            ConcertPatchReq concertPatchReq,
            @Parameter(hidden = true) Authentication authentication
    ) throws ParseException {
        return concertService.patchConcert(concertPatchReq, authentication);
    }

    @GetMapping("/recent")
    @Operation(summary = "최근 콘서트 조회 API", description = "최근 콘서트 6개를 가져옴")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConcertGetRes.class))))
    })
    public ResponseEntity<List<ConcertGetRes>> getRecentConcert(){
        return concertService.getRecentConcert();
    }

    @GetMapping("/funding")
    @Operation(summary = "가장 펀딩이 높은 5개의 콘서트 조회 API", description = "펀딩 순서대로 6개를 가져옴")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공", content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConcertGetRes.class))))
    })
    public ResponseEntity<List<ConcertGetRes>> getFundingConcert(){
        return concertService.getFundingConcert();
    }

    @GetMapping("/genre")
    @Operation(summary = "장르 별로 가장 펀딩이 높은 3개의 콘서트 조회 API", description = "장르 별로 펀딩이 높은 3개 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConcertGetRes.class))))
    })
    public ResponseEntity<List<ConcertGetRes>> getGenreConcert(GenreEnum genre){
        return concertService.getGenreConcert(genre);
    }
}
