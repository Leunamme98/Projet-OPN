package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.model.DispositifMedical;
import com.officePharmaceutiqueNationale.OPN.dto.DispositifMedicalDto;
import com.officePharmaceutiqueNationale.OPN.mapper.DispositifMedicalMapper;
import com.officePharmaceutiqueNationale.OPN.repository.DispositifMedicalRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.DispositifMedicalService;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class DispositifMedicalServiceImpl implements DispositifMedicalService {

    private final DispositifMedicalRepository dispositifMedicalRepository;
    private final DispositifMedicalMapper dispositifMedicalMapper;

    public DispositifMedicalServiceImpl(DispositifMedicalRepository dispositifMedicalRepository, DispositifMedicalMapper dispositifMedicalMapper) {
        this.dispositifMedicalRepository = dispositifMedicalRepository;
        this.dispositifMedicalMapper = dispositifMedicalMapper;
    }

    // Enregistrer un nouveau dispositif médical
    @Override
    public DispositifMedicalDto enregistrerUnDispositifMedical(DispositifMedicalDto dispositifMedicalDto) {

        // Vérification des données d'entrée
        if (dispositifMedicalDto == null) {
            throw new IllegalArgumentException("Les données du dispositif médical ne peuvent pas être nulles");
        }

        DispositifMedical dispositifMedical = dispositifMedicalMapper.toEntity(dispositifMedicalDto);
        // Génération de l'id UUID
        String id = UUID.randomUUID().toString();
        dispositifMedical.setId(id);

        // Par défaut, isDeleted est à true
        dispositifMedical.setIsDeleted(true);

        DispositifMedical savedDispositif = dispositifMedicalRepository.save(dispositifMedical);
        return dispositifMedicalMapper.toDto(savedDispositif);
    }

    // Modifier un dispositif médical
    @Override
    public DispositifMedicalDto modifierUnDispositifMedical(DispositifMedicalDto dto) {
        if (dto == null || dto.getId() == null) {
            throw new IllegalArgumentException("L'ID du dispositif médical ne peut pas être nul");
        }

        DispositifMedical existingDispositif = dispositifMedicalRepository.findById(dto.getId())
                .orElseThrow(() -> new ResourceNotFoundException("Dispositif médical non trouvé avec l'ID : " + dto.getId()));

        existingDispositif.setCode(dto.getCode());
        existingDispositif.setLibelle(dto.getLibelle());
        existingDispositif.setDateExpiration(dto.getDateExpiration());
        existingDispositif.setPrixGenerique(dto.getPrixGenerique());
        existingDispositif.setQuantiteStockSeuil(dto.getQuantiteStockSeuil());
        existingDispositif.setDescription(dto.getDescription());
        existingDispositif.setCheminImage(dto.getCheminImage());
        existingDispositif.setEtat(dto.getEtat());
        existingDispositif.setPaysFabrication(dto.getPaysFabrication());
        existingDispositif.setQuantiteStock(dto.getQuantiteStock());

        DispositifMedical updatedDispositif = dispositifMedicalRepository.save(existingDispositif);
        return dispositifMedicalMapper.toDto(updatedDispositif);
    }

    // Supprimer un dispositif médical existant
    @Override
    public void supprimerUnDispositifMedical(String idDispositif) {
        if (idDispositif == null) {
            throw new IllegalArgumentException("L'ID du dispositif médical ne peut pas être nul");
        }

        DispositifMedical dispositifMedical = dispositifMedicalRepository.findById(idDispositif)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositif médical non trouvé avec l'ID : " + idDispositif));

        // Vérifier l'état de l'attribut isDeleted
        if (Boolean.FALSE.equals(dispositifMedical.getIsDeleted())) {
            throw new IllegalStateException("Le dispositif médical ne peut être supprimé que si son état est marqué comme supprimé (isDeleted = true)");
        }

        dispositifMedicalRepository.delete(dispositifMedical);
    }

    // Récupérer un dispositif médical par son ID
    @Override
    public DispositifMedicalDto recupererUnDispositifMedicalParId(String idDispositif) {
        if (idDispositif == null) {
            throw new IllegalArgumentException("L'ID du dispositif médical ne peut pas être nul");
        }

        DispositifMedical dispositifMedical = dispositifMedicalRepository.findById(idDispositif)
                .orElseThrow(() -> new ResourceNotFoundException("Dispositif médical non trouvé avec l'ID : " + idDispositif));

        return dispositifMedicalMapper.toDto(dispositifMedical);
    }

    // Récupérer tous les dispositifs médicaux
    @Override
    public List<DispositifMedicalDto> recupererLesDispositifsMedicaux() {
        List<DispositifMedical> dispositifs = dispositifMedicalRepository.findAll();
        return dispositifMedicalMapper.toDtoList(dispositifs);
    }

    // Faire la pagination
    @Override
    public Page<DispositifMedicalDto> recuperationParPagination(int page, int limit) {
        Pageable pageable = PageRequest.of(page, limit);
        Page<DispositifMedical> dispositifsPage = dispositifMedicalRepository.findAll(pageable);
        return dispositifsPage.map(dispositifMedicalMapper::toDto);
    }

    // Récupérer les métadonnées
    @Override
    public Page<DispositifMedicalDto> recuperationDesMetadonnees(int page, int limit) {
        // Méthode non implémentée
        return null;
    }
}
