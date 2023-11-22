package Hackerton.Backend.Data.Dto.Concert.Req;

import Hackerton.Backend.Data.Enum.RegionEnum;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Date;

@Data
public class ConcertPostReq {

    @Schema(description = "버스킹 시간", example = "1999-12-04 10:00:00")
    private Date concertDate;

    @Schema(description = "지역", example = "INCHEON")
    private RegionEnum region;

    @Schema(description = "펀딩 마감 시간", example = "1999-11-24 10:00:00")
    private Date fundingDate;

    @Schema(description = "펀딩 금액", example = "203912049321093")
    private Integer fundingPrice;

    @Schema(description = "위도", example = "38")
    private Float latitude;

    @Schema(description = "경도", example = "38")
    private Float longitude;
}
