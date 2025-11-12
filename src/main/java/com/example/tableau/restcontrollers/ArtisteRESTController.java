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
import com.example.tableau.repos.ArtisteRepository;

/**
 * REST Controller for managing Artiste entities.
 * This controller provides endpoints to retrieve artiste information
 * that will be consumed by the Angular frontend.
 */
@RestController
@RequestMapping("/api/art")  // This creates endpoints at /tableau/api/art
@CrossOrigin(origins = "http://localhost:4200")  // Allow Angular app to call these endpoints
public class ArtisteRESTController {

    @Autowired
    ArtisteRepository artisteRepository;

    /**
     * GET endpoint to retrieve all artistes.
     * URL: http://localhost:8081/tableau/api/art
     * This is used to populate the dropdown list in add/update forms.
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<Artiste> getAllArtistes() {
        return artisteRepository.findAll();
    }
    @RequestMapping(method = RequestMethod.POST)
    public Artiste createArtiste(@RequestBody Artiste artiste) {
        return artisteRepository.save(artiste); 
    }
    @RequestMapping(method = RequestMethod.PUT)
    public Artiste updateArtiste(@RequestBody Artiste artiste) {
        return artisteRepository.save(artiste);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void deleteArtiste(@PathVariable("id") Long id) {
        artisteRepository.deleteById(id);
    }

    /**
     * GET endpoint to retrieve a single artiste by ID.
     * URL: http://localhost:8081/tableau/api/art/{id}
     * This is used when we need details about a specific artiste.
     */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Artiste getArtisteById(@PathVariable("id") Long id) {
        return artisteRepository.findById(id).get();
    }
}