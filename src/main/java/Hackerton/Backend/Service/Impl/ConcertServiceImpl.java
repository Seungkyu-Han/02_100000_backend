package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.Concert.Req.ConcertPatchReq;
import Hackerton.Backend.Data.Dto.Concert.Req.ConcertPostReq;
import Hackerton.Backend.Data.Dto.Concert.Res.ConcertGetRes;
import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertPhoto;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.ArtistRepository;
import Hackerton.Backend.Repository.ConcertPhotoRepository;
import Hackerton.Backend.Repository.ConcertRepository;
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
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final UserRepository userRepository;
    private final ConcertRepository concertRepository;
    private final ConcertPhotoRepository concertPhotoRepository;
    private final AmazonS3Client amazonS3Client;

    private final ArtistRepository artistRepository;
    @Value("${cloud.aws.s3.bucket}")
    private String bucket;


    @Override
    public ResponseEntity<ConcertGetRes> getConcert(Long id) {
        Optional<Concert> concert = concertRepository.findById(id);

        if(concert.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        List<ConcertPhoto> concertPhotoList = concertPhotoRepository.findByConcert(concert.get());

        return new ResponseEntity<>(new ConcertGetRes(concert.get(), concertPhotoList), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> deleteConcert(Long id, Authentication authentication) {

        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if(concertRepository.checkConcertByUser(id, user) == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        concertRepository.deleteById(id);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ConcertGetRes> postConcert(ConcertPostReq concertPostReq, Authentication authentication) {
        Artist artist = artistRepository.findByUserId(Integer.valueOf(authentication.getName()));

        if(artist == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        Concert concert = Concert.builder()
                .concertDate(new Date(concertPostReq.getConcertDate().getTime()))
                .artist(artist)
                .region(concertPostReq.getRegion())
                .fundingDate(new Date(concertPostReq.getFundingDate().getTime()))
                .fundingPrice(concertPostReq.getFundingPrice())
                .latitude(concertPostReq.getLatitude())
                .longitude(concertPostReq.getLongitude())
                .build();

        concertRepository.save(concert);

        for(MultipartFile multipartFile: concertPostReq.getMultipartFileList()){
            String fileName = uploadToS3(multipartFile);
            concertPhotoRepository.save(ConcertPhoto.builder()
                            .concert(concert)
                            .imgUrl(fileName)
                            .build());
        }

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<HttpStatus> patchConcert(ConcertPatchReq concertPatchReq, Authentication authentication) {
        Optional<Concert> concert = concertRepository.findById(concertPatchReq.getId());

        if(concert.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        Artist artist = artistRepository.findByUserId(Integer.valueOf(authentication.getName()));

        if(artist == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        if(!concert.get().getArtist().equals(artist))
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        concertPhotoRepository.deleteAll(concertPhotoRepository.findAllById(concertPatchReq.getDeleteFileList()));

        if (concertPatchReq.getConcertDate() != null)
            concert.get().setConcertDate(new Date(concertPatchReq.getConcertDate().getTime()));

        if (concertPatchReq.getFundingDate() != null)
            concert.get().setFundingDate(new Date(concertPatchReq.getFundingDate().getTime()));

        if(concertPatchReq.getRegion() != null)
            concert.get().setRegion(concertPatchReq.getRegion());

        if(concertPatchReq.getFundingPrice() != null)
            concert.get().setFundingPrice(concertPatchReq.getFundingPrice());

        if(concertPatchReq.getLatitude() != null)
            concert.get().setLatitude(concertPatchReq.getLatitude());

        if(concertPatchReq.getLongitude() != null)
            concert.get().setLongitude(concertPatchReq.getLongitude());

        for(MultipartFile multipartFile: concertPatchReq.getMultipartFileList()){
            String fileName = uploadToS3(multipartFile);
            concertPhotoRepository.save(ConcertPhoto.builder()
                    .concert(concert.get())
                    .imgUrl(fileName)
                    .build());
        }

        concertRepository.save(concert.get());


        return new  ResponseEntity<>(HttpStatus.OK);
    }

    private String uploadToS3(MultipartFile multipartFile){
        String uuidName = UUID.randomUUID() + "." + StringUtils.getFilenameExtension(multipartFile.getOriginalFilename());
        ObjectMetadata objectMetadata = new ObjectMetadata();
        objectMetadata.setContentType(multipartFile.getContentType());
        objectMetadata.setContentLength(multipartFile.getSize());
        try {
            amazonS3Client.putObject(bucket, uuidName, multipartFile.getInputStream(), objectMetadata);
            return uuidName;
        } catch (IOException e) {
            return null;
        }

    }

    @Override
    public ResponseEntity<List<ConcertGetRes>> getRecentConcert() {

        List<Concert> recent6Concert = concertRepository.findRecent6Concert();

        List<ConcertGetRes> result = new ArrayList<>();

        for(Concert concert : recent6Concert){
            List<ConcertPhoto> concertPhotoList = concertPhotoRepository.findByConcert(concert);
            result.add(new ConcertGetRes(concert, concertPhotoList));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ConcertGetRes>> getFundingConcert() {

        List<Concert> fundingDescConcert = concertRepository.findConcertDescFunding();

        List<ConcertGetRes> result = new ArrayList<>();

        for(Concert concert : fundingDescConcert){
            List<ConcertPhoto> concertPhotoList = concertPhotoRepository.findByConcert(concert);
            result.add(new ConcertGetRes(concert, concertPhotoList));
        }

        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
