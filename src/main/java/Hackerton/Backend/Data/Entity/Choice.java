package Hackerton.Backend.Data.Entity;

import Hackerton.Backend.Data.Entity.Embeded.ChoiceRelationship;
import jakarta.persistence.*;
import lombok.Data;

@Table
@Entity
@Data
public class Choice {

    @EmbeddedId
    private ChoiceRelationship choiceRelationship;

}
