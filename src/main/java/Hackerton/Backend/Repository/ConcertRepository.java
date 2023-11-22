package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;

public interface ConcertRepository {

    void save(Concert concert);

    void update(Concert concert);

    Concert findById(Long id);
}
