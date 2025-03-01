package com.officePharmaceutiqueNationale.OPN.model;
import com.officePharmaceutiqueNationale.OPN.enums.TypeMouvement;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class MouvementStock {

    @Id
    private String id;

    private int quantite;
    private TypeMouvement typeMouvement;
    private LocalDate dateMouvement;

    @ManyToOne
    private Article article;

}
