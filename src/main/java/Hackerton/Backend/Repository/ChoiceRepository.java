package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.Choice;
import Hackerton.Backend.Data.Entity.Embeded.ChoiceRelationship;
import Hackerton.Backend.Data.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ChoiceRepository extends JpaRepository<Choice, ChoiceRelationship> {

    @Query(
            "SELECT c from Choice c where c.choiceRelationship.user = :user"
    )
    List<Choice> findByIdUser(User user);

    @Query(
            "SELECT c from Choice c where c.choiceRelationship.artist = :artist"
    )
    List<Choice> findByIdArtist(Artist artist);

    @Query(
            "SELECT c from Choice c where c.choiceRelationship.artist = :artist AND c.choiceRelationship.user = :user"
    )
    Optional<Choice> findByIdUserAndIdArtist(User user, Artist artist);

}
