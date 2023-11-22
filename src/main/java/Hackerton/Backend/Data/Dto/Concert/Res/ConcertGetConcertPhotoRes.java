package Hackerton.Backend.Data.Dto.Concert.Res;

import Hackerton.Backend.Data.Entity.ConcertPhoto;
import lombok.Data;

@Data
public class ConcertGetConcertPhotoRes {

    private Integer id;

    private String imgUrl;

    public ConcertGetConcertPhotoRes(ConcertPhoto concertPhoto){
        this.id = concertPhoto.getId();
        this.imgUrl = concertPhoto.getImgUrl();
    }
}
