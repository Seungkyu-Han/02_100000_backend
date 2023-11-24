package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.Artist.Req.ArtistInformationReq;
import Hackerton.Backend.Data.Dto.Artist.Req.ArtistUpdateReq;
import Hackerton.Backend.Data.Dto.Artist.Res.ArtistInformationRes;
import Hackerton.Backend.Service.ArtistService;
import Hackerton.Backend.Service.Impl.ArtistServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
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

@RestController
@RequestMapping("/api/artist")
@RequiredArgsConstructor
@Tag(name = "아티스트 관련 API")
public class ArtistController {

    private ArtistService artistService;

    @PostMapping("/create")
    @Operation(summary = "아티스트 정보 저장 API", description = "아티스트의 정보를 저장")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "아티스트 등록 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "아티스트 등록 실패",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
    })
    public ResponseEntity<HttpStatus> saveArtist(@RequestBody ArtistInformationReq artistinformationreq, @Parameter(hidden = true) Authentication authentication) {

        return artistService.saveArtist(artistinformationreq, authentication);

    }

    @GetMapping("/{artistId}")
    @Operation(summary = "아티스트 조회 API", description = "아티스트 정보를 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "아티스트 조회 성공",
                    content = @Content(schema = @Schema(implementation = ArtistInformationRes.class))),
            @ApiResponse(responseCode = "403", description = "아티스트 권한 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<ArtistInformationRes> getArtist(@PathVariable Long artistId) {
        return artistService.getArtist(artistId);
    }

    @PatchMapping("/search")
    @Operation(summary = "아티스트 ", description = "아티스트의 정보를 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<HttpStatus> updateArtist(@RequestBody ArtistUpdateReq artistUpdateReq, @PathVariable Long artistId) {
        return artistService.updateArtist(artistUpdateReq, artistId);
    }

    @GetMapping("/{artistId}/count")
    @Operation(summary = "버스킹 진행횟수", description = "버스킹 진행횟수 세기")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "횟수 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<Integer> countConcert(@PathVariable Long artistId) {
        return artistService.countConcert(artistId);
    }

}
