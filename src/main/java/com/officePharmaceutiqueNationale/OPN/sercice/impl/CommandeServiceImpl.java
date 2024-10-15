package com.officePharmaceutiqueNationale.OPN.sercice.impl;



import com.officePharmaceutiqueNationale.OPN.dto.CommandeDto;
import com.officePharmaceutiqueNationale.OPN.exception.ResourceNotFoundException;
import com.officePharmaceutiqueNationale.OPN.mapper.CommandeMapper;
import com.officePharmaceutiqueNationale.OPN.model.Commande;
import com.officePharmaceutiqueNationale.OPN.model.EtatCommande;
import com.officePharmaceutiqueNationale.OPN.repository.CommandeRepository;
import com.officePharmaceutiqueNationale.OPN.sercice.CommandeService;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.UUID;


@Service
@Transactional
public class CommandeServiceImpl implements CommandeService {

    private final CommandeRepository commandeRepository;
    private final CommandeMapper commandeMapper;
    private static final Random RANDOM = new Random();

    public CommandeServiceImpl(CommandeRepository commandeRepository, CommandeMapper commandeMapper) {
        this.commandeRepository = commandeRepository;
        this.commandeMapper = commandeMapper;
    }

    // Créer une nouvelle commande
    @Override
    public CommandeDto creerCommande(CommandeDto commandeDto) {
        if (commandeDto == null) {
            throw new IllegalArgumentException("Les données de la commande ne peuvent pas être nulles");
        }

        // Générer un ID pour la nouvelle commande
        String id = UUID.randomUUID().toString();
        Commande commande = commandeMapper.toEntity(commandeDto);
        commande.setId(id);

        // Initialiser l'état de la commande à "En Cours"
        commande.setEtatCommande(EtatCommande.EN_COURS);

        // Définir la date de commande à la date et l'heure actuelles en GMT
        commande.setDateCommande(LocalDateTime.now(ZoneOffset.UTC));

        // Générer un numéro de commande unique
        commande.setNumeroCommande(genererNumeroCommande());


        // Sauvegarder la commande
        Commande savedCommande = commandeRepository.save(commande);
        return commandeMapper.toDto(savedCommande);
    }

    // Récupérer une commande par son ID
    @Override
    public CommandeDto getCommandeById(String commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + commandeId));
        return commandeMapper.toDto(commande);
    }

    // Récupérer toutes les commandes
    @Override
    public List<CommandeDto> getAllCommandes() {
        List<Commande> commandes = commandeRepository.findAll();
        return commandes.stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // Récupérer toutes les commandes d'un client
    @Override
    public List<CommandeDto> getCommandesByClientId(String clientId) {
        List<Commande> commandes = commandeRepository.findByClientId(clientId);
        return commandes.stream()
                .map(commandeMapper::toDto)
                .collect(Collectors.toList());
    }

    // Mettre à jour l'état d'une commande
    @Override
    public CommandeDto mettreAJourEtatCommande(String commandeId, String nouvelEtat) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + commandeId));

        // Vérifier et mettre à jour l'état de la commande
        try {
            EtatCommande etatCommande = EtatCommande.valueOf(nouvelEtat.toUpperCase());
            commande.setEtatCommande(etatCommande);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("L'état de commande est invalide : " + nouvelEtat);
        }

        Commande updatedCommande = commandeRepository.save(commande);
        return commandeMapper.toDto(updatedCommande);
    }

    // Supprimer une commande par son ID
    @Override
    public void supprimerCommande(String commandeId) {
        Commande commande = commandeRepository.findById(commandeId)
                .orElseThrow(() -> new ResourceNotFoundException("Commande non trouvée avec l'ID : " + commandeId));

        commandeRepository.delete(commande);
    }

    // Méthode pour générer un numéro de commande unique
    private String genererNumeroCommande() {

        // Obtenir la date et l'heure du système
        String dateFormat = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());

        // Générer un identifiant aléatoire de 4 chiffres
        int randomSuffix = RANDOM.nextInt(10000); // 0 à 9999
        String numeroCommande = "COM" + dateFormat + String.format("%04d", randomSuffix);

        // Vérifier unicité dans la base de données
        while (commandeRepository.existsByNumeroCommande(numeroCommande)) {
            randomSuffix = RANDOM.nextInt(10000);
            numeroCommande = "COM" + dateFormat + String.format("%04d", randomSuffix);
        }

        return numeroCommande;
    }
}