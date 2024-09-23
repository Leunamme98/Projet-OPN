package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SpecialitePharmaceutique {

    @Id
    private String idSpecialiteMedicament;

    private String libelleSpecialiteMedicament;
    private String nomLaboratoire;

    @OneToMany(mappedBy = "specialitePharmaceutique")
    private List<Medicament> Medicaments;

    @ManyToOne
    private Dci dci;
}
