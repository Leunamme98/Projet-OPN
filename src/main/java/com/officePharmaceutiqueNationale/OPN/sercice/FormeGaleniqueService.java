package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.FormeGaleniqueDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface FormeGaleniqueService {

    FormeGaleniqueDto enregistrerUneFormeGalenique(FormeGaleniqueDto formeGaleniqueDto);
    FormeGaleniqueDto modifierUneFormeGalenique(FormeGaleniqueDto formeGaleniqueDto);
    void supprimerUneFormeGalenique(String id);
    FormeGaleniqueDto recupererUneFormeGaleniqueParId(String id);
    List<FormeGaleniqueDto> recupererLesFormesGaleniques();
    Page<FormeGaleniqueDto> recuperationParPagination(int page, int limit);
    Page<FormeGaleniqueDto> recuperationDesMetadonnees(int page, int limit);

}
