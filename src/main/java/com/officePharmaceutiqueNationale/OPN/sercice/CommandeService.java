package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;

import java.util.List;

public interface CommandeService {

    CommandeDto recupererCommandeParId(String idCommande);
    List<CommandeDto> recupererLesCommandes();

}
