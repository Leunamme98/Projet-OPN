package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.MedicamentDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.MedicamentMapper;
import com.officePharmaceutiqueNationale.OPN.model.FormeGalenique;
import com.officePharmaceutiqueNationale.OPN.model.Medicament;
import com.officePharmaceutiqueNationale.OPN.model.SpecialitePharmaceutique;
import com.officePharmaceutiqueNationale.OPN.repository.FormeGaleniqueRepository;
import com.officePharmaceutiqueNationale.OPN.repository.MedicamentRepository;
import com.officePharmaceutiqueNationale.OPN.repository.SpecialitePharmaceutiqueRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.MedicamentService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class MedicamentServiceImpl implements MedicamentService {

    private final MedicamentRepository medicamentRepository;
    private final MedicamentMapper medicamentMapper;
    private final FormeGaleniqueRepository formeGaleniqueRepository;
    private final SpecialitePharmaceutiqueRepository specialitePharmaceutiqueRepository;

    public MedicamentServiceImpl(MedicamentRepository medicamentRepository, MedicamentMapper medicamentMapper, FormeGaleniqueRepository formeGaleniqueRepository, SpecialitePharmaceutiqueRepository specialitePharmaceutiqueRepository) {
        this.medicamentRepository = medicamentRepository;
        this.medicamentMapper = medicamentMapper;
        this.formeGaleniqueRepository = formeGaleniqueRepository;
        this.specialitePharmaceutiqueRepository = specialitePharmaceutiqueRepository;
    }

    // Enregistrer un nouveau médicament
    @Override
    public MedicamentDto enregistrerUnMedicament(@Valid MedicamentDto medicamentDto) {

        if (medicamentDto == null ) {
            throw new IllegalArgumentException("Le DTO du médicament ne doit pas être nul");
        }

        // Génération du UUID pour l'ID du médicament
        String medicamentId = UUID.randomUUID().toString();

        // Création de l'entité Medicament avec l'ID généré
        Medicament medicament = medicamentMapper.toEntity(medicamentDto);
        medicament.setId(medicamentId);

        // Par défaut, isDeleted est défini à true
        medicament.setIsDeleted(true);

        // Vérifier si la spécialité pharmaceutique est présente
        if (medicamentDto.getSpecialitePharmaceutique() != null && medicamentDto.getSpecialitePharmaceutique().getId() != null) {
            Optional<SpecialitePharmaceutique> optionalSpecialite = specialitePharmaceutiqueRepository.findById(medicamentDto.getSpecialitePharmaceutique().getId());
            SpecialitePharmaceutique specialite = optionalSpecialite.orElseThrow(() ->
                    new IllegalArgumentException("Spécialité pharmaceutique avec l'ID " + medicamentDto.getSpecialitePharmaceutique().getId() + " n'existe pas.")
            );

            // Mettre à jour l'attribut isDeleted de la spécialité à false pour empêcher sa suppression
            specialite.setIsDeleted(false);
            specialitePharmaceutiqueRepository.save(specialite);

            medicament.setSpecialitePharmaceutique(specialite);
        }

        // Vérifier si la forme galénique est présente
        if (medicamentDto.getFormeGalenique() != null && medicamentDto.getFormeGalenique().getId() != null) {
            Optional<FormeGalenique> optionalFormeGalenique = formeGaleniqueRepository.findById(medicamentDto.getFormeGalenique().getId());
            FormeGalenique formeGalenique = optionalFormeGalenique.orElseThrow(() ->
                    new IllegalArgumentException("Forme galénique avec l'ID " + medicamentDto.getFormeGalenique().getId() + " n'existe pas.")
            );

            // Mettre à jour l'attribut isDeleted de la forme galénique à false
            formeGalenique.setIsDeleted(false);
            formeGaleniqueRepository.save(formeGalenique);

            medicament.setFormeGalenique(formeGalenique);
        }

        // Sauvegarder l'entité médicament dans le repository
        Medicament savedMedicament = medicamentRepository.save(medicament);

        // Retourner le DTO correspondant
        return medicamentMapper.toDto(savedMedicament);
    }

    // Modifier un medicament
    @Override
    public MedicamentDto modifierUnMedicament(MedicamentDto medicamentDto) {
        // Vérification de l'ID
        if (medicamentDto == null || medicamentDto.getId() == null) {
            throw new IllegalArgumentException("L'ID du médicament ne peut pas être nul.");
        }

        // Récupérer l'entité existante
        Medicament existingMedicament = medicamentRepository.findById(medicamentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Médicament non trouvé avec l'ID : " + medicamentDto.getId()));

        // Mettre à jour les champs de l'entité
        existingMedicament.setCode(medicamentDto.getCode());
        existingMedicament.setLibelle(medicamentDto.getLibelle());
        existingMedicament.setDateExpiration(medicamentDto.getDateExpiration());
        existingMedicament.setPrixGenerique(medicamentDto.getPrixGenerique());
        existingMedicament.setQuantiteStockSeuil(medicamentDto.getQuantiteStockSeuil());
        existingMedicament.setDescription(medicamentDto.getDescription());
        existingMedicament.setCheminImage(medicamentDto.getCheminImage());
        existingMedicament.setEtat(medicamentDto.getEtat());
        existingMedicament.setConcentration(medicamentDto.getConcentration());
        existingMedicament.setUniteConcentration(medicamentDto.getUniteConcentration());

        // Mettre à jour forme galénique (si non nulle et valide)
        if (medicamentDto.getFormeGalenique() != null && medicamentDto.getFormeGalenique().getId() != null) {
            Optional<FormeGalenique> optionalForme = formeGaleniqueRepository.findById(medicamentDto.getFormeGalenique().getId());
            FormeGalenique formeGalenique = optionalForme.orElseThrow(() ->
                    new ResourceNotFoundException("Forme galénique non trouvée avec l'ID : " + medicamentDto.getFormeGalenique().getId())
            );
            existingMedicament.setFormeGalenique(formeGalenique);
        } else {
            existingMedicament.setFormeGalenique(null);
        }

        // Mettre à jour spécialité pharmaceutique (si non nulle et valide)
        if (medicamentDto.getSpecialitePharmaceutique() != null && medicamentDto.getSpecialitePharmaceutique().getId() != null) {
            Optional<SpecialitePharmaceutique> optionalSpecialite = specialitePharmaceutiqueRepository.findById(medicamentDto.getSpecialitePharmaceutique().getId());
            SpecialitePharmaceutique specialite = optionalSpecialite.orElseThrow(() ->
                    new ResourceNotFoundException("Spécialité pharmaceutique non trouvée avec l'ID : " + medicamentDto.getSpecialitePharmaceutique().getId())
            );
            existingMedicament.setSpecialitePharmaceutique(specialite);
        } else {
            existingMedicament.setSpecialitePharmaceutique(null);
        }

        // Sauvegarder les modifications
        Medicament updatedMedicament = medicamentRepository.save(existingMedicament);

        // Mapper vers le DTO
        return medicamentMapper.toDto(updatedMedicament);
    }

    // Supprimer un médicament
    @Override
    public void supprimerUnMedicament(String idMedicament) {
        if (idMedicament == null) {
            throw new IllegalArgumentException("L'ID du médicament ne peut pas être nul");
        }

        // Récupérer l'entité existante
        Medicament medicament = medicamentRepository.findById(idMedicament)
                .orElseThrow(() -> new ResourceNotFoundException("Médicament non trouvé avec l'ID : " + idMedicament));

        // Vérifier l'état de l'attribut isDeleted
        if (Boolean.FALSE.equals(medicament.getIsDeleted())) {
            throw new IllegalStateException("Le médicament ne peut être supprimé que si son état est marqué comme supprimé (isDeleted = true)");
        }

        // Supprimer l'entité
        medicamentRepository.delete(medicament);
    }

    // Récuperer un médicament existant par son id
    @Override
    public MedicamentDto recupererUnMedicamentParId(String idMedicament) {
        if (idMedicament == null) {
            throw new IllegalArgumentException("L'ID du médicament ne peut pas être nul");
        }

        // Récupérer l'entité
        Medicament medicament = medicamentRepository.findById(idMedicament)
                .orElseThrow(() -> new ResourceNotFoundException("Médicament non trouvé avec l'ID : " + idMedicament));

        // Mapper vers le DTO
        return medicamentMapper.toDto(medicament);
    }

    // Récupérer la liste des médicaments
    @Override
    public List<MedicamentDto> recupererLesMedicaments() {
        List<Medicament> medicaments = medicamentRepository.findAll();
        return medicamentMapper.toDtoList(medicaments);
    }

    // Faire la pagination
    @Override
    public Page<MedicamentDto> recuperationParPagination(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<Medicament> medicamentsPage = medicamentRepository.findAll(pageable);
        return medicamentsPage.map(medicamentMapper::toDto);
    }

    // Récupérer les métadonnées
    @Override
    public Page<MedicamentDto> recuperationDesMetadonnees(int page, int limit) {
        return null;
    }

}
