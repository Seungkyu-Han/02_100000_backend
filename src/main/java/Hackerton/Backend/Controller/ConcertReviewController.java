package Hackerton.Backend.Controller;


import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Service.ConcertReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/review/concert")
public class ConcertReviewController {

    private final ConcertReviewService concertReviewService;

    @GetMapping("/{id}")
    public ResponseEntity<List<ConcertReviewResDto>> getReview(@PathVariable Integer id){
        return concertReviewService.getConcertReview(id);
    }



}
