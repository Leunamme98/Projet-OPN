package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.model.TypeMouvement;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MouvementStockDto {

    private String id;

    private int quantite;
    private TypeMouvement typeMouvement;
    private LocalDate dateMouvement;

    private String articleId;
}
