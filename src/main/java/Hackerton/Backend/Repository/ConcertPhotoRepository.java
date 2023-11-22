package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertPhoto;

import java.util.List;

public interface ConcertPhotoRepository {

    void save(ConcertPhoto concertPhoto);

    List<ConcertPhoto> findByConcert(Concert concert);
}
