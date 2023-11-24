package Hackerton.Backend.Data.Dto.ArtistReview.Req;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ArtistReviewUpdateReqDto {
    @Schema(description = "콘서트 아이디 아님")
    private Integer reviewId;
    @Schema(description = "킨더조이 별로에요")
    private String content;
}
