package Hackerton.Backend.Controller;


import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Service.ConcertReviewService;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Parameter;
import org.springframework.data.repository.query.Param;
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

    @GetMapping("/{id}")
    public ResponseEntity<List<ConcertReviewResDto>> getReview(@PathVariable Long id){
        return concertReviewService.getConcertReview(id);
    }

    @PostMapping
    public ResponseEntity<HttpStatus> addReview(@RequestBody ConcertReviewReqDto dto, Authentication authentication){
        return concertReviewService.addConcertReview(dto, authentication);
    }




}
