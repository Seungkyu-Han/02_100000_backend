package Hackerton.Backend.Entity;

import Hackerton.Backend.Entity.Embeded.FundingRelationship;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="funding")
public class Funding {
    @EmbeddedId
    private FundingRelationship id;

    @MapsId("userId")
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @MapsId("concertId")
    @OneToOne
    @JoinColumn(name="concert_id")
    private Concert concert;

    @Column
    private int funding_price;
}
