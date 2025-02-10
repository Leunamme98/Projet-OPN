package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.SpecialitePharmaceutiqueDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface SpecialitePharmaceutiqueService {

    SpecialitePharmaceutiqueDto enregistrerUneSpecialite(SpecialitePharmaceutiqueDto specialitePharmaceutiqueDto);

    SpecialitePharmaceutiqueDto modifierUneSpecialite(SpecialitePharmaceutiqueDto specialiteDto);

    void supprimerUneSpecialite(String id);

    List<SpecialitePharmaceutiqueDto> recupererLesSpecialites();

    SpecialitePharmaceutiqueDto recupererUneSpecialiteParId(String id);

    Page<SpecialitePharmaceutiqueDto> recuperationParPagination(int page, int limit);

    Page<SpecialitePharmaceutiqueDto> recuperationDesMetadonnees(int page, int limit);

}
