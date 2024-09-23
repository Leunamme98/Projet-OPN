package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DciDto {

    private String idDci;
    private String nomDci;

    private List<SpecialitePharmaceutiqueDto> specialitePharmaceutiques;
}
