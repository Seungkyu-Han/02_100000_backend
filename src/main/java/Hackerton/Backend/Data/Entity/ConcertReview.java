package Hackerton.Backend.Data.Entity;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@Entity
@NoArgsConstructor
public class ConcertReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Concert concert;

    @ManyToOne
    private User user;

    @Column
    private String content;

    @Builder
    public ConcertReview(Concert concert, User user, String content) {
        this.concert = concert;
        this.user = user;
        this.content = content;
    }
}

