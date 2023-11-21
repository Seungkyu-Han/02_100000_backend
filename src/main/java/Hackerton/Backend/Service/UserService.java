package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.User.Req.UserPatchReq;
import Hackerton.Backend.Data.Dto.User.Res.UserGetRes;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface UserService {
    ResponseEntity<UserGetRes> getUser(Authentication authentication);

    ResponseEntity<UserGetRes> patchUser(UserPatchReq userPatchReq, Authentication authentication);
}
