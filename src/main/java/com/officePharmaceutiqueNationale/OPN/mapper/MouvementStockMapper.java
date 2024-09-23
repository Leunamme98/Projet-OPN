package com.officePharmaceutiqueNationale.OPN.mapper;

import org.mapstruct.Mapper; 
import com.officePharmaceutiqueNationale.OPN.dto.MouvementStockDto;
import com.officePharmaceutiqueNationale.OPN.model.MouvementStock;

@Mapper
public interface MouvementStockMapper {

    
    MouvementStockDto toDto(MouvementStock mouvementStock);
    
     
    MouvementStock toEntity(MouvementStockDto mouvementStockDto);
}
