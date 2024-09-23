package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class MedicamentDto extends ArticleDto {

    private Double concentration;
    private String uniteConcentration;

    // Relations
    private FormeGaleniqueDto formeGalenique;
    private SpecialitePharmaceutiqueDto specialitePharmaceutique;
}
