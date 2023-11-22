package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.Concert.Res.ConcertGetRes;
import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertPhoto;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.ConcertPhotoRepository;
import Hackerton.Backend.Repository.ConcertRepository;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.ConcertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ConcertServiceImpl implements ConcertService {

    private final UserRepository userRepository;
    private final ConcertRepository concertRepository;
    private final ConcertPhotoRepository concertPhotoRepository;


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

        return null;
    }
}
