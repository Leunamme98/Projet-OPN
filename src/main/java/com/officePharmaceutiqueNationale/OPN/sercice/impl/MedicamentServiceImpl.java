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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
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

    @Override
    public MedicamentDto enregistrerUnMedicament(MedicamentDto medicamentDto, String idFormeGalenique, String idSpecialitePharmaceutique) {

        // Vérification des entrées
        if (medicamentDto == null || idFormeGalenique == null || idSpecialitePharmaceutique == null) {
            throw new IllegalArgumentException("Les informations du médicament et des identifiants ne doivent pas être nulles.");
        }

        // Mapping du DTO vers l'entité
        Medicament medicament = medicamentMapper.toEntity(medicamentDto);

        // Génération d'un ID pour le médicament
        medicament.setId(UUID.randomUUID().toString());

        // Récupération et validation de la forme galénique
        FormeGalenique formeGalenique = formeGaleniqueRepository.findById(idFormeGalenique)
                .orElseThrow(() -> new ResourceNotFoundException("Forme galénique non trouvée pour l'ID : " + idFormeGalenique));

        // Récupération et validation de la spécialité pharmaceutique
        SpecialitePharmaceutique specialitePharmaceutique = specialitePharmaceutiqueRepository.findById(idSpecialitePharmaceutique)
                .orElseThrow(() -> new ResourceNotFoundException("Spécialité pharmaceutique non trouvée pour l'ID : " + idSpecialitePharmaceutique));

        // Association des entités avec le médicament
        medicament.setFormeGalenique(formeGalenique);
        medicament.setSpecialitePharmaceutique(specialitePharmaceutique);

        // Enregistrement dans la base de données
        Medicament medicamentEnregistre = medicamentRepository.save(medicament);

        // Retour du DTO correspondant
        return medicamentMapper.toDto(medicamentEnregistre);
    }


    @Override
    public MedicamentDto modifierUnMedicament(MedicamentDto medicamentDto) {
        // Vérification de l'ID
        if (medicamentDto == null || medicamentDto.getId() == null) {
            throw new IllegalArgumentException("L'ID du médicament ne peut pas être nul.");
        }

        // Récupération du médicament existant à partir de l'ID
        Medicament medicamentExistant = medicamentRepository.findById(medicamentDto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Médicament non trouvé pour l'ID : " + medicamentDto.getId()));

        // Mise à jour des attributs
        medicamentExistant.setCode(medicamentDto.getCode());
        medicamentExistant.setLibelle(medicamentDto.getLibelle());
        medicamentExistant.setDateExpiration(medicamentDto.getDateExpiration());
        medicamentExistant.setPrixGenerique(medicamentDto.getPrixGenerique());
        medicamentExistant.setQuantiteStockSeuil(medicamentDto.getQuantiteStockSeuil());
        medicamentExistant.setDescription(medicamentDto.getDescription());
        medicamentExistant.setEtat(medicamentDto.getEtat());
        medicamentExistant.setConcentration(medicamentDto.getConcentration());
        medicamentExistant.setUniteConcentration(medicamentDto.getUniteConcentration());

        // Récupération et mise à jour des relations (forme galénique et spécialité pharmaceutique)
        if (medicamentDto.getFormeGalenique() != null && medicamentDto.getFormeGalenique().getId() != null) {
            FormeGalenique formeGalenique = formeGaleniqueRepository.findById(medicamentDto.getFormeGalenique().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Forme galénique non trouvée pour l'ID : " + medicamentDto.getFormeGalenique().getId()));
            medicamentExistant.setFormeGalenique(formeGalenique);
        }

        if (medicamentDto.getSpecialitePharmaceutique() != null && medicamentDto.getSpecialitePharmaceutique().getId() != null) {
            SpecialitePharmaceutique specialitePharmaceutique = specialitePharmaceutiqueRepository.findById(medicamentDto.getSpecialitePharmaceutique().getId())
                    .orElseThrow(() -> new ResourceNotFoundException("Spécialité pharmaceutique non trouvée pour l'ID : " + medicamentDto.getSpecialitePharmaceutique().getId()));
            medicamentExistant.setSpecialitePharmaceutique(specialitePharmaceutique);
        }

        // Sauvegarde des modifications dans la base de données
        Medicament medicamentMisAJour = medicamentRepository.save(medicamentExistant);

        // Retour du DTO correspondant à l'entité mise à jour
        return medicamentMapper.toDto(medicamentMisAJour);
    }


    @Override
    public void supprimerUnMedicament(String idMedicament) {
        // Vérification de l'existence du médicament
        Medicament medicament = medicamentRepository.findById(idMedicament)
                .orElseThrow(() -> new ResourceNotFoundException("Médicament non trouvé pour l'ID : " + idMedicament));

        // Suppression du médicament
        medicamentRepository.delete(medicament);
    }

    @Override
    public MedicamentDto recupererUnMedicamentParId(String idMedicament) {
        // Récupération du médicament depuis la base de données
        Medicament medicament = medicamentRepository.findById(idMedicament)
                .orElseThrow(() -> new ResourceNotFoundException("Médicament non trouvé pour l'ID : " + idMedicament));

        // Conversion en DTO et retour
        return medicamentMapper.toDto(medicament);
    }

    @Override
    public List<MedicamentDto> recupererLesMedicaments() {
        // Récupération de la liste de tous les médicaments
        List<Medicament> medicaments = medicamentRepository.findAll();

        // Conversion de la liste d'entités en liste de DTO
        return medicaments.stream()
                .map(medicamentMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<MedicamentDto> recuperationParPagination(int page, int limit) {
        // Création d'un objet Pageable pour la pagination
        Pageable pageable = PageRequest.of(page, limit);

        // Récupération des médicaments avec pagination
        Page<Medicament> medicamentsPage = medicamentRepository.findAll(pageable);

        // Conversion de la page d'entités en page de DTO
        return medicamentsPage.map(medicamentMapper::toDto);
    }

}
