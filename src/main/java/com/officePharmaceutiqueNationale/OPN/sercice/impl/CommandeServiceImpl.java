package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.mapper.CommandeMapper;
import com.officePharmaceutiqueNationale.OPN.model.Commande;
import com.officePharmaceutiqueNationale.OPN.repository.CommandeRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.CommandeService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;


    // Récupérer une commande par son id
    @Override
    public CommandeDto recupererCommandeParId(String idCommande) {
        // Vérifier que l'ID de la commande n'est pas nul ou vide
        if (idCommande == null || idCommande.isEmpty()) {
            throw new IllegalArgumentException("L'ID de la commande ne peut pas être nul ou vide");
        }

        // Récupérer le client existant par son ID
        Commande commandeExist = commandeRepository.findById(idCommande)
                .orElseThrow(() -> new EntityNotFoundException("Commande avec ID " + idCommande + " non trouvé"));

        // Convertir la commande en DTO et la retourner
        return commandeMapper.toDto(commandeExist);
    }

    // Récuperer toutes les commandes
    @Override
    public List<CommandeDto> recupererLesCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        return commandeMapper.toDtoList(commandes);
    }

}
