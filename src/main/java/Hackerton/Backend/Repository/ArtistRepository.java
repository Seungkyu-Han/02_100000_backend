package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ArtistRepository extends JpaRepository<Artist, Long> {

    Optional<Artist> findByUser(User user);


}
