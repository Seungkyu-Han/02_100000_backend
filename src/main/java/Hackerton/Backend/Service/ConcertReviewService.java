package Hackerton.Backend.Service;


import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewCreateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewUpdateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ConcertReviewService {

    ResponseEntity<List<ConcertReviewResDto>> getConcertReview(Long id);
    ResponseEntity<HttpStatus> createConcertReview(ConcertReviewCreateReqDto dto, Authentication authentication);

    ResponseEntity<HttpStatus> updateConcertReview(ConcertReviewUpdateReqDto dto, Authentication authentication);

    ResponseEntity<HttpStatus> deleteConcertReview(Integer reviewId, Authentication authentication);
}
