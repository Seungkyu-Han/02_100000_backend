package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.Artist.Req.ArtistInformationReq;
import Hackerton.Backend.Data.Dto.Artist.Req.ArtistUpdateReq;
import Hackerton.Backend.Data.Dto.Artist.Res.ArtistInformationRes;
import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Data.Enum.RegionEnum;
import Hackerton.Backend.Repository.ArtistRepository;
import Hackerton.Backend.Repository.ConcertRepository;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;
    private final ConcertRepository concertRepository;

    @Override
    public ResponseEntity<HttpStatus> saveArtist(ArtistInformationReq dto, Authentication authentication) {

        User user = userRepository.findById(Integer.valueOf(authentication.getName()));
        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        String artistName = dto.getArtistName();
        GenreEnum genre = dto.getGenre();
        RegionEnum region = dto.getRegion();
        String intro = dto.getIntro();
        String introduction= dto.getIntroduction();

        Artist artist = Artist.builder()
                .artistName(artistName)
                .user(user)
                .genre(genre)
                .region(region)
                .intro(intro)
                .introduction(introduction)
                .build();

        artistRepository.save(artist);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<ArtistInformationRes> getArtist(Long id) {
        Artist artist = artistRepository.findById(id).orElse(null);
        User user=artist.getUser();

        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ArtistInformationRes artistInformationRes = new ArtistInformationRes(artist);
        artistInformationRes.setImgUrl(user.getImgUrl());

        return new ResponseEntity<>(artistInformationRes, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> updateArtist(ArtistUpdateReq artistupdatereq, Long id) {
        Artist artist = artistRepository.findById(id).orElse(null);

        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        artist.setArtistName((artistupdatereq.getArtistName() == null) ? artist.getArtistName() : artistupdatereq.getArtistName());
        artist.setRegion((artistupdatereq.getRegion() == null) ? artist.getRegion() : artistupdatereq.getRegion());
        artist.setGenre((artistupdatereq.getGenre() == null) ? artist.getGenre() : artistupdatereq.getGenre());
        artist.setIntro((artistupdatereq.getIntro() == null) ? artist.getIntro() : artistupdatereq.getIntro());
        artist.setIntroduction((artistupdatereq.getIntroduction()==null)?artist.getIntroduction():artistupdatereq.getIntroduction());

        artistRepository.save(artist);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<Integer> countConcert(Long id) {
        Artist artist = artistRepository.findById(id).orElse(null);

        if (artist == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Concert> concert = concertRepository.findAllByArtistId(id);

        return new ResponseEntity<>(concert.size(), HttpStatus.OK);
    }


}
