package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Panier {

    @Id
    private String id;

    @ManyToOne
    private Client client;

    @OneToMany(mappedBy = "panier")
    private List<LignePanier> lignePaniers;
}
