package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertPhoto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConcertPhotoRepository extends JpaRepository<ConcertPhoto, Integer> {

    List<ConcertPhoto> findByConcert(Concert concert);
}
