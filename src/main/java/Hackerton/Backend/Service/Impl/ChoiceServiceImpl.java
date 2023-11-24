package Hackerton.Backend.Service.Impl;

import Hackerton.Backend.Data.Dto.Choice.Req.ChoiceReqDto;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceGetRankRes;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceResArtistsDto;
import Hackerton.Backend.Data.Dto.Choice.Res.ChoiceResUsersDto;
import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.Choice;
import Hackerton.Backend.Data.Entity.Embeded.ChoiceRelationship;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Repository.ArtistRepository;
import Hackerton.Backend.Repository.ChoiceRepository;
import Hackerton.Backend.Repository.UserRepository;
import Hackerton.Backend.Service.ChoiceService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ChoiceServiceImpl implements ChoiceService {

    private final ChoiceRepository choiceRepository;
    private final ArtistRepository artistRepository;
    private final UserRepository userRepository;


    public ResponseEntity<ChoiceResArtistsDto> getArtists(Authentication authentication) {

        User user = userRepository.findById(Integer.valueOf(authentication.getName()));
        if(user==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        List<Choice> choices = choiceRepository.findByIdUser(user);

        List<Artist> artists = new ArrayList<>();
        for (Choice choice : choices) {
            Artist artist = choice.getChoiceRelationship().getArtist();
            artists.add(artist);
        }
        ChoiceResArtistsDto choiceResArtistsDto = new ChoiceResArtistsDto(user, artists);

        return new ResponseEntity<>(choiceResArtistsDto, HttpStatus.OK);
    }

    public ResponseEntity<ChoiceResUsersDto> getUserNum(Authentication authentication) {
        User targetUser = userRepository.findById(Integer.valueOf(authentication.getName()));
        if(targetUser==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Artist artist = artistRepository.findByUser(targetUser).orElse(null);
        if(artist==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        List<Choice> choices = choiceRepository.findByIdArtist(artist);
        List<User> users = new ArrayList<>();
        for (Choice choice : choices) {
            User user = choice.getChoiceRelationship().getUser();
            users.add(user);
        }
        ChoiceResUsersDto choiceResUsersDto = new ChoiceResUsersDto(artist, users, users.size());

        return new ResponseEntity<>(choiceResUsersDto,HttpStatus.OK);

    }
    public ResponseEntity<HttpStatus> choiceArtist(ChoiceReqDto dto, Authentication authentication){
        User user = userRepository.findById(Integer.valueOf(authentication.getName()));
        if(user==null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        Artist artist=dto.getArtist();
        if(artist==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ChoiceRelationship choiceRelationship=ChoiceRelationship.builder()
                .user(user)
                .artist(artist)
                .build();

        Choice choice =new Choice();
        choice.setChoiceRelationship(choiceRelationship);

        choiceRepository.save(choice);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public ResponseEntity<HttpStatus> deleteChoiceArtist(ChoiceReqDto dto, Authentication authentication){
        Choice choice =choiceRepository.findByIdUserAndIdArtist(dto.getUser(),dto.getArtist()).orElse(null);
        if(choice==null){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        choiceRepository.delete(choice);

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @Override
    public ResponseEntity<List<ChoiceGetRankRes>> getChoiceRank() {
        List<ChoiceGetRankRes> choiceGetRankRes = new ArrayList<>();

        for(Object[] artist : artistRepository.findArtistChoiceRank(PageRequest.of(0, 3)))
            choiceGetRankRes.add(new ChoiceGetRankRes((String) artist[0], (Long) artist[1]));

        return new ResponseEntity<>(choiceGetRankRes, HttpStatus.OK);
    }
}
