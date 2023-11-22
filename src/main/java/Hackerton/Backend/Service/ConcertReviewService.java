package Hackerton.Backend.Service;


import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ConcertReviewService {

    ResponseEntity<List<ConcertReviewResDto>> getConcertReview(Long id);
    ResponseEntity<HttpStatus> addConcertReview(ConcertReviewReqDto dto,  Authentication authentication)
}
