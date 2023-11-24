package Hackerton.Backend.Repository;

import Hackerton.Backend.Data.Entity.Artist;
import Hackerton.Backend.Data.Entity.User;
import Hackerton.Backend.Data.Enum.GenreEnum;
import Hackerton.Backend.Data.Enum.InterestEnum;
import Hackerton.Backend.Data.Enum.RegionEnum;
import Hackerton.Backend.Data.Enum.RoleEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArtistRepositoryTest {

    @Autowired
    private ArtistRepository artistRepository;

    @Autowired
    private UserRepository userRepository;


    @Test
    public void findArtistByUserId() {
        // given
        User user = User.builder().id(1).refreshToken("").name("박기현").role(RoleEnum.ARTIST).interest(InterestEnum.HIPHOP).build();
        Artist artist = Artist.builder().artistName("킨더조이").user(user).genre(GenreEnum.HIPHOP).intro("").region(RegionEnum.INCHEON).build();

        // when
        userRepository.save(user);
        User user1 = userRepository.findById(1);
        Artist artist1 = artistRepository.findByUserId(1);

        // then

        Assertions.assertEquals(artist1.getUser(), user1);
    }
}
