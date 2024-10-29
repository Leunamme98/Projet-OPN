package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.MouvementStockDto;
import com.officePharmaceutiqueNationale.OPN.model.MouvementStock;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MouvementStockMapper {

    @Mapping(target = "articleId", source = "article.id")
    MouvementStockDto toDto(MouvementStock mouvementStock);

    @Mapping(target = "article", ignore = true)
    MouvementStock toEntity(MouvementStockDto mouvementStockDto);

    List<MouvementStockDto> toDtoList(List<MouvementStock> mouvementStocks);
    List<MouvementStock> toEntityList(List<MouvementStockDto> mouvementStockDtos);

}