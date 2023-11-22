package Hackerton.Backend.Controller;


import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewCreateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewUpdateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Service.ConcertReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review/concert")
public class ConcertReviewController {

    private final ConcertReviewService concertReviewService;

    @GetMapping("/{concertId}")
    public ResponseEntity<List<ConcertReviewResDto>> getReview(@PathVariable Long concertId) {
        return concertReviewService.getConcertReview(concertId);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> createReview(@RequestBody ConcertReviewCreateReqDto dto, Authentication authentication) {
        return concertReviewService.createConcertReview(dto, authentication);
    }

    @PatchMapping
    public ResponseEntity<HttpStatus> updateReview(@RequestBody ConcertReviewUpdateReqDto dto, Authentication authentication) {
        return concertReviewService.updateConcertReview(dto, authentication);
    }

    @DeleteMapping("/delete/{reviewId}")
    public ResponseEntity<HttpStatus> deleteReview(@PathVariable Integer reviewId, Authentication authentication) {
        return concertReviewService.deleteConcertReview(reviewId, authentication);
    }


}
