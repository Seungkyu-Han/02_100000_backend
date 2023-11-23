package Hackerton.Backend.Data.Dto.Artist.Req;

import Hackerton.Backend.Data.Entity.Artist;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ArtistInformationReq {

    @Schema(description = "아티스트의 이름 ", example = "박종혁")
    private String artistName;

//    @Schema(description = "아티스트 장르 ", example = "힙합")
//    private GenreEnum genre;
//
//    @Schema(description = "아티스트 지역 ", example = "대구")
//    private RegionEnum region;
//
//    @Schema(description = "아티스트의 소개 ", example = "세상 제일 가는 가수")
//    private String intro;
//
//    @Schema(description = "아티스트 유튜브 Url ", example = "https://youtu.be/Z7lyMucsaHE?si=xWb2wQr-d3NMNahv")
//    private String youtubeUrl;
//
//    @Schema(description = "아티스트 이미지 Url", example = "https://avatars.githubusercontent.com/u/114932050?v=4")
//    private String imgUrl;

    public Artist toEntity() {
        //toEntitiy()메서드는 DTO인 form 객체를 엔티티 객체로 변환하는 역활
        return new Artist(null,artistName,null,null,null);
    }

}
