package Hackerton.Backend.Data.Entity;

import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Data.Enum.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
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
    @Schema(description = "아티스트 아이디")
    private Long id;

    @Schema(description = "아티스트 이름")
    private String artistName;

    @Schema(description = "아티스트 장르")
    @Enumerated(EnumType.STRING)
    private GenreEnum genre;

    @Schema(description = "아티스트 지역")
    @Enumerated(EnumType.STRING)
    private RegionEnum region;

    @Schema(description = "아티스트 유튜브 링크")
    private String intro;

    @Schema(description = "아티스트 소개글")
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
