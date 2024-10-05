package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import com.officePharmaceutiqueNationale.OPN.dto.MedicamentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ArticleService {

    // Méthodes pour Medicament
    MedicamentDto enregistrerMedicament(MedicamentDto medicamentDto);
    MedicamentDto modifierMedicament(String id, MedicamentDto medicamentDto);
    MedicamentDto recupererMedicamentParId(String id);
    List<MedicamentDto> recupererTousLesMedicaments();
    void supprimerMedicament(String id);
    Page<MedicamentDto> recupererPagination(int page, int limit);

    // Méthodes pour DispositifMedical
    DispositifMedicalDto enregistrerDispositifMedical(DispositifMedicalDto dispositifMedicalDto);
    DispositifMedicalDto modifierDispositifMedical(String id, DispositifMedicalDto dispositifMedicalDto);
    DispositifMedicalDto recupererDispositifMedicalParId(String id);
    List<DispositifMedicalDto> recupererTousLesDispositifsMedicaux();
    void supprimerDispositifMedical(String id);
    Page<DispositifMedicalDto> recupererPaginationDispositif(int page, int limit);
}
