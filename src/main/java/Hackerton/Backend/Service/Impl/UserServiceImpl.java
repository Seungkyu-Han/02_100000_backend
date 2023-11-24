package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.User.Req.UserPatchReq;
import Hackerton.Backend.Data.Dto.User.Res.UserGetRes;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class    UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Override
    public ResponseEntity<UserGetRes> getUser(Authentication authentication) {
        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if(user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(new UserGetRes(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserGetRes> patchUser(UserPatchReq userPatchReq, Authentication authentication) {
        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if(user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        user.setName((userPatchReq.getName() == null) ? user.getName() : userPatchReq.getName());
        user.setRole((userPatchReq.getRole() == null) ? user.getRole() : userPatchReq.getRole());
        user.setInterest((user.getInterest() == null) ? user.getInterest() : userPatchReq.getInterest());

        userRepository.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }
}
