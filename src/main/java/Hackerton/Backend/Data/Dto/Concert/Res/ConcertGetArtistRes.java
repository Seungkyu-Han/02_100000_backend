package Hackerton.Backend.Data.Dto.Concert.Res;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Enum.GenreEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ConcertGetArtistRes {

    @Schema(description = "아티스트 이름", example = "황현태")
    private String artistName;

    @Schema(description = "장르", example = "HIPHOP")
    private String genre;

    @Schema(description = "장르", example = "INCHEON")
    private String region;

    @Schema(description = "이미지 S3 주소", example = "https://avatars.githubusercontent.com/u/114932050?v=4")
    private String imgUrl;

    @Schema(description = "간단한 소개", example = "안녕하세요, 밴드 보컬 황현태입니다.")
    private String intro;


    public ConcertGetArtistRes(Artist artist){
        this.artistName = artist.getArtistName();
        this.genre = artist.getGenre().name();
        this.region = artist.getRegion().name();
        this.imgUrl = artist.getUser().getImgUrl();
    }

}
