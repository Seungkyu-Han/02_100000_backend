package Hackerton.Backend.Entity;

import Hackerton.Backend.Entity.Embeded.ChoiceRelationship;
import Hackerton.Backend.Entity.Embeded.FundingRelationship;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name="choice")
public class Choice {
    @EmbeddedId
    private ChoiceRelationship id;

    @MapsId("userId")
    @OneToOne
    @JoinColumn(name="user_id")
    private User user;

    @MapsId("artistId")
    @OneToOne
    @JoinColumn(name="artist_id")
    private Artist artist;
}
