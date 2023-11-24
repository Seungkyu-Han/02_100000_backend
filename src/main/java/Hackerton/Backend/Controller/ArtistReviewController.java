package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.ArtistReview.Req.ArtistReviewCreateReqDto;
import Hackerton.Backend.Data.Dto.ArtistReview.Req.ArtistReviewUpdateReqDto;
import Hackerton.Backend.Data.Dto.ArtistReview.Res.ArtistReviewResDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewCreateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewUpdateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Service.ArtistReviewService;
import Hackerton.Backend.Service.Impl.ArtistReviewServiceImpl;
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

@Tag(name = "아티스트리뷰 관련API")
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review/artist")
public class ArtistReviewController {

    private ArtistReviewService artistReviewService;

    @GetMapping("/{artistId}")
    @Operation(summary = "아티스트리뷰 조회 API", description = "아티스트리뷰 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "아티스트 리뷰 조회 성공",
                    content = @Content(array = @ArraySchema(schema = @Schema(implementation = ConcertReviewResDto.class)))),
            @ApiResponse(responseCode = "404", description = "아티스트 리뷰 조회 실패",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
    })
    public ResponseEntity<List<ArtistReviewResDto>> getReview(@PathVariable Long artistId) {
        return artistReviewService.getArtistReview(artistId);
    }

    @PostMapping
    @Operation(summary = "아티스트리뷰 등록 API", description = "아티스트 리뷰 등록")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "콘서트 리뷰 등록 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "콘서트 리뷰 등록 실패",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "400", description = "콘서트 아티스트가 리뷰를 작성한 유저와 같음.",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<HttpStatus> createReview(@RequestBody ArtistReviewCreateReqDto dto, Authentication authentication) {
        return artistReviewService.createArtistReview(dto, authentication);
    }

    @PatchMapping
    @Operation(summary = "아티스트 리뷰 수정 API", description = "아티스트 리뷰 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "아티스트 리뷰 수정 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "아티스트 조회 실패",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
    })
    public ResponseEntity<HttpStatus> updateReview(@RequestBody ArtistReviewUpdateReqDto dto, Authentication authentication) {
        return artistReviewService.updateArtistReview(dto, authentication);
    }

    @DeleteMapping("/{reviewId}")
    @Operation(summary = "아티스트 리뷰 삭제 API", description = "아티스트 리뷰 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "아티스트 리뷰 삭제 성공",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
            @ApiResponse(responseCode = "404", description = "아티스트 리뷰 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class))),
    })
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Integer reviewId, Authentication authentication) {
        return artistReviewService.deleteArtistReview(reviewId, authentication);
    }


}
