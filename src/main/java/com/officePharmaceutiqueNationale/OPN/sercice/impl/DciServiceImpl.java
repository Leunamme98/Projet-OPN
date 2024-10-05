package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.DciDto;
import com.officePharmaceutiqueNationale.OPN.mapper.DciMapper;
import com.officePharmaceutiqueNationale.OPN.model.Dci;
import com.officePharmaceutiqueNationale.OPN.repository.DciRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.DciService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class DciServiceImpl implements DciService {

    private final DciRepository dciRepository;
    private final DciMapper dciMapper;

    public DciServiceImpl(DciRepository dciRepository, DciMapper dciMapper) {
        this.dciRepository = dciRepository;
        this.dciMapper = dciMapper;
    }

    @Override
    public DciDto enregistrerUnDCI(DciDto dciDto) {
        Dci dci = dciMapper.toEntity(dciDto);

        // Générer un identifiant unique avec UUID
        dci.setId(UUID.randomUUID().toString());

        Dci savedDci = dciRepository.save(dci);
        return dciMapper.toDto(savedDci);
    }

    @Override
    public DciDto modifierUnDCI(DciDto dciDto) {
        return dciMapper.toDto(dciRepository.save(dciMapper.toEntity(dciDto)));
       }

    @Override
    public void supprimerUnDCI(String id) {
        // Recherche du DCI par ID
        Dci dci = dciRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DCI non trouvé avec l'ID : " + id));

        // Vérification de l'état de suppression
        if (dci.getIsDeleted()) {
            dciRepository.delete(dci);
        } else {
            throw new RuntimeException("Impossible de supprimer le DCI : " + id + " car il n'est pas marqué comme supprimé.");
        }
    }

    @Override
    public DciDto recupererDciParId(String idDci) {
        return dciRepository.findById(idDci)
                .map(dciMapper::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("DCI non trouvé avec l'ID : " + idDci));
    }

    @Override
    public List<DciDto> recupererLesDCI() {
        return dciRepository.findAll().stream()
                .map(dciMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DciDto> recuperationParPagination(int page, int limit) {
        return dciRepository.findAll(PageRequest.of(page, limit))
                .map(dciMapper::toDto);
    }

    @Override
    public Page<DciDto> recuperationDesMetadonnees(int page, int limit) {
        return dciRepository.findAll(PageRequest.of(page, limit))
                .map(dciMapper::toDto);
    }
}
