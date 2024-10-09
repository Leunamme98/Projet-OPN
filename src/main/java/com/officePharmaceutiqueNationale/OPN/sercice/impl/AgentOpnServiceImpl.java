package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.AgentOpnDto;
import com.officePharmaceutiqueNationale.OPN.mapper.AgentOpnMapper;
import com.officePharmaceutiqueNationale.OPN.model.AgentOpn;
import com.officePharmaceutiqueNationale.OPN.repository.AgentOpnRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.AgentOpnService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AgentOpnServiceImpl implements AgentOpnService {

    private final AgentOpnRepository agentOpnRepository;
    private final AgentOpnMapper agentOpnMapper;
    private final BCryptPasswordEncoder passwordEncoder;


    // Enregistrer un nouvel agent d'OPN
    @Override
    public AgentOpnDto enregistrerAgent(AgentOpnDto agentOpnDto) {
        // Vérifier que l'objet DTO n'est pas nul
        if (agentOpnDto == null) {
            throw new IllegalArgumentException("Le DTO de l'agent ne peut pas être nul");
        }

        // Générer un nouvel ID unique pour l'agent
        String generatedId = UUID.randomUUID().toString();

        // Mapper le DTO à l'entité AgentOpn
        AgentOpn agentOpn = agentOpnMapper.toEntity(agentOpnDto);

        // Définir l'ID généré et marquer l'agent comme pouvant être supprimé
        agentOpn.setId(generatedId);
        agentOpn.setIsDeleted(true);

        // Vérifier que le mot de passe est fourni
        if (agentOpnDto.getMotDePasse() == null || agentOpnDto.getMotDePasse().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être nul ou vide");
        }

        // Hacher le mot de passe et le définir dans l'entité
        String hashedPassword = passwordEncoder.encode(agentOpnDto.getMotDePasse());
        agentOpn.setMotDePasseHache(hashedPassword);

        // Enregistrer l'agent dans la base de données
        AgentOpn savedAgent = agentOpnRepository.save(agentOpn);

        // Retourner le DTO de l'agent enregistré
        return agentOpnMapper.toDto(savedAgent);
    }

    // Modifier un agent de l'OPN
    @Override
    public AgentOpnDto modifierAgent(AgentOpnDto agentOpnDto) {
        // Vérifier que le DTO n'est pas nul
        if (agentOpnDto == null) {
            throw new IllegalArgumentException("Le DTO de l'agent à modifier ne peut pas être nul");
        }

        // Récupérer l'agent existant par son ID
        AgentOpn agentOpnExist = agentOpnRepository.findById(agentOpnDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Agent avec ID " + agentOpnDto.getId() + " non trouvé"));

        // Modification des données (sans changer mot de passe et isDeleted)
        agentOpnExist.setIdentifiant(agentOpnDto.getIdentifiant());
        agentOpnExist.setAdresse(agentOpnDto.getAdresse());
        agentOpnExist.setNumeroTelephone(agentOpnDto.getNumeroTelephone());
        agentOpnExist.setEmail(agentOpnDto.getEmail());
        agentOpnExist.setCheminPhoto(agentOpnDto.getCheminPhoto());
        agentOpnExist.setMatriculeAgent(agentOpnDto.getMatriculeAgent());
        agentOpnExist.setNomAgent(agentOpnDto.getNomAgent());
        agentOpnExist.setPrenomAgent(agentOpnDto.getPrenomAgent());

        // Note: on ne change pas le mot de passe ni isDeleted

        // Enregistrer les modifications dans la base de données
        AgentOpn updatedAgent = agentOpnRepository.save(agentOpnExist);

        // Retourner le DTO de l'agent mis à jour
        return agentOpnMapper.toDto(updatedAgent);
    }

    // Supprimer un agent de l'OPN existant
    @Override
    public void supprimerAgent(String idAgent) {
        // Vérifier que l'ID de l'agent n'est pas nul ou vide
        if (idAgent == null || idAgent.isEmpty()) {
            throw new IllegalArgumentException("L'ID de l'agent à supprimer ne peut pas être nul ou vide");
        }

        // Récupérer l'agent existant par son ID
        AgentOpn agentOpnExist = agentOpnRepository.findById(idAgent)
                .orElseThrow(() -> new EntityNotFoundException("Agent avec ID " + idAgent + " non trouvé"));

        // Vérifier si l'agent est marqué comme supprimé
        if (!agentOpnExist.getIsDeleted()) {
            throw new IllegalStateException("L'agent avec ID " + idAgent + " ne peut pas être supprimé car il est actif");
        }

        // Si isDeleted est true, procéder à la suppression
        agentOpnRepository.delete(agentOpnExist);
    }

    // Récupérer un Agent via  son id
    @Override
    public AgentOpnDto recupererAgentParId(String idAgent) {
        // Vérifier que l'ID de l'agent n'est pas nul ou vide
        if (idAgent == null || idAgent.isEmpty()) {
            throw new IllegalArgumentException("L'ID de l'agent ne peut pas être nul ou vide");
        }

        // Récupérer l'agent existant par son ID
        AgentOpn agentOpnExist = agentOpnRepository.findById(idAgent)
                .orElseThrow(() -> new EntityNotFoundException("Agent avec ID " + idAgent + " non trouvé"));


        // Convertir l'agent en DTO et le retourner
        return agentOpnMapper.toDto(agentOpnExist);
    }

    // Récuperer tous les agents
    @Override
    public List<AgentOpnDto> recupererTousLesAgents() {
        // Récupérer la liste de tous les agents
        List<AgentOpn> agentsOpnList = agentOpnRepository.findAll();

        // Convertir la liste des agents en DTOs
        return agentOpnMapper.toDtoList(agentsOpnList);
    }

    // Faire la pagination
    @Override
    public Page<AgentOpnDto> recuperationParPagination(int page, int limit) {
        // Vérification des paramètres de pagination
        if (page < 0 || limit <= 0) {
            throw new IllegalArgumentException("Les paramètres de pagination ne sont pas valides");
        }

        // Récupérer les agents avec pagination
        return agentOpnRepository.findAll(PageRequest.of(page, limit))
                .map(agentOpnMapper::toDto);
    }

    // Récupérer les métadonnées
    @Override
    public Page<AgentOpnDto> recuperationDesMetadonnees(int page, int limit) {
        return null;
    }

}
