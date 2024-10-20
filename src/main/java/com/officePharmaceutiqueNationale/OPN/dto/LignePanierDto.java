package com.officePharmaceutiqueNationale.OPN.dto;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class LignePanierDto {

    private String id;
    private Integer quantite;
    private LocalDateTime dateAjout;
    private ArticleDto article;
    private PanierDto panier;

}
