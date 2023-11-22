package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Embeded.FundingRelationship;
import Hackerton.Backend.Data.Entity.Funding;

public interface FundingRepository {

    void save(Funding funding);

    void update(Funding funding);

    Funding findById(FundingRelationship fundingRelationship);
}
