package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class SpecialitePharmaceutiqueDto {

    private String id;
    private String libelleSpecialiteMedicament;
    private String nomLaboratoire;

    private DciDto dci;
}
