package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.SpecialitePharmaceutiqueDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.DciMapper;
import com.officePharmaceutiqueNationale.OPN.mapper.SpecialitePharmaceutiqueMapper;
import com.officePharmaceutiqueNationale.OPN.model.Dci;
import com.officePharmaceutiqueNationale.OPN.model.SpecialitePharmaceutique;
import com.officePharmaceutiqueNationale.OPN.repository.DciRepository;
import com.officePharmaceutiqueNationale.OPN.repository.SpecialitePharmaceutiqueRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.SpecialitePharmaceutiqueService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@Transactional
@RequiredArgsConstructor
public class SpecialitePharmaceutiqueServiceImpl implements SpecialitePharmaceutiqueService {

    private final SpecialitePharmaceutiqueRepository specialiteRepository;
    private final SpecialitePharmaceutiqueMapper specialiteMapper;
    private final DciRepository dciRepository;

    // Enregistrer une spécialité pharmaceutique
    @Override
    public SpecialitePharmaceutiqueDto enregistrerUneSpecialite(@Valid SpecialitePharmaceutiqueDto specialiteDto) {

        if (specialiteDto == null || specialiteDto.getLibelleSpecialiteMedicament() == null
                || specialiteDto.getLibelleSpecialiteMedicament().isBlank()) {
            throw new IllegalArgumentException("Le DTO de la spécialité pharmaceutique ne doit pas être nul et doit contenir un libellé valide");
        }

        // Génération du UUID pour l'ID de la spécialité pharmaceutique
        String specialiteId = UUID.randomUUID().toString();

        // Création de l'entité SpecialitePharmaceutique avec l'ID généré
        SpecialitePharmaceutique specialite = specialiteMapper.toEntity(specialiteDto);
        specialite.setId(specialiteId);

        // Attribut isDeleted défini à True
        specialite.setIsDeleted(true);

        // Vérifier si le DCI est présent
        if (specialiteDto.getDci() != null && specialiteDto.getDci().getId() != null) {
            Optional<Dci> optionalDci = dciRepository.findById(specialiteDto.getDci().getId());
            Dci dci = optionalDci.orElseThrow(() ->
                    new IllegalArgumentException("DCI avec l'ID " + specialiteDto.getDci().getId() + " n'existe pas.")
            );

            // Mettre à jour l'attribut isDeleted du DCI en False empêchant sa suppression
            dci.setIsDeleted(false);
            dciRepository.save(dci);

            specialite.setDci(dci);
        }

        // Enregistrer la spécialité dans la base de données
        SpecialitePharmaceutique savedSpecialite = specialiteRepository.save(specialite);

        // Retourner l'objet DTO
        return specialiteMapper.toDto(savedSpecialite);
    }


    // Modifier une spécialité existante
    @Override
    public SpecialitePharmaceutiqueDto modifierUneSpecialite(@Valid SpecialitePharmaceutiqueDto specialiteDto) {

        if (specialiteDto == null || specialiteDto.getId() == null || specialiteDto.getId().isBlank()) {
            throw new IllegalArgumentException("Le DTO de la spécialité pharmaceutique ne doit pas être nul et doit contenir un ID valide");
        }

        // Récupérer la spécialité existante
        SpecialitePharmaceutique specialite = specialiteRepository.findById(specialiteDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Spécialité non trouvée avec l'ID : " + specialiteDto.getId()));

        // Modification des attributs
        specialite.setLibelleSpecialiteMedicament(specialiteDto.getLibelleSpecialiteMedicament());
        specialite.setNomLaboratoire(specialiteDto.getNomLaboratoire());

        // Modification du DCI si nécessaire
        if (specialiteDto.getDci() != null && specialiteDto.getDci().getId() != null) {

            // Vérifier si le DCI existe
            Optional<Dci> optionalDci = dciRepository.findById(specialiteDto.getDci().getId());
            Dci dci = optionalDci.orElseThrow(() ->
                    new IllegalArgumentException("DCI avec l'ID " + specialiteDto.getDci().getId() + " n'existe pas.")
            );

            // Mettre à jour le DCI de la spécialité
            specialite.setDci(dci);
        }

        // Enregistrer les modifications
        SpecialitePharmaceutique updatedSpecialite = specialiteRepository.save(specialite);

        return specialiteMapper.toDto(updatedSpecialite);
    }


    // Supprimer une spécialité pharmaceutique
    @Override
    public void supprimerUneSpecialite(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'ID de la spécialité pharmaceutique ne doit pas être nul ou vide");
        }

        SpecialitePharmaceutique specialite = specialiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spécialité non trouvée avec l'ID : " + id));

        // Vérifier l'état de l'attribut isDeleted
        if (Boolean.FALSE.equals(specialite.getIsDeleted())) {
            throw new IllegalStateException("La spécialité ne peut être supprimée que si son état est marqué comme supprimé (isDeleted = true)");
        }

        specialiteRepository.delete(specialite);
    }

    // Récupérer toutes les spécialités pharmaceutiques
    @Override
    public List<SpecialitePharmaceutiqueDto> recupererLesSpecialites() {
        List<SpecialitePharmaceutique> specialites = specialiteRepository.findAll();
        return specialiteMapper.toDtoList(specialites);
    }

    // Récupérer une spécialité par id
    @Override
    public SpecialitePharmaceutiqueDto recupererUneSpecialiteParId(String id) {
        if (id == null || id.isBlank()) {
            throw new IllegalArgumentException("L'ID de la spécialité pharmaceutique ne peut pas être nul ou vide");
        }

        SpecialitePharmaceutique specialite = specialiteRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Spécialité non trouvée avec l'ID : " + id));

        return specialiteMapper.toDto(specialite);
    }

    // Faire la pagination
    @Override
    public Page<SpecialitePharmaceutiqueDto> recuperationParPagination(int page, int limit) {
        if (page < 0 || limit <= 0) {
            throw new IllegalArgumentException("Les paramètres de pagination ne sont pas valides");
        }

        Page<SpecialitePharmaceutique> specialitePage = specialiteRepository.findAll(PageRequest.of(page, limit));
        return specialitePage.map(specialiteMapper::toDto);
    }

    // Récupérer les métadonnées
    @Override
    public Page<SpecialitePharmaceutiqueDto> recuperationDesMetadonnees(int page, int limit) {
        // Implémentation spécifique selon vos besoins
        throw new UnsupportedOperationException("Méthode recuperationDesMetadonnees non implémentée");
    }
}
