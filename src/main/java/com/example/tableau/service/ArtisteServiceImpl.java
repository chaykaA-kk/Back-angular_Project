package com.example.tableau.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.tableau.entities.Artiste;
import com.example.tableau.entities.Tableau;
import com.example.tableau.repos.ArtisteRepository;
import com.example.tableau.repos.TableauRepository;

@Service
public class ArtisteServiceImpl implements ArtisteService {

    @Autowired
    ArtisteRepository artisteRepository;

    @Autowired
    TableauRepository tableauRepository;  // ✅ Ajouté pour gérer les tableaux liés

    @Override
    public Artiste saveArtiste(Artiste a) {
        return artisteRepository.save(a);
    }

    @Override
    public Artiste updateArtiste(Artiste a) {
        return artisteRepository.save(a);
    }

    @Override
    public void deleteArtiste(Artiste a) {
        artisteRepository.delete(a);
    }

    @Override
    public void deleteArtisteById(Long id) {
        // ✅ CORRECTION : Avant de supprimer l'artiste, mettre artiste = null dans tous ses tableaux
        
        // 1. Récupérer tous les tableaux de cet artiste
        List<Tableau> tableaux = tableauRepository.findByArtisteIdArtiste(id);
        
        // 2. Pour chaque tableau, mettre l'artiste à null
        for (Tableau tableau : tableaux) {
            tableau.setArtiste(null);
            tableauRepository.save(tableau);
        }
        
        // 3. Maintenant on peut supprimer l'artiste en toute sécurité
        artisteRepository.deleteById(id);
    }

    @Override
    public Artiste getArtiste(Long id) {
        return artisteRepository.findById(id).orElse(null);
    }

    @Override
    public List<Artiste> getAllArtistes() {
        return artisteRepository.findAll();
    }
}