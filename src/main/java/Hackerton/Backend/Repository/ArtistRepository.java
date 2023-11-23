package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Artist;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArtistRepository extends JpaRepository<Artist, Long> {
}
