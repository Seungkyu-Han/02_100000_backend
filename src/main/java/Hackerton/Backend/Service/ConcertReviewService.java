package Hackerton.Backend.Service;


import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ConcertReviewService {

    ResponseEntity<List<ConcertReviewResDto>> getConcertReview(Integer id);
}
