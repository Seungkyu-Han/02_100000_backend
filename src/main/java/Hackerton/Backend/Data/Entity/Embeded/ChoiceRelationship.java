package Hackerton.Backend.Data.Entity.Embeded;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import jakarta.persistence.Embeddable;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@NoArgsConstructor
@Getter
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ChoiceRelationship that = (ChoiceRelationship) o;
        return Objects.equals(user, that.user) && Objects.equals(artist, that.artist);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user, artist);
    }
}
