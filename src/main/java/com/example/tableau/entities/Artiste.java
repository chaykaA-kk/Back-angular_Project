package com.example.tableau.entities;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Artiste {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idArtiste;
    
    private String nomArtiste;
    private String nationalite;

    @JsonIgnore
    @OneToMany(mappedBy = "artiste")
    private List<Tableau> tableaux;

    // Constructeurs
    public Artiste() {
        super();
    }

    public Artiste(String nomArtiste, String nationalite) {
        super();
        this.nomArtiste = nomArtiste;
        this.nationalite = nationalite;
    }

    // Getters et Setters
    public Long getIdArtiste() {
        return idArtiste;
    }

    public void setIdArtiste(Long idArtiste) {
        this.idArtiste = idArtiste;
    }

    public String getNomArtiste() {
        return nomArtiste;
    }

    public void setNomArtiste(String nomArtiste) {
        this.nomArtiste = nomArtiste;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public List<Tableau> getTableaux() {
        return tableaux;
    }

    public void setTableaux(List<Tableau> tableaux) {
        this.tableaux = tableaux;
    }

    @Override
    public String toString() {
        return "Artiste [idArtiste=" + idArtiste + ", nomArtiste=" + nomArtiste + 
               ", nationalite=" + nationalite + "]";
    }
}