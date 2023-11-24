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
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private Long id;

    private String artistName;

    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @Enumerated(EnumType.STRING)
    private RegionEnum region;

    private String intro;

    private String introduction;

    @OneToOne
    @JoinColumn
    private User user;

    @Builder
    public Artist(GenreEnum genre, String artistName, RegionEnum region, User user, String intro, String introduction) {
        this.genre = genre;
        this.artistName = artistName;
        this.region = region;
        this.user = user;
        this.intro = intro;
        this.introduction = introduction;
    }
}
