package Hackerton.Backend.Data.Dto.ArtistReview.Res;

import Hackerton.Backend.Data.Entity.ArtistReview;
import Hackerton.Backend.Data.Entity.ConcertReview;
import Hackerton.Backend.Data.Entity.User;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ArtistReviewResDto {

    @Schema(description = "콘서트 아이디 아님")
    private Integer artistReviewId;

    private User user;

    @Schema(example = "킨더조이 최고에요")
    private String content;

    public ArtistReviewResDto(ArtistReview review) {
        this.artistReviewId = review.getId();
        this.user = review.getUser();
        this.content = review.getContent();
    }

}
