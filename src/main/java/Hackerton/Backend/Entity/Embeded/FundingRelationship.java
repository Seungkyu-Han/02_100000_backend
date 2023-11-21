package Hackerton.Backend.Entity.Embeded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class FundingRelationship implements Serializable {
    private String userId;
    private Long concertId;

}
