package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.MedicamentDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface MedicamentService {

    MedicamentDto enregistrerUnMedicament(MedicamentDto medicamentDto);
    MedicamentDto modifierUnMedicament(MedicamentDto medicamentDto);
    void supprimerUnMedicament(String idMedicament);
    MedicamentDto recupererUnMedicamentParId(String idMedicament);
    List<MedicamentDto> recupererLesMedicaments();
    Page<MedicamentDto> recuperationParPagination(int page, int limit);
    Page<MedicamentDto> recuperationDesMetadonnees(int page, int limit);

}
