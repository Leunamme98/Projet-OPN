package com.officePharmaceutiqueNationale.OPN.repository;

 

import com.officePharmaceutiqueNationale.OPN.model.Article;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource
public interface ArticleRepository extends JpaRepository<Article, String> {
     
}
