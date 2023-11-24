package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.User.Req.UserPatchReq;
import Hackerton.Backend.Data.Dto.User.Res.UserGetRes;
import Hackerton.Backend.Data.Dto.User.Res.UserImgRes;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.UserService;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AmazonS3Client amazonS3Client;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Override
    public ResponseEntity<UserGetRes> getUser(Authentication authentication) {
        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        return new ResponseEntity<>(new UserGetRes(user), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserGetRes> patchUser(UserPatchReq userPatchReq, Authentication authentication) {
        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if (user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        user.setName((userPatchReq.getName() == null) ? user.getName() : userPatchReq.getName());
        user.setRole((userPatchReq.getRole() == null) ? user.getRole() : userPatchReq.getRole());
        user.setInterest((user.getInterest() == null) ? user.getInterest() : userPatchReq.getInterest());

        userRepository.update(user);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<UserImgRes> createImg(MultipartFile file, Authentication authentication) throws IOException {
        String originalFileName = file.getOriginalFilename();

        ObjectMetadata metadata = new ObjectMetadata();
        metadata.setContentLength(file.getSize());
        metadata.setContentType(file.getContentType());

        amazonS3Client.putObject(bucket, originalFileName, file.getInputStream(), metadata);
        String url = amazonS3Client.getUrl(bucket, originalFileName).toString();

        User user = userRepository.findById(Integer.valueOf(authentication.getName()));
        user.setImgUrl(url);

        UserImgRes res = new UserImgRes(url);

        return new ResponseEntity<>(res, HttpStatus.OK);
    }


}