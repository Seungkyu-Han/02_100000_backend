package Hackerton.Backend.Data.Dto.ConcertReview.Res;

import Hackerton.Backend.Data.Entity.ConcertReview;
import Hackerton.Backend.Data.Entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.net.UnknownServiceException;

@Data
public class ConcertReviewResDto {

    private Integer concertReviewId;

    private User user;

    private String content;

    public ConcertReviewResDto(ConcertReview review) {
        this.concertReviewId = review.getId();
        this.user = review.getUser();
        this.content = review.getContent();
    }
}


