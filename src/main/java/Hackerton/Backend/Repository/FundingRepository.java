package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.Embeded.FundingRelationship;
import Hackerton.Backend.Data.Entity.Funding;

public interface FundingRepository {

    void save(Funding funding);

    void update(Funding funding);

    Funding findById(FundingRelationship fundingRelationship);

    Integer getCurFundingByConcert(Concert concert);

    Integer getFundingPriceByConcertIdAndUserId(Long concert_id, Integer user_id);
}
