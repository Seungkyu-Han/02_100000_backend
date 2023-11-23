package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.Artist.Req.ArtistInformationReq;
import Hackerton.Backend.Data.Entity.Artist;

public interface ArtistService {

    public Artist saveArtist(ArtistInformationReq artistinformationreq);
}
