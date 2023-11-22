package Hackerton.Backend.Data.Dto.ConcertReview.Req;

import lombok.Data;

@Data
public class ConcertReviewUpdateReqDto {

    private Integer reviewId;

    private String content;
}
