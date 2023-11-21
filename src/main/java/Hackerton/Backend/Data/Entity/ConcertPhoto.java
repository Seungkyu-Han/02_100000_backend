package Hackerton.Backend.Data.Entity;


import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class ConcertPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    private Concert concert;

    private String imgUrl;

    @Builder
    public ConcertPhoto(Concert concert, String imgUrl){
        this.concert = concert;
        this.imgUrl = imgUrl;
    }
}
