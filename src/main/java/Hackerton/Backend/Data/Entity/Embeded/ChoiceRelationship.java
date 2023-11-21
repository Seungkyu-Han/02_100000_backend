package Hackerton.Backend.Data.Entity.Embeded;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class ChoiceRelationship implements Serializable {

    @ManyToOne
    private User user;

    @ManyToOne
    private Artist artist;

    @Builder
    public ChoiceRelationship(User user, Artist artist){
        this.user = user;
        this.artist = artist;
    }
}
