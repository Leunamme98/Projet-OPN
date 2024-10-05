package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.FormeGaleniqueDto;
import com.officePharmaceutiqueNationale.OPN.mapper.FormeGaleniqueMapper;
import com.officePharmaceutiqueNationale.OPN.model.FormeGalenique;
import com.officePharmaceutiqueNationale.OPN.repository.FormeGaleniqueRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.FormeGaleniqueService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class FormeGaleniqueServiceImpl implements FormeGaleniqueService {

    private final FormeGaleniqueRepository formeGaleniqueRepository;
    private final FormeGaleniqueMapper formeGaleniqueMapper;

    public FormeGaleniqueServiceImpl(FormeGaleniqueRepository formeGaleniqueRepository, FormeGaleniqueMapper formeGaleniqueMapper) {
        this.formeGaleniqueRepository = formeGaleniqueRepository;
        this.formeGaleniqueMapper = formeGaleniqueMapper;
    }

    @Override
    public FormeGaleniqueDto enregistrerUneFormeGalenique(FormeGaleniqueDto formeGaleniqueDto) {

        // Génération de l'id UUID
        String id = UUID.randomUUID().toString();

        FormeGalenique formeGalenique = formeGaleniqueMapper.toEntity(formeGaleniqueDto);
        formeGalenique.setId(id);

        FormeGalenique savedForme = formeGaleniqueRepository.save(formeGalenique);
        return formeGaleniqueMapper.toDto(savedForme);

    }

    @Override
    public FormeGaleniqueDto modifierUneFormeGalenique(String id, FormeGaleniqueDto formeGaleniqueDto) {
        // Récupérer l'entité existante à partir de la base de données
        FormeGalenique existingForme = formeGaleniqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forme galénique non trouvée avec l'ID : " + id));

        // Mise à jour directe des champs avec les valeurs du DTO
        existingForme.setNomFormeGalenique(formeGaleniqueDto.getNomFormeGalenique());
        existingForme.setDescriptionFormeGalenique(formeGaleniqueDto.getDescriptionFormeGalenique());

        // Sauvegarder l'entité mise à jour dans le repository
        FormeGalenique updatedForme = formeGaleniqueRepository.save(existingForme);

        // Retourner le DTO mis à jour
        return formeGaleniqueMapper.toDto(updatedForme);
    }

    @Override
    public void supprimerUneFormeGalenique(String id) {

        FormeGalenique formeGalenique = formeGaleniqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forme galénique non trouvée avec l'ID : " + id));

        formeGaleniqueRepository.delete(formeGalenique);
    }

    @Override
    public FormeGaleniqueDto recupererUneFormeGaleniqueParId(String id) {
        FormeGalenique formeGalenique = formeGaleniqueRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Forme galénique non trouvée avec l'ID : " + id));

        return formeGaleniqueMapper.toDto(formeGalenique);
    }

    @Override
    public List<FormeGaleniqueDto> recupererLesFormesGaleniques() {
        List<FormeGalenique> formes = formeGaleniqueRepository.findAll();
        return formes.stream()
                .map(formeGaleniqueMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<FormeGaleniqueDto> recuperationParPagination(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<FormeGalenique> formesPage = formeGaleniqueRepository.findAll(pageable);
        return formesPage.map(formeGaleniqueMapper::toDto);
    }

    @Override
    public Page<FormeGaleniqueDto> recuperationDesMetadonnees(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<FormeGalenique> formesPage = formeGaleniqueRepository.findAll(pageable);
        return formesPage.map(formeGaleniqueMapper::toDto);
    }
}
