package Hackerton.Backend.Data.Dto.ConcertReview.Res;

import Hackerton.Backend.Data.Entity.ConcertReview;
import Hackerton.Backend.Data.Entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ConcertReviewResDto {
    @Schema(description = "콘서트 아이디 아님")
    private Integer concertReviewId;

    private User user;
    @Schema(example = "킨더조이 최고에요")
    private String content;

    public ConcertReviewResDto(ConcertReview review) {
        this.concertReviewId = review.getId();
        this.user = review.getUser();
        this.content = review.getContent();
    }
}


