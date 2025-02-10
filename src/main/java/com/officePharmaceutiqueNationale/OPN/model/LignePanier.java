package com.officePharmaceutiqueNationale.OPN.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Data
@AllArgsConstructor @NoArgsConstructor
public class LignePanier {

    @Id
    private String id;

    private Integer quantite;
    private LocalDateTime dateAjout;

    @ManyToOne(cascade = CascadeType.ALL)
    private Panier panier;

    @OneToOne
    private Article article;

}
