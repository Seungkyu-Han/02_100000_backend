package Hackerton.Backend.Data.Entity;

import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Data.Enum.RegionEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;


@Table
@Data
@Entity
@NoArgsConstructor
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date concertDate;

    @ManyToOne
    private Artist artist;

    @Enumerated(EnumType.STRING)
    private RegionEnum region;
    private Date fundingDate;

    private Integer fundingPrice;

    private GenreEnum genre;

    //위도
    private Float latitude;

    //경도
    private Float longitude;

    @Builder
    public Concert(Date fundingDate, Date concertDate, RegionEnum region, Integer fundingPrice, GenreEnum genre ,Artist artist, Float latitude, Float longitude){
        this.fundingDate = fundingDate;
        this.concertDate = concertDate;
        this.artist = artist;
        this.genre = genre;
        this.region = region;
        this.fundingPrice = fundingPrice;
        this.latitude = latitude;
        this.longitude = longitude;
    }
}



