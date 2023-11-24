package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.ArtistReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArtistReviewRepository extends JpaRepository<ArtistReview,Integer> {

    List<ArtistReview> findAllByArtist(Artist artist);
}
