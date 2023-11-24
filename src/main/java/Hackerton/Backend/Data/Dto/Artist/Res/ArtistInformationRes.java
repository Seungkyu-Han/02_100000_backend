package Hackerton.Backend.Data.Dto.Artist.Res;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Data.Enum.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;

public class ArtistInformationRes {

    @Schema(description = "아티스트의 이름 ", example = "박종혁")
    private String artistName;

    @Schema(description = "아티스트 장르 ", example = "힙합")
    private GenreEnum genre;

    @Schema(description = "아티스트 지역 ", example = "대구")
    private RegionEnum region;

    @Schema(description = "아티스트 유튜브 Url ", example = "https://youtu.be/Z7lyMucsaHE?si=xWb2wQr-d3NMNahv")
    private String intro;

    @Schema(description = "아티스트 자기소개", example = "안녕하세요")
    private String introduction;

    @Schema(description = "유저 이미지 Url", example = "https://avatars.githubusercontent.com/u/114932050?v=4")
    private String imgUrl;

    public ArtistInformationRes(Artist artist) {
        this.artistName = artist.getArtistName();
        this.genre = artist.getGenre();
        this.region = artist.getRegion();
        this.intro = artist.getIntro();
        this.introduction = artist.getIntroduction();
    }

    public void setImgUrl(String imgUrl) {

        this.imgUrl = imgUrl;
    }

}
