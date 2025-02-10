package com.officePharmaceutiqueNationale.OPN.dto;

import com.officePharmaceutiqueNationale.OPN.enums.TypeMouvement;
import lombok.Data;

import java.time.LocalDate;

@Data
public class MouvementStockDto {

    private String id;

    private int quantite;
    private TypeMouvement typeMouvement;
    private LocalDate dateMouvement;

    private ArticleDto article;
}
