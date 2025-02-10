package com.officePharmaceutiqueNationale.OPN.sercice;

import com.officePharmaceutiqueNationale.OPN.dto.ClientDto;
import org.springframework.data.domain.Page;

import java.util.List;

public interface ClientService {

    // Enregistrer un nouveau client
    ClientDto enregistrerClient(ClientDto clientDto);

    // Modifier un client existant
    ClientDto modifierClient(ClientDto clientDto);

    // Supprimer un client par son ID
    void supprimerClient(String idClient);

    // Récupérer un client par son ID
    ClientDto recupererClientParId(String idClient);

    // Récupérer la liste de tous les clients
    List<ClientDto> recupererTousLesClients();

    // Faire la pagination
    Page<ClientDto> recuperationParPagination(int page, int limit);

    // Récupérer les métadonnées
    Page<ClientDto> recuperationDesMetadonnees(int page, int limit);

}
