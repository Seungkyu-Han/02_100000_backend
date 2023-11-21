package Hackerton.Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

@Table(name="concert_review")
@Data
@Entity
public class Concert_review {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)//자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private int id;

    @Column
    private String content;

    @OneToOne
    @JoinColumn(name="USER_ID")
    private User user;

    @OneToOne
    @JoinColumn(name="CONCERT_ID")
    private Concert concert;
}

