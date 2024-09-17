package com.officePharmaceutiqueNationale.OPN.model;


import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @NoArgsConstructor @AllArgsConstructor
public class Client extends Utilisateur {

   private String nomStructure;

   private String numeroAccreditation;
}
