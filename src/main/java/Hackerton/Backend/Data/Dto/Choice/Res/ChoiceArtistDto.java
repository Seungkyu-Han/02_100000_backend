package Hackerton.Backend.Data.Dto.Choice.Res;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Data.Enum.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
public class ChoiceArtistDto {

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


    private String imgUrl;

    private Integer follower;

    public ChoiceArtistDto(Artist artist, Integer follower){
        this.id = artist.getId();
        this.artistName = artist.getArtistName();
        this.genre = artist.getGenre();
        this.region = artist.getRegion();
        this.intro = artist.getIntro();
        this.introduction = artist.getIntroduction();
        this.imgUrl = artist.getUser().getImgUrl();
        this.follower = follower;
    }
}
