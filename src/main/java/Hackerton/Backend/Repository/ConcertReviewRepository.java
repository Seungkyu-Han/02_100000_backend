package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface ConcertReviewRepository extends JpaRepository<ConcertReview, Integer> {

    List<ConcertReview> findAllByConcert(Concert concert);

    Optional<ConcertReview> findById(Integer id);
}
