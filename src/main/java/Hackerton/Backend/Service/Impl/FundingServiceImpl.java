package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Entity.Concert;
import Hackerton.Backend.Data.Entity.Embeded.FundingRelationship;
import Hackerton.Backend.Data.Entity.Funding;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.ConcertRepository;
import Hackerton.Backend.Repository.FundingRepository;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.FundingService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FundingServiceImpl implements FundingService {

    private final FundingRepository fundingRepository;
    private final UserRepository userRepository;
    private final ConcertRepository concertRepository;

    @Override
    public ResponseEntity<Integer> getFunding(Long id, Authentication authentication) {

        Integer fundingPrice = fundingRepository.getFundingPriceByConcertIdAndUserId(id, Integer.valueOf(authentication.getName()));

        if(fundingPrice == null)
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        return new ResponseEntity<>(fundingPrice, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<HttpStatus> postFunding(Long id, Integer money, Authentication authentication) {

        Optional<Concert> concert = concertRepository.findById(id);
        User user = userRepository.findById(Integer.valueOf(authentication.getName()));

        if(concert.isEmpty())
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);

        if(user == null)
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);

        FundingRelationship fundingRelationship = new FundingRelationship(user, concert.get());

        Funding funding = fundingRepository.findById(fundingRelationship);


        if(funding == null)
        {
            Funding toFunding = Funding.builder()
                    .fundingPrice(money)
                    .fundingRelationship(fundingRelationship)
                    .build();

            fundingRepository.save(toFunding);
            return new ResponseEntity<>(HttpStatus.CREATED);
        }
        else
        {
            Funding toFunding = Funding.builder()
                    .fundingRelationship(fundingRelationship)
                    .fundingPrice(money + funding.getFundingPrice())
                    .build();

            fundingRepository.update(toFunding);

            return new ResponseEntity<>(HttpStatus.CREATED);
        }
    }
}
