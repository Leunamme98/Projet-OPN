package com.officePharmaceutiqueNationale.OPN.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "Article_type")
@Data
public abstract class Article {

    @Id
    private String id;

    private String code;
    private String libelle;
    private LocalDate dateExpiration;
    private Double prixGenerique;
    private int quantiteStockSeuil;
    private String description;
    private String cheminImage;

    @Enumerated(EnumType.STRING)
    private EtatArticle etat;

    private Boolean isDeleted;

}
