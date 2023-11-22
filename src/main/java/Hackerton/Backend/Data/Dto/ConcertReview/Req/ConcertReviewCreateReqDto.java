package Hackerton.Backend.Data.Dto.ConcertReview.Req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ConcertReviewCreateReqDto {
    @Schema(description = "콘서트 리뷰 아이디 아님")
    private Long concertId;
    @Schema(example = "킨더조이님 노래 잘불러용")
    private String content;
}
