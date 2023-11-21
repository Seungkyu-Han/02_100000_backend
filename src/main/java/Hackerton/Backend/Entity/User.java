package Hackerton.Backend.Entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Table(name="user")
@Data
@Entity
public class User {
    @Id
    private String id;

    @Column
    private String name;

    @Column
    private String refresh_Token;

    @Column//enum으로 설정하기
    private String content;

    @Column//enum으로 설정하기
    private String interest;
}
