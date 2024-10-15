package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.LignePanierDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.LignePanierMapper;
import com.officePharmaceutiqueNationale.OPN.model.LignePanier;
import com.officePharmaceutiqueNationale.OPN.repository.LignePanierRepository;
import com.officePharmaceutiqueNationale.OPN.repository.PanierRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.LignePanierService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class LignePanierServiceImpl implements LignePanierService {

    private final LignePanierRepository lignePanierRepository;
    private final PanierRepository panierRepository;
    private final LignePanierMapper lignePanierMapper;

    public LignePanierServiceImpl(LignePanierRepository lignePanierRepository, PanierRepository panierRepository, LignePanierMapper lignePanierMapper) {
        this.lignePanierRepository = lignePanierRepository;
        this.panierRepository = panierRepository;
        this.lignePanierMapper = lignePanierMapper;
    }

    @Override
    public LignePanierDto creerLignePanier(LignePanierDto lignePanierDto) {
        LignePanier lignePanier = lignePanierMapper.toEntity(lignePanierDto);

        // Générer un nouvel ID
        String id = UUID.randomUUID().toString();
        lignePanier.setId(id);

        LignePanier savedLignePanier = lignePanierRepository.save(lignePanier);
        return lignePanierMapper.toDto(savedLignePanier);
    }

    @Override
    public LignePanierDto modifierQuantiteLignePanier(String idLignePanier, int nouvelleQuantite) {
        LignePanier lignePanier = lignePanierRepository.findById(idLignePanier)
                .orElseThrow(() -> new ResourceNotFoundException("LignePanier non trouvée avec l'ID : " + idLignePanier));

        lignePanier.setQuantite(nouvelleQuantite);
        LignePanier updatedLignePanier = lignePanierRepository.save(lignePanier);
        return lignePanierMapper.toDto(updatedLignePanier);
    }

    @Override
    public void supprimerLignePanier(String idLignePanier) {
        LignePanier lignePanier = lignePanierRepository.findById(idLignePanier)
                .orElseThrow(() -> new ResourceNotFoundException("LignePanier non trouvée avec l'ID : " + idLignePanier));

        lignePanierRepository.delete(lignePanier);
    }

    @Override
    public List<LignePanierDto> recupererLignesParPanierId(String panierId) {
        List<LignePanier> lignesPanier = lignePanierRepository.findByPanierId(panierId);
        return lignesPanier.stream()
                .map(lignePanierMapper::toDto)
                .collect(Collectors.toList());
    }
}
