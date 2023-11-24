package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.User;
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
}
