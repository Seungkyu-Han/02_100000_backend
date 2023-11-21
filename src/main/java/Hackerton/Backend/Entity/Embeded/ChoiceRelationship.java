package Hackerton.Backend.Entity.Embeded;

import jakarta.persistence.Embeddable;

import java.io.Serializable;

@Embeddable
public class ChoiceRelationship implements Serializable {

    private String userId;
    private Long artistId;
}
