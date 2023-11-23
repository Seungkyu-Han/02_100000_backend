package Hackerton.Backend.Controller;

import Hackerton.Backend.Data.Dto.Artist.Req.ArtistInformationReq;
import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Service.Impl.ArtistServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
@Tag(name = "아티스트 관련 API")
public class ArtistController {

    @Autowired
    private ArtistServiceImpl artistservice;

        @PostMapping("/create")
    @Operation(summary = "아티스트정보 저장 API", description = "아티스트의 정보를 저장")
    public ResponseEntity<Artist> saveArtist(@RequestBody ArtistInformationReq artistinformationreq) {
        Artist artist = artistservice.saveArtist(artistinformationreq);

        return (artist != null) ?
                ResponseEntity.status(HttpStatus.OK).body(artist) :
                ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}
