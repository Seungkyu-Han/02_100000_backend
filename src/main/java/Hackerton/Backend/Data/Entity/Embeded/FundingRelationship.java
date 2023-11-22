package Hackerton.Backend.Data.Entity.Embeded;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Data
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FundingRelationship that = (FundingRelationship) o;
        return Objects.equals(user, that.user) && Objects.equals(concert, that.concert);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, concert);
    }
}
