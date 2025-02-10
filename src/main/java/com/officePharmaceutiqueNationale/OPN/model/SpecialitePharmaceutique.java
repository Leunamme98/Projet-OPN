package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class SpecialitePharmaceutique {

    @Id
    private String id;

    private String libelleSpecialiteMedicament;
    private String nomLaboratoire;
    private Boolean isDeleted;

    @ManyToOne
    private Dci dci;
}