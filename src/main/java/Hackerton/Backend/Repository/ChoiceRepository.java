package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.Choice;
import Hackerton.Backend.Data.Entity.Embeded.ChoiceRelationship;
import Hackerton.Backend.Data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, ChoiceRelationship> {
    List<Choice> findByIdUser(User user);
    List<Choice> findByIdArtist(Artist artist);
    Optional<Choice> findByIdUserAndIdArtist(User user, Artist artist);

}
