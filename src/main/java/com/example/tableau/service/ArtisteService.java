
package com.example.tableau.service;

import java.util.List;
import com.example.tableau.entities.Artiste;

public interface ArtisteService {
    Artiste saveArtiste(Artiste a);
    Artiste updateArtiste(Artiste a);
    void deleteArtiste(Artiste a);
    void deleteArtisteById(Long id);
    Artiste getArtiste(Long id);
    List<Artiste> getAllArtistes();
}