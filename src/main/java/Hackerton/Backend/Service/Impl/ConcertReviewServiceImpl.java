package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewCreateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewUpdateReqDto;
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

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ConcertReviewServiceImpl implements ConcertReviewService {

    private final ConcertReviewRepository concertReviewRepository;
    private final ConcertRepository concertRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<ConcertReviewResDto>> getConcertReview(Long concertId) {
        Optional<Concert> concert = concertRepository.findById(concertId);
        if(concert.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        List<ConcertReview> concertReviews = concertReviewRepository.findAllByConcert(concert.get());

        List<ConcertReviewResDto> dtos = new ArrayList<>();
        for (ConcertReview review : concertReviews) {
            ConcertReviewResDto dto = new ConcertReviewResDto(review);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> createConcertReview(ConcertReviewCreateReqDto dto, Authentication authentication) {
        Optional<Concert> concert = concertRepository.findById(dto.getConcertId());
        if (concert.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userRepository.findById(Integer.valueOf(authentication.getName()));
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (Objects.equals(concert.get().getArtist().getUser().getId(), user.getId())) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String content = dto.getContent();
        ConcertReview concertReview = ConcertReview.builder()
                .concert(concert.get())
                .user(user)
                .content(content)
                .build();

        concertReviewRepository.save(concertReview);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> updateConcertReview(ConcertReviewUpdateReqDto dto, Authentication authentication) {
        ConcertReview concertReview = concertReviewRepository.findById((dto.getReviewId())).orElse(null);
        if (concertReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        concertReview.setContent(dto.getContent());

        concertReviewRepository.save(concertReview);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteConcertReview(Integer reviewId, Authentication authentication) {
        ConcertReview concertReview = concertReviewRepository.findById(reviewId).orElse(null);
        if (concertReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        concertReviewRepository.delete(concertReview);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
