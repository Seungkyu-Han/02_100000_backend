package Hackerton.Backend.Service;

import Hackerton.Backend.Data.Dto.Concert.Req.ConcertPostReq;
import Hackerton.Backend.Data.Dto.Concert.Res.ConcertGetRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ConcertService {
    ResponseEntity<ConcertGetRes> getConcert(Long id);

    ResponseEntity<HttpStatus> deleteConcert(Long id, Authentication authentication);

    ResponseEntity<ConcertGetRes> postConcert(ConcertPostReq concertPostReq, Authentication authentication);
}
