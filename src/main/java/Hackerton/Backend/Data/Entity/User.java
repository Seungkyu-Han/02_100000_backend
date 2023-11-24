package Hackerton.Backend.Data.Entity;


import Hackerton.Backend.Data.Enum.InterestEnum;
import Hackerton.Backend.Data.Enum.RoleEnum;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Data
@Entity
@NoArgsConstructor
public class User {

    @Id
    private Integer id;

    @Column(length = 10)
    private String name;

    private String refreshToken;

    @Enumerated(EnumType.STRING)
    private RoleEnum role;

    @Enumerated(EnumType.STRING)
    private InterestEnum interest;

    @Column
    private String imgUrl;

    @Builder
    public User(Integer id, String name, String refreshToken, RoleEnum role, InterestEnum interest){
        this.id = id;
        this.name = name;
        this.refreshToken = refreshToken;
        this.role = role;
        this.interest = interest;
    }
}
