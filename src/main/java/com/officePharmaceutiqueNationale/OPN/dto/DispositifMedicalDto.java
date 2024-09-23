package com.officePharmaceutiqueNationale.OPN.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)

public class DispositifMedicalDto extends ArticleDto {

    private String paysFabrication;
}
