package com.officePharmaceutiqueNationale.OPN.config;

import com.officePharmaceutiqueNationale.OPN.model.*;
import com.officePharmaceutiqueNationale.OPN.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Configuration
public class DatabaseInitializer {

    @Bean
    CommandLineRunner initDatabase(
            DciRepository dciRepository,
            SpecialitePharmaceutiqueRepository specialiteRepo,
            FormeGaleniqueRepository formeRepo,
            MedicamentRepository medicamentRepository
    ) {
        return args -> {

            // 📌 Ajouter les DCI (Dénominations communes internationales)
            if (dciRepository.count() == 0) {
                List<Dci> dciList = List.of(
                        new Dci(UUID.randomUUID().toString(), "Paracétamol", false),
                        new Dci(UUID.randomUUID().toString(), "Ibuprofène", false),
                        new Dci(UUID.randomUUID().toString(), "Amoxicilline", false),
                        new Dci(UUID.randomUUID().toString(), "Ciprofloxacine", false),
                        new Dci(UUID.randomUUID().toString(), "Oméprazole", false),
                        new Dci(UUID.randomUUID().toString(), "Azithromycine", false),
                        new Dci(UUID.randomUUID().toString(), "Metformine", false),
                        new Dci(UUID.randomUUID().toString(), "Salbutamol", false),
                        new Dci(UUID.randomUUID().toString(), "Prednisolone", false),
                        new Dci(UUID.randomUUID().toString(), "Losartan", false)
                );
                dciRepository.saveAll(dciList);
            }

            // 📌 Ajouter les Formes Galéniques
            if (formeRepo.count() == 0) {
                List<FormeGalenique> formes = List.of(
                        new FormeGalenique(UUID.randomUUID().toString(), "Comprimé", "Forme solide orale", false),
                        new FormeGalenique(UUID.randomUUID().toString(), "Sirop", "Forme liquide orale", false),
                        new FormeGalenique(UUID.randomUUID().toString(), "Gélule", "Capsule dure contenant le médicament", false),
                        new FormeGalenique(UUID.randomUUID().toString(), "Injection", "Solution injectable", false),
                        new FormeGalenique(UUID.randomUUID().toString(), "Pommade", "Application locale sur la peau", false)
                );
                formeRepo.saveAll(formes);
            }

            // 📌 Ajouter  Spécialités Pharmaceutiques
            if (specialiteRepo.count() == 0) {
                List<Dci> allDci = dciRepository.findAll();

                List<SpecialitePharmaceutique> specialites = List.of(
                        new SpecialitePharmaceutique(UUID.randomUUID().toString(), "Doliprane 500mg", "Sanofi", false, allDci.get(0)),
                        new SpecialitePharmaceutique(UUID.randomUUID().toString(), "Nurofen 400mg", "Reckitt Benckiser", false, allDci.get(1)),
                        new SpecialitePharmaceutique(UUID.randomUUID().toString(), "Amoxicilline 1g", "GSK", false, allDci.get(2)),
                        new SpecialitePharmaceutique(UUID.randomUUID().toString(), "Ciflox 500mg", "Bayer", false, allDci.get(3)),
                        new SpecialitePharmaceutique(UUID.randomUUID().toString(), "Mopral 20mg", "AstraZeneca", false, allDci.get(4))
                );
                specialiteRepo.saveAll(specialites);
            }

            // 📌 Ajouter les Médicaments
            if (medicamentRepository.count() == 0) {
                List<FormeGalenique> allFormes = formeRepo.findAll();
                List<SpecialitePharmaceutique> allSpecialites = specialiteRepo.findAll();

                List<Medicament> medicaments = List.of(
                        new Medicament(UUID.randomUUID().toString(), "MED-001", "Doliprane 500mg", LocalDate.of(2026, 5, 15),
                                2.50, 5.00, 10, 50, "Analgésique et antipyrétique",
                                EtatArticle.DISPONIBLE, false, 500.0, "mg", allFormes.get(0), allSpecialites.get(0)),

                        new Medicament(UUID.randomUUID().toString(), "MED-002", "Nurofen 400mg", LocalDate.of(2025, 8, 20),
                                3.00, 6.50, 8, 40, "Anti-inflammatoire non stéroïdien",
                                EtatArticle.DISPONIBLE, false, 400.0, "mg", allFormes.get(0), allSpecialites.get(1)),

                        new Medicament(UUID.randomUUID().toString(), "MED-003", "Amoxicilline 1g", LocalDate.of(2027, 3, 10),
                                5.00, 10.00, 5, 30, "Antibiotique à large spectre",
                                EtatArticle.DISPONIBLE, false, 1.0, "g", allFormes.get(2), allSpecialites.get(2)),

                        new Medicament(UUID.randomUUID().toString(), "MED-004", "Ciflox 500mg", LocalDate.of(2026, 11, 25),
                                4.50, 9.00, 6, 35, "Antibiotique fluoroquinolone",
                                EtatArticle.DISPONIBLE, false, 500.0, "mg", allFormes.get(0), allSpecialites.get(3)),

                        new Medicament(UUID.randomUUID().toString(), "MED-005", "Mopral 20mg", LocalDate.of(2028, 1, 5),
                                7.00, 14.00, 4, 20, "Inhibiteur de la pompe à protons",
                                EtatArticle.DISPONIBLE, false, 20.0, "mg", allFormes.get(0), allSpecialites.get(4)),

                        // Ajout d'autres médicaments réels
                        new Medicament(UUID.randomUUID().toString(), "MED-006", "Paracétamol 1g", LocalDate.of(2026, 5, 30),
                                2.50, 5.00, 10, 50, "Analgésique et antipyrétique",
                                EtatArticle.DISPONIBLE, false, 1.0, "g", allFormes.get(0), allSpecialites.get(0)),

                        new Medicament(UUID.randomUUID().toString(), "MED-007", "Ibuprofène 200mg", LocalDate.of(2027, 10, 15),
                                1.50, 3.00, 15, 60, "Anti-inflammatoire non stéroïdien",
                                EtatArticle.DISPONIBLE, false, 200.0, "mg", allFormes.get(0), allSpecialites.get(1)),

                        new Medicament(UUID.randomUUID().toString(), "MED-008", "Oméprazole 20mg", LocalDate.of(2028, 8, 10),
                                5.00, 10.00, 20, 70, "Inhibiteur de la pompe à protons",
                                EtatArticle.DISPONIBLE, false, 20.0, "mg", allFormes.get(0), allSpecialites.get(4))


                );
                medicamentRepository.saveAll(medicaments);
            }
        };
    }
}
