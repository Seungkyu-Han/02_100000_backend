package Hackerton.Backend.Data.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Table
@Data
@Entity
@NoArgsConstructor
public class ArtistReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Artist artist;

    @ManyToOne
    private User user;


    @Column(length = 1000)
    private String content;

    @Builder
    public ArtistReview(Artist artist, User user, String content) {
        this.artist = artist;
        this.user = user;
        this.content = content;
    }
}
