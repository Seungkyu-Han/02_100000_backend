package Hackerton.Backend.Data.Dto.Concert.Req;

import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Data.Enum.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ConcertPostReq {

    private String concertDate;

    @Schema(description = "지역", example = "INCHEON")
    private RegionEnum region;

    private String fundingDate;

    @Schema(description = "장르", example = "HIPHOP")
    private GenreEnum genre;

    @Schema(description = "펀딩 금액", example = "203912049321093")
    private Integer fundingPrice;

    @Schema(description = "위도", example = "38")
    private Float latitude;

    @Schema(description = "경도", example = "38")
    private Float longitude;

    private List<MultipartFile> multipartFileList;

    @Schema(description = "간단한 소개", example = "하이")
    private String intro;

    @Schema(description = "제목", example = "야무지다")
    private String title;

    @Schema(description = "자세한 설명", example = "야무지게 맛깔나게 해보겠습니다.")
    private String detail;

    @Schema(description = "유튜브 링크", example = "유튜브 링크")
    private String url;
}
