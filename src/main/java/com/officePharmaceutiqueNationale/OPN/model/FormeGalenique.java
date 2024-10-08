package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class FormeGalenique {

    @Id
    private String id;

    private String nomFormeGalenique;
    private String descriptionFormeGalenique;

    private Boolean isDeleted;
}
