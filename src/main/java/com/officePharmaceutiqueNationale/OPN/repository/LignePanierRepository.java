 package com.officePharmaceutiqueNationale.OPN.repository;


import com.officePharmaceutiqueNationale.OPN.model.Article;
import com.officePharmaceutiqueNationale.OPN.model.LignePanier;
import com.officePharmaceutiqueNationale.OPN.model.Panier;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

@Transactional
 public interface LignePanierRepository extends JpaRepository<LignePanier, String> {

     LignePanier findByPanierAndArticle(Panier panier, Article article);

     List<LignePanier> findByPanierId(String idPanier);

     @Modifying
     @Query("DELETE FROM LignePanier lp WHERE lp.panier.id = :panierId")
     void deleteByPanierId(@Param("panierId") String panierId);

 }
