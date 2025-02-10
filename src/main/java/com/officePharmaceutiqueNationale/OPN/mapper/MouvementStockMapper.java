package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.MouvementStockDto;
import com.officePharmaceutiqueNationale.OPN.model.MouvementStock;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MouvementStockMapper {

    MouvementStockDto toDto(MouvementStock mouvementStock);
    MouvementStock toEntity(MouvementStockDto mouvementStockDto);

    List<MouvementStockDto> toDtoList(List<MouvementStock> mouvementStocks);
    List<MouvementStock> toEntityList(List<MouvementStockDto> mouvementStockDtos);

}