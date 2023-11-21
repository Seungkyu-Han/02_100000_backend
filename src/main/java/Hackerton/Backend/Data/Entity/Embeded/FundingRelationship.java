package Hackerton.Backend.Data.Entity.Embeded;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class FundingRelationship implements Serializable {
    @ManyToOne
    private User user;

    @ManyToOne
    private Concert concert;

    @Builder
    public FundingRelationship(User user, Concert concert){
        this.user = user;
        this.concert = concert;
    }
}
