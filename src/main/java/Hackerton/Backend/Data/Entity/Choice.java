package Hackerton.Backend.Data.Entity;

import Hackerton.Backend.Data.Entity.Embeded.ChoiceRelationship;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table
@Entity
@Data
@NoArgsConstructor
public class Choice {

    @EmbeddedId
    private ChoiceRelationship choiceRelationship;

}
