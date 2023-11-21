package Hackerton.Backend.Data.Entity;

import Hackerton.Backend.Data.Entity.Embeded.FundingRelationship;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class Funding {
    @EmbeddedId
    private FundingRelationship fundingRelationship;

    @Column
    private Integer fundingPrice;

    @Builder
    public Funding(FundingRelationship fundingRelationship, Integer fundingPrice){
        this.fundingRelationship = fundingRelationship;
        this.fundingPrice = fundingPrice;
    }
}
