package com.officePharmaceutiqueNationale.OPN.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data @NoArgsConstructor @AllArgsConstructor
@Builder
public class DciDto {
    private String id;
    private String nomDci;
    private Boolean isDeleted;
}
