package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.User;

public interface ConcertRepository {

    void save(Concert concert);

    void update(Concert concert);

    void deleteById(Long id);

    Concert findById(Long id);

    Boolean checkConcertByUser(Long id, User user);
}
