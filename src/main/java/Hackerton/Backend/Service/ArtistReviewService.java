package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.ArtistReview.Req.ArtistReviewCreateReqDto;
import Hackerton.Backend.Data.Dto.ArtistReview.Req.ArtistReviewUpdateReqDto;
import Hackerton.Backend.Data.Dto.ArtistReview.Res.ArtistReviewResDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewCreateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewUpdateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Data.Entity.Artist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ArtistReviewService {
    ResponseEntity<List<ArtistReviewResDto>> getArtistReview(Long artistId);

    ResponseEntity<HttpStatus> createArtistReview(ArtistReviewCreateReqDto dto, Authentication authentication);

    ResponseEntity<HttpStatus> updateArtistReview(ArtistReviewUpdateReqDto dto, Authentication authentication);

    ResponseEntity<HttpStatus> deleteArtistReview(Integer reviewId, Authentication authentication);
}
