package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.Concert.Req.ConcertPostReq;
import Hackerton.Backend.Data.Dto.Concert.Res.ConcertGetRes;
import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertPhoto;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.ConcertPhotoRepository;
import Hackerton.Backend.Repository.ConcertRepository;
//import Hackerton.Backend.Repository.Impl.ArtistRepositoryImpl;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.ConcertService;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.sql.Date;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final UserRepository userRepository;
    private final ConcertRepository concertRepository;
    private final ConcertPhotoRepository concertPhotoRepository;
    private final AmazonS3Client amazonS3Client;

//    private final ArtistRepositoryImpl artistRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;
    private final String concertPhotoPath = "concertPhoto/";


    @Override
    public ResponseEntity<ConcertGetRes> getConcert(Long id) {
        Concert concert = concertRepository.findById(id);

        if(concert == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ConcertPhoto> concertPhotoList = concertPhotoRepository.findByConcert(concert);

        return new ResponseEntity<>(new ConcertGetRes(concert, concertPhotoList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteConcert(Long id, Authentication authentication) {

        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if(!concertRepository.checkConcertByUser(id, user))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        concertRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConcertGetRes> postConcert(ConcertPostReq concertPostReq, Authentication authentication) {
//        Artist artist = artistRepository.findArtistByUserId(Integer.valueOf(authentication.getName()));
//
//        if(artist == null)
//            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//
//        Concert concert = Concert.builder()
//                .concertDate(new Date(concertPostReq.getConcertDate().getTime()))
//                .artist(artist)
//                .region(concertPostReq.getRegion())
//                .fundingDate(new Date(concertPostReq.getFundingDate().getTime()))
//                .fundingPrice(concertPostReq.getFundingPrice())
//                .latitude(concertPostReq.getLatitude())
//                .longitude(concertPostReq.getLongitude())
//                .build();

//        Long concertId = concertRepository.save(concert);

        for(MultipartFile multipartFile: concertPostReq.getMultipartFileList()){
            String fileName = uploadToS3(multipartFile);
            if(fileName == null) {

                return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
            }

        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    private String uploadToS3(MultipartFile multipartFile){
        String uuidName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());;
        objectMetadata.setContentLength(multipartFile.getSize());
        try {
            amazonS3Client.putObject(bucket, uuidName, multipartFile.getInputStream(), objectMetadata);
            return uuidName;
        } catch (IOException e) {
            return null;
        }

    }
}
