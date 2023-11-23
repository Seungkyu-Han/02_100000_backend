package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.Artist.Req.ArtistInformationReq;
import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Repository.ArtistRepository;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.ArtistService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ArtistServiceImpl implements ArtistService {

    @Autowired
    private final ArtistRepository artistRepository;
    @Autowired
    private final UserRepository userRepository;

    @Override
    public Artist saveArtist(ArtistInformationReq artistinformationreq) {
        Artist artist = artistinformationreq.toEntity();

        if (artist.getId() != null) {
            return null;
        }


        return artistRepository.save(artist);
    }

}
