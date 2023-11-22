package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.Concert.Res.ConcertGetRes;
import Hackerton.Backend.Data.Dto.User.Res.UserGetRes;
import Hackerton.Backend.Service.ConcertService;
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
@RequestMapping("/api/concert")
@RequiredArgsConstructor
@Tag(name = "콘서트 관련 API")
public class ConcertController {

    private final ConcertService concertService;

    @GetMapping("/{id}")
    @Operation(summary = "콘서트정보 조회 API", description = "콘서트 id 사용하여 콘서트 정보를 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserGetRes.class)))
    })
    public ResponseEntity<ConcertGetRes> getConcert(@PathVariable Long id){
        return concertService.getConcert(id);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "콘서트정보 삭제 API", description = "콘서트 id 사용하여 콘서트 정보를 삭제")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserGetRes.class)))
    })
    public ResponseEntity<HttpStatus> deleteConcert(@PathVariable Long id, @Parameter(hidden = true) Authentication authentication){
        return concertService.deleteConcert(id, authentication);
    }



}
