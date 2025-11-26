package com.example.tableau.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.tableau.entities.Artiste;
import com.example.tableau.service.ArtisteService;

@RestController
@RequestMapping("/api/art")
@CrossOrigin(origins = "http://localhost:4200")
public class ArtisteRESTController {

    @Autowired
    ArtisteService artisteService;

    // ✅ Récupérer tous les artistes
    @RequestMapping(method = RequestMethod.GET)
    public List<Artiste> getAllArtistes() {
        return artisteService.getAllArtistes();
    }

    // ✅ Récupérer un artiste par ID
    @RequestMapping(value="/{id}", method = RequestMethod.GET)
    public Artiste getArtisteById(@PathVariable("id") Long id) {
        return artisteService.getArtiste(id);
    }

    // ✅ Créer un nouvel artiste
    @RequestMapping(method = RequestMethod.POST)
    public Artiste createArtiste(@RequestBody Artiste artiste) {
        return artisteService.saveArtiste(artiste);
    }

    // ✅ Modifier un artiste existant
    @RequestMapping(method = RequestMethod.PUT)
    public Artiste updateArtiste(@RequestBody Artiste artiste) {
        return artisteService.updateArtiste(artiste);
    }

    // ✅ Supprimer un artiste par ID
    @RequestMapping(value="/{id}", method = RequestMethod.DELETE)
    public void deleteArtiste(@PathVariable("id") Long id) {
        artisteService.deleteArtisteById(id);
    }
}