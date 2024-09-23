package com.officePharmaceutiqueNationale.OPN.model;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Dci {

    @Id
    private String idDci;

    private String nomDci;

    @OneToMany(mappedBy = "dci")
    private List<SpecialitePharmaceutique> specialitePharmaceutiques;
}