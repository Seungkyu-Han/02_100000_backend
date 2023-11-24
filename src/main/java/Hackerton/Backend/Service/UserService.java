package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.User.Req.UserPatchReq;
import Hackerton.Backend.Data.Dto.User.Res.UserGetRes;
import Hackerton.Backend.Data.Dto.User.Res.UserImgRes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface UserService {
    ResponseEntity<UserGetRes> getUser(Authentication authentication);

    ResponseEntity<UserGetRes> patchUser(UserPatchReq userPatchReq, Authentication authentication);

    ResponseEntity<UserImgRes> createImg(MultipartFile file,Authentication authentication) throws IOException;



}