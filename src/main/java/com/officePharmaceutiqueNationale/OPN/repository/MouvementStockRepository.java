 package com.officePharmaceutiqueNationale.OPN.repository;

import com.officePharmaceutiqueNationale.OPN.model.MouvementStock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


 public interface MouvementStockRepository extends JpaRepository<MouvementStock, String> {

    List<MouvementStock> findByArticleId(String articleId);
}
