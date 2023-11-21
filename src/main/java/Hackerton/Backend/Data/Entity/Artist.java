package Hackerton.Backend.Data.Entity;

import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Data.Enum.RegionEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@NoArgsConstructor
@Data
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @Enumerated(EnumType.STRING)
    private RegionEnum region;

    private String imgUrl;

    @OneToOne
    @JoinColumn
    private User user;

    @Builder
    public Artist(GenreEnum genre, RegionEnum region, String imgUrl, User user){
        this.genre = genre;
        this.region = region;
        this.imgUrl = imgUrl;
        this.user = user;
    }
}
