package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Data.Enum.GenreEnum;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ConcertRepository extends JpaRepository<Concert, Long> {

    Optional<Concert> findById(Long id);


    @Query(
            "SELECT c FROM Concert c " +
                    "LEFT JOIN c.artist a " +
                    "LEFT JOIN a.user u " +
                    "WHERE u = :user AND c.id = :id"
    )
    Concert checkConcertByUser(Long id, User user);


    @Query(
            "SELECT c FROM Concert c " +
                    "LEFT JOIN c.artist a " +
                    "LEFT JOIN a.user u " +
                    "WHERE u.id = :id"
    )
    List<Concert> findAllByArtistId(Long id);

    @Query(
            "SELECT c FROM Concert c " +
                    "ORDER BY c.id desc "
    )
    List<Concert> findRecent6Concert(Pageable pageable);

    @Query(
            "SELECT c FROM Concert c " +
                    "LEFT JOIN Funding f ON f.fundingRelationship.concert.id = c.id " +
                    "GROUP BY c.id, c.fundingPrice ORDER BY (SUM(f.fundingPrice) / c.fundingPrice) desc"
    )
    List<Concert> findConcertDescFunding(Pageable pageable);

    @Query(
            "SELECT c FROM Concert c " +
                    "LEFT JOIN Funding f ON f.fundingRelationship.concert.id = c.id " +
                    "WHERE c.genre = :genre " +
                    "GROUP BY c.id, c.fundingPrice ORDER BY (SUM(f.fundingPrice) / c.fundingPrice) desc"
    )
    List<Concert> findConcertDescFundingByGenre(GenreEnum genre, Pageable pageable);
}
