package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.DciDto;
import com.officePharmaceutiqueNationale.OPN.mapper.DciMapper;
import com.officePharmaceutiqueNationale.OPN.model.Dci;
import com.officePharmaceutiqueNationale.OPN.repository.DciRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.DciService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DciServiceImpl implements DciService {

    private final DciRepository dciRepository;
    private final DciMapper dciMapper;

    public DciServiceImpl(DciRepository dciRepository, DciMapper dciMapper) {
        this.dciRepository = dciRepository;
        this.dciMapper = dciMapper;
    }

    // Enregistrer un nouveau DCI
    @Override
    public DciDto enregistrerUnDCI(@Valid DciDto dciDto) {
        if (dciDto == null || dciDto.getNomDci() == null || dciDto.getNomDci().isBlank()) {
            throw new IllegalArgumentException("Le DTO du DCI ne doit pas être nul et doit contenir un nom valide");
        }

        // Génération de l'ID UUID
        String id = UUID.randomUUID().toString();

        Dci dci = dciMapper.toEntity(dciDto);

        dci.setId(id);
        dci.setIsDeleted(true);

        Dci savedDci = dciRepository.save(dci);
        return dciMapper.toDto(savedDci);
    }

    // Modifier un dci existant
    @Override
    public DciDto modifierUnDCI(@Valid DciDto dciDto) {
        if (dciDto == null || dciDto.getId() == null || dciDto.getId().isBlank()) {
            throw new IllegalArgumentException("Le DTO du DCI ne doit pas être nul et doit contenir un ID valide");
        }

        Dci existingDci = dciRepository.findById(dciDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("DCI non trouvé avec l'ID : " + dciDto.getId()));

        // Mise à jour des champs
        existingDci.setNomDci(dciDto.getNomDci());

        Dci updatedDci = dciRepository.save(existingDci);
        return dciMapper.toDto(updatedDci);
    }

    // Supprimer un Dci
    @Override
    public void supprimerUnDCI(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'ID du DCI ne doit pas être nul ou vide");
        }

        Dci dci = dciRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("DCI non trouvé avec l'ID : " + id));

        if (Boolean.TRUE.equals(dci.getIsDeleted())) {
            dciRepository.delete(dci);
        } else {
            throw new RuntimeException("Impossible de supprimer le DCI : " + id + " car il n'est pas marqué comme supprimé.");
        }
    }

    // Récupérer un Dci par son id
    @Override
    public DciDto recupererDciParId(String idDci) {
        if (idDci == null || idDci.isBlank()) {
            throw new IllegalArgumentException("L'ID du DCI ne peut pas être nul ou vide");
        }

        Dci dci = dciRepository.findById(idDci)
                .orElseThrow(() -> new ResourceNotFoundException("DCI non trouvé avec l'ID : " + idDci));

        return dciMapper.toDto(dci);
    }

    // Récupérer tous les Dci
    @Override
    public List<DciDto> recupererLesDCI() {
        List<Dci> dcis = dciRepository.findAll();
        return dciMapper.toDtoList(dcis);
    }

    // Récupérer les dci en faisant les paginations
    @Override
    public Page<DciDto> recuperationParPagination(int page, int limit) {
        if (page < 0 || limit <= 0) {
            throw new IllegalArgumentException("Les paramètres de pagination ne sont pas valides");
        }

        return dciRepository.findAll(PageRequest.of(page, limit))
                .map(dciMapper::toDto);
    }

    // Récupérer les métadonnées
    @Override
    public Page<DciDto> recuperationDesMetadonnees(int page, int limit) {
        // Implémentation en attente
        return null;
    }
}
