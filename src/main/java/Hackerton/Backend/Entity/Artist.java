package Hackerton.Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;
@Table(name="artist")
@Data
@Entity
public class Artist {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private long id;

    @Column//enum으로 대체하기
    private String genre;

    @Column//enum으로 대체하기
    private String region;

    @Column
    private String photo;

    @OneToOne
    @JoinColumn(name="USER_ID")
    private User user;


}
