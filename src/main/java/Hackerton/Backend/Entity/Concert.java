package Hackerton.Backend.Entity;

import jakarta.persistence.*;
import lombok.Data;

import java.util.Date;

@Table(name="concert")
@Data
@Entity
public class Concert {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//자동 생성 기능 추가(숫자가 자동으로 매겨짐)
    private long id;

    @Column
    @Temporal(TemporalType.DATE)
    private Date funding_date;

    @Column
    @Temporal(TemporalType.DATE)
    private Date concert_date;

    @Column//enum으로 대체하기
    private String concert_region;

    @Column
    private int funding_price;

    @Column
    private String concert_photo;
}



