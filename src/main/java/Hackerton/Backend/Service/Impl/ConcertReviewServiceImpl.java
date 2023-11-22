package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Data.Entity.ConcertReview;
import Hackerton.Backend.Repository.ConcertReviewRepository;
import Hackerton.Backend.Service.ConcertReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ConcertReviewServiceImpl implements ConcertReviewService {


    private final ConcertReviewRepository concertReviewRepository;

    @Override
    public ResponseEntity<List<ConcertReviewResDto>> getConcertReview(Integer id) {
        List<ConcertReview> concertReviews = concertReviewRepository.findAllById(id);

        List<ConcertReviewResDto> dtos = new ArrayList<>();
        for (ConcertReview review : concertReviews) {
            ConcertReviewResDto dto = new ConcertReviewResDto(review);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }
}
