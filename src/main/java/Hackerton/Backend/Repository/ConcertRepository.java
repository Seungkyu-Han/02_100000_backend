package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.User;

import java.util.List;

public interface ConcertRepository {

    Long save(Concert concert);

    void update(Concert concert);

    void deleteById(Long id);

    Concert findById(Long id);

    Boolean checkConcertByUser(Long id, User user);

    List<Concert> findAllByArtistId(Long id);
}
