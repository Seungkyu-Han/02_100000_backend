package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.User.Req.UserPatchReq;
import Hackerton.Backend.Data.Dto.User.Res.UserGetRes;
import Hackerton.Backend.Data.Dto.User.Res.UserImgRes;
import Hackerton.Backend.Service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "유저 관련 API")
public class UserController {

    private final UserService userService;

    @GetMapping
    @Operation(summary = "유저정보 조회 API", description = "AccessToken 사용하여 유저 정보를 조회")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "조회 성공",
                    content = @Content(schema = @Schema(implementation = UserGetRes.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<UserGetRes> getUser(@Parameter(hidden = true)Authentication authentication){
        return userService.getUser(authentication);
    }

    @PatchMapping
    @Operation(summary = "유저정보 수정 API", description = "사용자의 정보를 수정")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "수정 성공",
                    content = @Content(schema = @Schema(implementation = UserGetRes.class))),
            @ApiResponse(responseCode = "403", description = "권한 없음",
                    content = @Content(schema = @Schema(implementation = HttpStatus.class)))
    })
    public ResponseEntity<UserGetRes> patchUser(@RequestBody UserPatchReq userPatchReq, @Parameter(hidden = true)Authentication authentication){
        return userService.patchUser(userPatchReq, authentication);
    }

    @PostMapping(value = "/img",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "응답 성공"),
            @ApiResponse(responseCode = "403", description = "인증 실패")
    })
    public ResponseEntity<UserImgRes> createImg(
            @RequestPart(value = "multipartFile") MultipartFile multipartFile,Authentication authentication
    ) throws IOException {
        return userService.createImg(multipartFile,authentication);
    }





}