package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.ConcertReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface ConcertReviewRepository extends JpaRepository<ConcertReview,Long> {

    List<ConcertReview> findAllById (Long id);

}
