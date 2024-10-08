package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.FormeGaleniqueDto;
import com.officePharmaceutiqueNationale.OPN.mapper.FormeGaleniqueMapper;
import com.officePharmaceutiqueNationale.OPN.model.FormeGalenique;
import com.officePharmaceutiqueNationale.OPN.repository.FormeGaleniqueRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.FormeGaleniqueService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class FormeGaleniqueServiceImpl implements FormeGaleniqueService {

    private final FormeGaleniqueRepository formeGaleniqueRepository;
    private final FormeGaleniqueMapper formeGaleniqueMapper;

    public FormeGaleniqueServiceImpl(FormeGaleniqueRepository formeGaleniqueRepository, FormeGaleniqueMapper formeGaleniqueMapper) {
        this.formeGaleniqueRepository = formeGaleniqueRepository;
        this.formeGaleniqueMapper = formeGaleniqueMapper;
    }

    @Override
    public FormeGaleniqueDto enregistrerUneFormeGalenique(FormeGaleniqueDto formeGaleniqueDto) {
        if (formeGaleniqueDto == null || formeGaleniqueDto.getNomFormeGalenique() == null || formeGaleniqueDto.getDescriptionFormeGalenique() == null) {
            throw new IllegalArgumentException("Les données de la forme galénique ne peuvent pas être nulles");
        }

        FormeGalenique formeGalenique = formeGaleniqueMapper.toEntity(formeGaleniqueDto);
        formeGalenique.setId(UUID.randomUUID().toString());

        // Laisser isDeleted à true lors de l'enregistrement
        formeGalenique.setIsDeleted(true);

        FormeGalenique savedForme = formeGaleniqueRepository.save(formeGalenique);
        return formeGaleniqueMapper.toDto(savedForme);
    }

    @Override
    public FormeGaleniqueDto modifierUneFormeGalenique(FormeGaleniqueDto formeGaleniqueDto) {
        if (formeGaleniqueDto == null || formeGaleniqueDto.getId() == null) {
            throw new IllegalArgumentException("L'ID de la forme galénique ne peut pas être nul");
        }

        FormeGalenique existingForme = formeGaleniqueRepository.findById(formeGaleniqueDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Forme galénique non trouvée avec l'ID : " + formeGaleniqueDto.getId()));

        existingForme.setNomFormeGalenique(formeGaleniqueDto.getNomFormeGalenique());
        existingForme.setDescriptionFormeGalenique(formeGaleniqueDto.getDescriptionFormeGalenique());

        FormeGalenique updatedForme = formeGaleniqueRepository.save(existingForme);
        return formeGaleniqueMapper.toDto(updatedForme);
    }

    @Override
    public void supprimerUneFormeGalenique(String id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la forme galénique ne peut pas être nul");
        }

        FormeGalenique formeGalenique = formeGaleniqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forme galénique non trouvée avec l'ID : " + id));

        if (Boolean.FALSE.equals(formeGalenique.getIsDeleted())) {
            throw new IllegalStateException("La forme galénique ne peut être supprimée que si son état est marqué comme supprimé (isDeleted = true)");
        }

        formeGaleniqueRepository.delete(formeGalenique);
    }

    @Override
    public FormeGaleniqueDto recupererUneFormeGaleniqueParId(String id) {
        if (id == null) {
            throw new IllegalArgumentException("L'ID de la forme galénique ne peut pas être nul");
        }

        FormeGalenique formeGalenique = formeGaleniqueRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Forme galénique non trouvée avec l'ID : " + id));

        return formeGaleniqueMapper.toDto(formeGalenique);
    }

    @Override
    public List<FormeGaleniqueDto> recupererLesFormesGaleniques() {
        List<FormeGalenique> formes = formeGaleniqueRepository.findAll();
        return formeGaleniqueMapper.toDtoList(formes);
    }

    @Override
    public Page<FormeGaleniqueDto> recuperationParPagination(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<FormeGalenique> formesPage = formeGaleniqueRepository.findAll(pageable);
        return formesPage.map(formeGaleniqueMapper::toDto);
    }

    @Override
    public Page<FormeGaleniqueDto> recuperationDesMetadonnees(int page, int limit) {
        // Méthode non implémentée
        return null;
    }
}
