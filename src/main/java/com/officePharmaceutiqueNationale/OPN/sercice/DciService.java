package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.DciDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DciService {

    DciDto enregistrerUnDCI(DciDto dciDto);

    DciDto modifierUnDCI(DciDto dciDto);

    void supprimerUnDCI(String id);

    DciDto recupererDciParId(String idDci);

    List<DciDto> recupererLesDCI();

    Page<DciDto> recuperationParPagination(int page, int limit);

    Page<DciDto> recuperationDesMetadonnees(int page, int limit);

}
