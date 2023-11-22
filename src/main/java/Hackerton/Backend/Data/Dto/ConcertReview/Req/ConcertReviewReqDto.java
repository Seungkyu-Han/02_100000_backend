package Hackerton.Backend.Data.Dto.ConcertReview.Req;

import Hackerton.Backend.Data.Entity.User;
import lombok.Data;

@Data
public class ConcertReviewReqDto {
    private Long concertId;
    private String content;
}
