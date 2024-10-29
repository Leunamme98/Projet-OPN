package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.MouvementStockDto;

import java.util.List;

public interface MouvementStockService {

    MouvementStockDto enregistrerMouvement(MouvementStockDto mouvementStockDto);
    List<MouvementStockDto> obtenirMouvementsParArticle(String articleId);
    MouvementStockDto obtenirMouvementParId(String mouvementId);
    List<MouvementStockDto> obtenirTousLesMouvements();

}
