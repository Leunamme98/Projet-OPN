package com.officePharmaceutiqueNationale.OPN.mapper;

import com.officePharmaceutiqueNationale.OPN.dto.ArticleDto;
import com.officePharmaceutiqueNationale.OPN.model.Article;
import org.mapstruct.Mapper;
 

@Mapper
public interface ArticleMapper {

     
    ArticleDto toDto(Article article);

 
    Article toEntity(ArticleDto articleDto);
}
