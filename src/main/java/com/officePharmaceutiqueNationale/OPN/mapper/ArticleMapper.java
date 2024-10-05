package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import com.officePharmaceutiqueNationale.OPN.dto.MedicamentDto;
import com.officePharmaceutiqueNationale.OPN.model.DispositifMedical;
import com.officePharmaceutiqueNationale.OPN.model.Medicament;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ArticleMapper {

    MedicamentDto toDto(Medicament medicament);
    Medicament toEntity(MedicamentDto medicamentDTO);

    DispositifMedicalDto toDto(DispositifMedical dispositifMedical);
    DispositifMedical toEntity(DispositifMedicalDto dispositifMedicalDTO);
}
