package com.officePharmaceutiqueNationale.OPN.dto;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class DciDTO {

    private String idDci;

    private String nomDci;

    private List<SpecialitePharmaceutiqueDTO> specialitePharmaceutiques;
}
