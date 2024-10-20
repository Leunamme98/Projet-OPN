package com.officePharmaceutiqueNationale.OPN.sercice.impl;

import com.officePharmaceutiqueNationale.OPN.dto.ClientDto;
import com.officePharmaceutiqueNationale.OPN.mapper.ClientMapper;
import com.officePharmaceutiqueNationale.OPN.model.Client;
import com.officePharmaceutiqueNationale.OPN.model.Panier;
import com.officePharmaceutiqueNationale.OPN.repository.ClientRepository;
import com.officePharmaceutiqueNationale.OPN.repository.PanierRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.ClientService;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class ClientServiceImpl implements ClientService {

    private final ClientRepository clientRepository;
    private final ClientMapper clientMapper;
    private final BCryptPasswordEncoder passwordEncoder;
    private final PanierRepository panierRepository;

    @Override
    public ClientDto enregistrerClient(ClientDto clientDto) {
        // Vérifier que le DTO du client n'est pas nul
        if (clientDto == null) {
            throw new IllegalArgumentException("Le DTO du client ne peut pas être nul");
        }

        // Générer un nouvel ID unique pour le client
        String generatedId = UUID.randomUUID().toString();

        // Mapper le DTO à l'entité Client
        Client client = clientMapper.toEntity(clientDto);

        // Définir l'ID généré et marquer le client comme pouvant être supprimé
        client.setId(generatedId);
        client.setIsDeleted(true);
        client.setIsActive(false);

        // Vérifier que le mot de passe est fourni
        if (clientDto.getMotDePasse() == null || clientDto.getMotDePasse().isEmpty()) {
            throw new IllegalArgumentException("Le mot de passe ne peut pas être nul ou vide");
        }

        // Hacher le mot de passe et le définir dans l'entité
        String hashedPassword = passwordEncoder.encode(clientDto.getMotDePasse());
        client.setMotDePasseHache(hashedPassword);

        // Enregistrer le client dans la base de données
        Client savedClient = clientRepository.save(client);

        // Créer et assigner un panier au client
        Panier panier = new Panier();
        panier.setId(UUID.randomUUID().toString());
        panier.setClientId(generatedId);
        panier.setNombreArticle(0);
        panier.setPrixTotalPanier(0D);

        // Sauvegarder le panier qui inclut maintenant la référence au client
        panierRepository.save(panier);

        // Assigner le panier au client
        savedClient.setPanier(panier);

        // Mettre à jour le client avec la référence au panier
        clientRepository.save(savedClient);

        // Retourner le DTO du client enregistré
        return clientMapper.toDto(savedClient);
    }

    // Modifier un client existant
    @Override
    public ClientDto modifierClient(ClientDto clientDto) {
        // Vérifier que le DTO n'est pas nul
        if (clientDto == null) {
            throw new IllegalArgumentException("Le DTO du client à modifier ne peut pas être nul");
        }

        // Récupérer le client existant par son ID
        Client clientExist = clientRepository.findById(clientDto.getId())
                .orElseThrow(() -> new EntityNotFoundException("Client avec ID " + clientDto.getId() + " non trouvé"));

        // Modification des données, en ignorant motDePasse et isDeleted
        clientExist.setIdentifiant(clientDto.getIdentifiant());
        clientExist.setAdresse(clientDto.getAdresse());
        clientExist.setNumeroTelephone(clientDto.getNumeroTelephone());
        clientExist.setEmail(clientDto.getEmail());
        clientExist.setCheminPhoto(clientDto.getCheminPhoto());
        clientExist.setNomEntreprise(clientDto.getNomEntreprise());
        clientExist.setNumeroAccreditation(clientDto.getNumeroAccreditation());
        clientExist.setTypeStructure(clientDto.getTypeStructure());
        clientExist.setNomDuResponsable(clientDto.getNomDuResponsable());
        clientExist.setNumeroContactResponsable(clientDto.getNumeroContactResponsable());
        clientExist.setIsActive(clientDto.getIsActive());

        // Enregistrer les modifications dans la base de données
        Client updatedClient = clientRepository.save(clientExist);

        // Retourner le DTO du client mis à jour
        return clientMapper.toDto(updatedClient);
    }

    // Supprimer un client
    @Override
    public void supprimerClient(String idClient) {
        // Vérifier que l'ID du client n'est pas nul ou vide
        if (idClient == null || idClient.isEmpty()) {
            throw new IllegalArgumentException("L'ID du client à supprimer ne peut pas être nul ou vide");
        }

        // Récupérer le client existant par son ID
        Client clientExist = clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client avec ID " + idClient + " non trouvé"));

        // Vérifier si le client est marqué comme supprimé
        if (!clientExist.getIsDeleted()) {
            throw new IllegalStateException("Le client avec ID " + idClient + " ne peut pas être supprimé car il est actif");
        }

        // Supprimer le panier associé au client
        panierRepository.deleteByClientId(idClient);

        // Supprimer le client de la base de données
        clientRepository.delete(clientExist);
    }

    // Récupérer un client par son ID
    @Override
    public ClientDto recupererClientParId(String idClient) {
        // Vérifier que l'ID du client n'est pas nul ou vide
        if (idClient == null || idClient.isEmpty()) {
            throw new IllegalArgumentException("L'ID du client ne peut pas être nul ou vide");
        }

        // Récupérer le client existant par son ID
        Client clientExist = clientRepository.findById(idClient)
                .orElseThrow(() -> new EntityNotFoundException("Client avec ID " + idClient + " non trouvé"));

        // Convertir le client en DTO et le retourner
        return clientMapper.toDto(clientExist);
    }

    // Récupérer la liste de tous les clients
    @Override
    public List<ClientDto> recupererTousLesClients() {
        // Récupérer la liste de tous les clients
        List<Client> clientsList = clientRepository.findAll();

        // Convertir la liste des clients en DTOs
        return clientMapper.toDtoList(clientsList);
    }

    // Faire la pagination
    @Override
    public Page<ClientDto> recuperationParPagination(int page, int limit) {
        // Vérification des paramètres de pagination
        if (page < 0 || limit <= 0) {
            throw new IllegalArgumentException("Les paramètres de pagination ne sont pas valides");
        }

        // Récupérer les clients avec pagination
        return clientRepository.findAll(PageRequest.of(page, limit))
                .map(clientMapper::toDto);
    }

    // Récupérer les métadonnées
    @Override
    public Page<ClientDto> recuperationDesMetadonnees(int page, int limit) {
        return null; // Cette méthode peut être implémentée plus tard si nécessaire
    }
}
