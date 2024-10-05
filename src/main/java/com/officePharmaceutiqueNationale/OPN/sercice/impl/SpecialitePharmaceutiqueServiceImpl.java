package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.SpecialitePharmaceutiqueDto;
import com.officePharmaceutiqueNationale.OPN.mapper.SpecialitePharmaceutiqueMapper;
import com.officePharmaceutiqueNationale.OPN.model.Dci;
import com.officePharmaceutiqueNationale.OPN.model.SpecialitePharmaceutique;
import com.officePharmaceutiqueNationale.OPN.repository.DciRepository;
import com.officePharmaceutiqueNationale.OPN.repository.SpecialitePharmaceutiqueRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.SpecialitePharmaceutiqueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class SpecialitePharmaceutiqueServiceImpl implements SpecialitePharmaceutiqueService {

    private final SpecialitePharmaceutiqueRepository specialiteRepository;
    private final DciRepository dciRepository;
    private final SpecialitePharmaceutiqueMapper specialiteMapper;

    public SpecialitePharmaceutiqueServiceImpl(SpecialitePharmaceutiqueRepository specialiteRepository, DciRepository dciRepository, SpecialitePharmaceutiqueMapper specialiteMapper) {
        this.specialiteRepository = specialiteRepository;
        this.dciRepository = dciRepository;
        this.specialiteMapper = specialiteMapper;
    }

    @Override
    public SpecialitePharmaceutiqueDto enregistrerUneSpecialite(SpecialitePharmaceutiqueDto specialiteDto, String idDci) {

        // Recherche du DCI associé
        Dci dci = dciRepository.findById(idDci)
                .orElseThrow(() -> new RuntimeException("DCI non trouvé avec l'ID : " + idDci));

        // Génération du UUID pour l'ID de la spécialité pharmaceutique
        String specialiteId = UUID.randomUUID().toString();

        // Création de l'entité SpecialitePharmaceutique avec l'ID généré
        SpecialitePharmaceutique specialite = specialiteMapper.toEntity(specialiteDto);
        specialite.setId(specialiteId);
        specialite.setDci(dci);

        // Enregistrer la spécialité dans la base de données
        SpecialitePharmaceutique savedSpecialite = specialiteRepository.save(specialite);

        // Retourner l'objet DTO
        return specialiteMapper.toDto(savedSpecialite);
    }

    @Override
    public SpecialitePharmaceutiqueDto modifierUneSpecialite(SpecialitePharmaceutiqueDto specialiteDto) {
        SpecialitePharmaceutique specialite = specialiteRepository.findById(specialiteDto.getId())
                .orElseThrow(() -> new RuntimeException("Spécialité non trouvée avec l'ID : " + specialiteDto.getId()));

        specialite.setLibelleSpecialiteMedicament(specialiteDto.getLibelleSpecialiteMedicament());
        specialite.setNomLaboratoire(specialiteDto.getNomLaboratoire());

        SpecialitePharmaceutique updatedSpecialite = specialiteRepository.save(specialite);
        return specialiteMapper.toDto(updatedSpecialite);
    }

    @Override
    public void supprimerUneSpecialite(String id) {

        SpecialitePharmaceutique specialite = specialiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Spécialité non trouvée avec l'ID : " + id));

        specialiteRepository.delete(specialite);
    }

    @Override
    public List<SpecialitePharmaceutiqueDto> recupererLesSpecialites() {
        List<SpecialitePharmaceutique> specialites = specialiteRepository.findAll();
        return specialites.stream()
                .map(specialiteMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public SpecialitePharmaceutiqueDto recupererUneSpecialiteParId(String id) {
        SpecialitePharmaceutique specialite = specialiteRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Spécialité non trouvée avec l'ID : " + id));

        return specialiteMapper.toDto(specialite);
    }

    @Override
    public Page<SpecialitePharmaceutiqueDto> recuperationParPagination(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<SpecialitePharmaceutique> specialitePage = specialiteRepository.findAll(pageable);
        return specialitePage.map(specialiteMapper::toDto);
    }

    @Override
    public Page<SpecialitePharmaceutiqueDto> recuperationDesMetadonnees(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<SpecialitePharmaceutique> specialitePage = specialiteRepository.findAll(pageable);
        return specialitePage.map(specialiteMapper::toDto);
    }
}

