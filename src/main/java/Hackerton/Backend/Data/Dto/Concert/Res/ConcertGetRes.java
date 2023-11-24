package Hackerton.Backend.Data.Dto.Concert.Res;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.ConcertPhoto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;

@Data
public class ConcertGetRes {

    @Schema(description = "콘서트 id", example = "1")
    private Long id;

    @Schema(description = "콘서트 시간", example = "2023-11-11 22:00:00")
    private Date concertDate;

    @Schema(description = "지역", example = "INCHEON")
    private String region;

    @Schema(description = "펀딩 종료 일자", example = "2023-11-10 22:00:00")
    private Date fundingDate;

    @Schema(description = "펀딩 금액", example = "220000")
    private Integer fundingPrice;

    private ConcertGetArtistRes artist;

    //위도
    @Schema(description = "위도", example = "38")
    private Float latitude;

    //경도
    @Schema(description = "경도", example = "38")
    private Float longitude;

    @Schema(description = "콘서트 관련 사진 S3 링크", example = "{'https://avatars.githubusercontent.com/u/114932050?v=4'}")
    private List<ConcertGetConcertPhotoRes> imgUrl;

    @Schema(description = "현재 펀딩 금액")
    private Integer curFundingPrice;

    public ConcertGetRes(Concert concert, Integer curFundingPrice, List<ConcertPhoto> concertPhotoList){
        this.id = concert.getId();
        this.concertDate = concert.getConcertDate();
        this.region = concert.getRegion().name();
        this.fundingDate = concert.getFundingDate();
        this.curFundingPrice = curFundingPrice;
        this.fundingPrice = concert.getFundingPrice();
        this.artist = new ConcertGetArtistRes(concert.getArtist());
        this.latitude = concert.getLatitude();
        this.longitude = concert.getLongitude();
        this.imgUrl = new ArrayList<>();
        for(ConcertPhoto concertPhoto : concertPhotoList)
            imgUrl.add(new ConcertGetConcertPhotoRes(concertPhoto));
    }

}

