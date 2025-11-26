package com.example.tableau.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;
import com.example.tableau.entities.Artiste;

@RepositoryRestResource(path = "art")
@CrossOrigin(origins = "http://localhost:4200")
public interface ArtisteRepository extends JpaRepository<Artiste, Long> {
    // Spring Data REST générera automatiquement les endpoints CRUD
}