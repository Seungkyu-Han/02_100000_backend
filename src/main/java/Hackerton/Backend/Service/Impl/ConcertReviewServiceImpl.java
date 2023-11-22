package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertReview;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.ConcertRepository;
import Hackerton.Backend.Repository.ConcertReviewRepository;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.ConcertReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import javax.swing.text.AbstractDocument;
import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertReviewServiceImpl implements ConcertReviewService {

    private final ConcertReviewRepository concertReviewRepository;
    private final ConcertRepository concertRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<ConcertReviewResDto>> getConcertReview(Long id) {
        List<ConcertReview> concertReviews = concertReviewRepository.findAllById(id);

        List<ConcertReviewResDto> dtos = new ArrayList<>();
        for (ConcertReview review : concertReviews) {
            ConcertReviewResDto dto = new ConcertReviewResDto(review);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
    public ResponseEntity<HttpStatus> addConcertReview(ConcertReviewReqDto dto, Authentication authentication){
        Concert concert=concertRepository.findById(dto.getConcertId());
        if(concert==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        User user= userRepository.findById(Integer.valueOf(authentication.getName()));
        if(user==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        String content=dto.getContent();
        ConcertReview concertReview=ConcertReview.builder().concert(concert).user(user).content(content).build();

        concertReviewRepository.save(concertReview);

    }
}
