package Hackerton.Backend.Data.Entity;


import Hackerton.Backend.Data.Enum.InterestEnum;
import Hackerton.Backend.Data.Enum.RoleEnum;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Data
@Entity
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
}
