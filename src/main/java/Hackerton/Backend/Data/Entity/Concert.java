package Hackerton.Backend.Data.Entity;

import Hackerton.Backend.Data.Enum.RegionEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table
@Data
@Entity
@NoArgsConstructor
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Date concertDate;

    @Enumerated(EnumType.STRING)
    private RegionEnum region;
    private Date fundingDate;

    private Integer fundingPrice;

    private String imgUrl;

    @Builder
    public Concert(Date fundingDate, Date concertDate, RegionEnum region, Integer fundingPrice, String imgUrl){
        this.fundingDate = fundingDate;
        this.concertDate = concertDate;
        this.region = region;
        this.fundingPrice = fundingPrice;
        this.imgUrl = imgUrl;
    }
}



