package com.example.tableau.restcontrollers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.example.tableau.entities.Tableau;
import com.example.tableau.service.TableauService;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:4200")
public class TableauRESTController {

    @Autowired
    TableauService tableauService;

    // Étape 1 : Récupérer tous les tableaux
    @RequestMapping(path="all",method = RequestMethod.GET)
    public List<Tableau> getAllTableaux() {
        return tableauService.getAllTableaux();
    }
    

    // Étape 2 : Récupérer un tableau par son ID
    @RequestMapping(value="/getbyid{id}", method = RequestMethod.GET)

    public Tableau getTableauById(@PathVariable("id") Long id) {
        return tableauService.getTableau(id);
    }

    // Étape 3 : Créer un nouveau tableau
    @RequestMapping(value="/addtab",method = RequestMethod.POST)

    public Tableau createTableau(@RequestBody Tableau tableau) {
        return tableauService.saveTableau(tableau);
    }
    // Étape 4 : Modifier un tableau existant
    @RequestMapping(value="/updatetab",method = RequestMethod.PUT)
    public Tableau updateTableau(@RequestBody Tableau tableau) {
        return tableauService.updateTableau(tableau);
    }
    // Étape 5 : Supprimer un tableau par ID
    @RequestMapping(value="/droptab{id}", method = RequestMethod.DELETE)
    public void deleteTableau(@PathVariable("id") Long id) {
        tableauService.deleteTableauById(id);
    }
    // Étape 6 : Récupérer les tableaux d’un artiste donné
    @RequestMapping(value="/tableauxartiste/{idArtiste}", method = RequestMethod.GET)
    public List<Tableau> getTableauxByArtisteId(@PathVariable("idArtiste") Long idArtiste) {
        return tableauService.findByArtisteIdArtiste(idArtiste);
    }
    @RequestMapping(value="/tableauxByName/{nom}", method = RequestMethod.GET)
    public List<Tableau> findByNomTableauContains(@PathVariable("nom") String nom) {
        return tableauService.findByNomTableauContains(nom);
    }
    



}