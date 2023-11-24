package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.Artist.Req.ArtistInformationReq;
import Hackerton.Backend.Data.Dto.Artist.Req.ArtistUpdateReq;
import Hackerton.Backend.Data.Dto.Artist.Res.ArtistInformationRes;
import Hackerton.Backend.Data.Dto.ConcertReview.Req.ConcertReviewUpdateReqDto;
import Hackerton.Backend.Data.Dto.ConcertReview.Res.ConcertReviewResDto;
import Hackerton.Backend.Data.Entity.Artist;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ArtistService {

    ResponseEntity<HttpStatus> saveArtist(ArtistInformationReq artistinformationreq, Authentication authentication);

    ResponseEntity<ArtistInformationRes> getArtist(Long id);

    ResponseEntity<HttpStatus> updateArtist(ArtistUpdateReq artistupdatereq,Long id);

    ResponseEntity<Integer>countConcert(Long artistId);
}
