package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.ArtistReview.Req.ArtistReviewCreateReqDto;
import Hackerton.Backend.Data.Dto.ArtistReview.Req.ArtistReviewUpdateReqDto;
import Hackerton.Backend.Data.Dto.ArtistReview.Res.ArtistReviewResDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Data.Entity.*;
import Hackerton.Backend.Repository.ArtistRepository;
import Hackerton.Backend.Repository.ArtistReviewRepository;
import Hackerton.Backend.Repository.ConcertReviewRepository;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.ArtistReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ArtistReviewServiceImpl implements ArtistReviewService {


    private final ArtistReviewRepository artistReviewRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;

    @Override
    public ResponseEntity<List<ArtistReviewResDto>> getArtistReview(Long artistId) {
        Artist artist = artistRepository.findById(artistId).orElse(null);

        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<ArtistReview> artistReviews = artistReviewRepository.findAllByArtist(artist);

        List<ArtistReviewResDto> dtos = new ArrayList<>();

        for (ArtistReview review : artistReviews) {
            ArtistReviewResDto dto = new ArtistReviewResDto(review);
            dtos.add(dto);
        }

        return new ResponseEntity<>(dtos, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> createArtistReview(ArtistReviewCreateReqDto dto, Authentication authentication) {
        Artist artist = artistRepository.findById(dto.getArtistId()).orElse(null);
        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        User user = userRepository.findById(Integer.valueOf(authentication.getName()));
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        if (artist.getUser().getId() == user.getId()) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        String content = dto.getContent();
        ArtistReview artistReview = ArtistReview.builder()
                .artist(artist)
                .user(user)
                .content(content)
                .build();

        artistReviewRepository.save(artistReview);

        return null;
    }

    @Override
    public ResponseEntity<HttpStatus> updateArtistReview(ArtistReviewUpdateReqDto dto, Authentication authentication) {

        ArtistReview artistReview = artistReviewRepository.findById((dto.getReviewId())).orElse(null);
        if (artistReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        artistReview.setContent(dto.getContent());

        artistReviewRepository.save(artistReview);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteArtistReview(Integer reviewId, Authentication authentication) {
        ArtistReview artistReview = artistReviewRepository.findById(reviewId).orElse(null);
        if (artistReview == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        artistReviewRepository.delete(artistReview);

        return new ResponseEntity<>(HttpStatus.OK);

    }
}
