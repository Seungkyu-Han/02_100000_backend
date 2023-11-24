package Hackerton.Backend.Service;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;

public interface FundingService {
    ResponseEntity<Integer> getFunding(Long id, Authentication authentication);

    ResponseEntity<HttpStatus> postFunding(Long id, Integer money, Authentication authentication);
}
