package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findById(Long id);
    Optional<Artist> findByUser(User user);

    @Query(
            "SELECT a FROM Artist a LEFT JOIN a.user u where u.id = :userId"
    )
    Artist findByUserId(Integer userId);
}
