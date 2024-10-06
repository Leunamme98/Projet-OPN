package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface DispositifMedicalService {

    DispositifMedicalDto enregistrerUnDispositifMedical(DispositifMedicalDto dto);
    DispositifMedicalDto modifierUnDispositifMedical(DispositifMedicalDto dto);
    void supprimerUnDispositifMedical(String idDispositif);
    DispositifMedicalDto recupererUnDispositifMedicalParId(String idDispositif);
    List<DispositifMedicalDto> recupererLesDispositifMedicaux();
    Page<DispositifMedicalDto> recuperationParPagination(int page, int limit);
}
