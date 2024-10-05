package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FormeGaleniqueDto {

    private String id;
    private String nomFormeGalenique;
    private String descriptionFormeGalenique;

}
