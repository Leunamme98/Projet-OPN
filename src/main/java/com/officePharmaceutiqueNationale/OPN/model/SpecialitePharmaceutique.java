package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SpecialitePharmaceutique {

    @Id
    private String id;
    private String libelleSpecialiteMedicament;
    private String nomLaboratoire;

    @ManyToOne
    private Dci dci;
}