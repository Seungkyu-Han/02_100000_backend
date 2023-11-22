package Hackerton.Backend.Data.Dto.ConcertReview.Req;

import lombok.Data;

@Data
public class ConcertReviewCreateReqDto {
    private Long concertId;
    private String content;
}
