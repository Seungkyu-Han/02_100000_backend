package Hackerton.Backend.Controller;

import Hackerton.Backend.Service.FundingService;
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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/funding")
@RequiredArgsConstructor
@Tag(name = "펀딩 관련 API")
public class FundingController {

    private final FundingService fundingService;

    @GetMapping
    @Operation(summary = "내가 펀딩한 금액 조회", description = "해당 콘서트에게 펀딩한 금액 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "펀딩한 금액 조회",
                    content = @Content(schema = @Schema(implementation = Integer.class)))
    })
    public ResponseEntity<Integer> getFunding(Long id, @Parameter(hidden = true) Authentication authentication) {
        return fundingService.getFunding(id, authentication);
    }

    @PostMapping
    @Operation(summary = "해당 콘서트에 펀딩하기", description = "해당 콘서트에게 추가로 펀딩하기")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "펀딩 성공 조회",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<HttpStatus> postFunding(
            @Parameter(description = "해당 콘서트 아이디") Long id,
            @Parameter(description = "펀딩할 금액") Integer money,
            @Parameter(hidden = true) Authentication authentication){
        return fundingService.postFunding(id, money, authentication);
    }
}
