package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.model.DispositifMedical;
import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import com.officePharmaceutiqueNationale.OPN.mapper.DispositifMedicalMapper;
import com.officePharmaceutiqueNationale.OPN.repository.DispositifMedicalRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.DispositifMedicalService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class DispositifMedicalServiceImpl implements DispositifMedicalService {

    private final DispositifMedicalRepository dispositifRepository;
    private final DispositifMedicalMapper dispositifMapper;

    public DispositifMedicalServiceImpl(DispositifMedicalRepository dispositifRepository, DispositifMedicalMapper dispositifMapper) {
        this.dispositifRepository = dispositifRepository;
        this.dispositifMapper = dispositifMapper;
    }

    @Override
    public DispositifMedicalDto enregistrerUnDispositifMedical(DispositifMedicalDto dto) {

        // Convertir le DTO en entité
        DispositifMedical dispositifMedical = dispositifMapper.toEntity(dto);

        // Générer un ID unique pour le dispositif médical
        dispositifMedical.setId(UUID.randomUUID().toString());

        // Enregistrer l'entité dans le repository
        DispositifMedical dispositifEnregistre = dispositifRepository.save(dispositifMedical);

        // Convertir l'entité enregistrée en DTO et la retourner
        return dispositifMapper.toDto(dispositifEnregistre);
    }

    @Override
    public DispositifMedicalDto modifierUnDispositifMedical(DispositifMedicalDto dto) {

        // Vérifier que l'ID du DTO n'est pas null ou vide
        if (dto.getId() == null || dto.getId().isEmpty()) {
            throw new IllegalArgumentException("L'ID du dispositif médical est requis pour la modification.");
        }

        // Récupérer le dispositif médical existant par son ID
        DispositifMedical dispositifMedicalExistant = dispositifRepository.findById(dto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Dispositif médical avec ID " + dto.getId() + " non trouvé."));

        // Mettre à jour les champs de l'entité à partir du DTO
        dispositifMedicalExistant.setCode(dto.getCode());
        dispositifMedicalExistant.setLibelle(dto.getLibelle());
        dispositifMedicalExistant.setDateExpiration(dto.getDateExpiration());
        dispositifMedicalExistant.setPrixGenerique(dto.getPrixGenerique());
        dispositifMedicalExistant.setQuantiteStockSeuil(dto.getQuantiteStockSeuil());
        dispositifMedicalExistant.setDescription(dto.getDescription());
        dispositifMedicalExistant.setEtat(dto.getEtat());
        dispositifMedicalExistant.setPaysFabrication(dto.getPaysFabrication());

        // Sauvegarder les modifications dans la base de données
        DispositifMedical dispositifMedicalModifie = dispositifRepository.save(dispositifMedicalExistant);

        // Convertir l'entité modifiée en DTO et la retourner
        return dispositifMapper.toDto(dispositifMedicalModifie);
    }

    @Override
    public void supprimerUnDispositifMedical(String idDispositif) {

        // Vérifier que l'ID n'est pas null ou vide
        if (idDispositif == null || idDispositif.isEmpty()) {
            throw new IllegalArgumentException("L'ID du dispositif médical est requis pour la suppression.");
        }

        // Récupérer le dispositif médical existant par son ID
        DispositifMedical dispositifMedicalExistant = dispositifRepository.findById(idDispositif)
                .orElseThrow(() -> new EntityNotFoundException("Dispositif médical avec ID " + idDispositif + " non trouvé."));

        // Supprimer le dispositif médical de la base de données
        dispositifRepository.delete(dispositifMedicalExistant);
    }


    @Override
    public DispositifMedicalDto recupererUnDispositifMedicalParId(String idDispositif) {

        // Vérifier que l'ID n'est pas null ou vide
        if (idDispositif == null || idDispositif.isEmpty()) {
            throw new IllegalArgumentException("L'ID du dispositif médical est requis.");
        }

        // Récupérer le dispositif médical existant par son ID
        DispositifMedical dispositifMedical = dispositifRepository.findById(idDispositif)
                .orElseThrow(() -> new EntityNotFoundException("Dispositif médical avec ID " + idDispositif + " non trouvé."));

        // Convertir l'entité en DTO et retourner le DTO
        return dispositifMapper.toDto(dispositifMedical);
    }

    @Override
    public List<DispositifMedicalDto> recupererLesDispositifMedicaux() {
        // Récupérer toutes les entités DispositifMedical
        List<DispositifMedical> dispositifsMedicaux = dispositifRepository.findAll();

        // Convertir la liste d'entités en liste de DTOs
        return dispositifsMedicaux.stream()
                .map(dispositifMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Page<DispositifMedicalDto> recuperationParPagination(int page, int limit) {
        // Récupérer la page de dispositifs médicaux
        Page<DispositifMedical> dispositifsMedicauxPage = dispositifRepository.findAll(PageRequest.of(page, limit));

        // Convertir la page d'entités en page de DTOs
        return dispositifsMedicauxPage.map(dispositifMapper::toDto);
    }

}
